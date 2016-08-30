package parser.publio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.IUrlCrawler;
import parser.PageLoader;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class PublioUrlCrawler implements IUrlCrawler {

    private final Logger logger = LogManager.getRootLogger();

    static final String MAIN_URL = "http://www.publio.pl";
    static final String PROMOTION_URL = MAIN_URL + "/szukaj,promocja.html?sections=EBOOK";

    private final PageLoader pageLoader;
    private final PublioUrlParser publioUrlParser;

    public PublioUrlCrawler(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
        this.publioUrlParser = new PublioUrlParser();
    }

    /**
     * This method uses NextPageUrlProducer and NextPageUrlConsumer because page publio.pl is slower then producer-consumer
     * concept is use to parallelize producing links to books details
     *
     * @return links to book details
     */
    public List<String> getLinksToAllBooks() {
        BlockingQueue queue = new ArrayBlockingQueue<>(32);

        NextPageUrlProducer nextPageUrlProducer = new NextPageUrlProducer(queue,pageLoader, PROMOTION_URL);
        NextPageUrlConsumer nextPageUrlConsumer = new NextPageUrlConsumer(queue,pageLoader);

        Thread nextPageUrlProducerThread = new Thread(nextPageUrlProducer);
        Thread nextPageUrlConsumerThread = new Thread(nextPageUrlConsumer);
        nextPageUrlProducerThread.start();
        nextPageUrlConsumerThread.start();

        try {
            nextPageUrlConsumerThread.join();
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return nextPageUrlConsumer.getBookDetailsUrls();
    }
}
