package parser.publio;

import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * This thread is needed for consuming url to next pages to produce urls to books details
 */
@Log4j2
class NextPageUrlConsumer implements Runnable {


    BlockingQueue<String> urlToNextPageQueue;
    PublioUrlParser publioUrlParser;
    PageLoader pageLoader;
    List<String> bookDetailsUrls;

    public NextPageUrlConsumer(BlockingQueue urlToNextPageQueue,PageLoader pageLoader) {
        this.urlToNextPageQueue = urlToNextPageQueue;
        this.pageLoader = pageLoader;
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
        log.debug("start thread");

        while (true) {
            try {
                String url = urlToNextPageQueue.take();
                log.debug("from urlToNextPageQueue " + url);

                if ("DONE".equals(url)) {
                    break;
                }

                bookDetailsUrls.addAll(getListToBookDetails(url));
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
        log.debug("exit thread");
    }

    List<String> getBookDetailsUrls() {
        return this.bookDetailsUrls;
    }
}
