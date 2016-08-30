package parser.publio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * This thread is needed for consuming url to next pages to produce urls to books details
 */
class NextPageUrlConsumer implements Runnable {

    private static final Logger logger = LogManager.getRootLogger();

    BlockingQueue<String> urlToNextPageQueue;
    PublioUrlParser publioUrlParser;
    PageLoader pageLoader;
    List<String> bookDetailsUrls;

    public NextPageUrlConsumer(BlockingQueue urlToNextPageQueue) {
        this.urlToNextPageQueue = urlToNextPageQueue;
        this.pageLoader = new PageLoader();
        this.publioUrlParser = new PublioUrlParser();
        bookDetailsUrls = new ArrayList<>();
    }

    List<String> getListToBookDetails(String page) {
        List<String> pages = new ArrayList<>();
        Optional<Document> document = pageLoader.getPage(page);
        pages.addAll(publioUrlParser.getLinksToBooksDetails(document));
        return pages;
    }

    @Override
    public void run() {
        logger.debug("start thread");

        while (true) {
            try {
                String url = urlToNextPageQueue.take();
                logger.debug("from urlToNextPageQueue " + url);

                if ("DONE".equals(url)) {
                    break;
                }

                bookDetailsUrls.addAll(getListToBookDetails(url));
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        logger.debug("exit thread");
    }

    List<String> getBookDetailsUrls() {
        return this.bookDetailsUrls;
    }
}
