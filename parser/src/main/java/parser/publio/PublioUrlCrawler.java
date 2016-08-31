package parser.publio;

import lombok.extern.log4j.Log4j2;
import parser.IUrlCrawler;
import parser.PageLoader;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
public class PublioUrlCrawler implements IUrlCrawler {

    static final String MAIN_URL = "http://www.publio.pl";
    static final String PROMOTION_URL = MAIN_URL + "/szukaj,promocja.html?sections=EBOOK";

    private final PageLoader pageLoader;
    private final PublioUrlParser publioUrlParser= new PublioUrlParser();

    public PublioUrlCrawler(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
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
        nextPageUrlProducerThread.setName("NextPageUrlProducerThread");
        Thread nextPageUrlConsumerThread = new Thread(nextPageUrlConsumer);
        nextPageUrlConsumerThread.setName("NextPageUrlConsumerThread");
        nextPageUrlProducerThread.start();
        nextPageUrlConsumerThread.start();

        try {
            nextPageUrlConsumerThread.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return nextPageUrlConsumer.getBookDetailsUrls();
    }
}
