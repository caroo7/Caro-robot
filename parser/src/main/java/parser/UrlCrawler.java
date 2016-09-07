package parser;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
public abstract class UrlCrawler {

    private static final int QUEUE_SIZE = 10;
    protected String promotionURL;
    protected PageLoader pageLoader;

    private final int QUEUE_SIZE=10;

        this.promotionURL = promotionUrl;
    }

    protected UrlCrawler(String promotionUrl, PageLoader pageLoader, IPageUrlParser pageUrlParser) {

        this.promotionURL = promotionUrl;
        this.pageLoader = pageLoader;
        this.pageUrlParser = pageUrlParser;
    }

    public List<String> getLinksToAllBooks() {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(QUEUE_SIZE);

        NextPageUrlProducer nextPageUrlProducer = new NextPageUrlProducer(queue, pageLoader, pageUrlParser, promotionURL);
        NextPageUrlConsumer nextPageUrlConsumer = new NextPageUrlConsumer(queue, pageLoader, pageUrlParser);

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
