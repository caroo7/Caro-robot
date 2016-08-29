package parser.publio;

import org.jsoup.nodes.Document;
import parser.IUrlCrawler;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class PublioUrlCrawler implements IUrlCrawler {

    final String mainUrl = "http://www.publio.pl";
    final String promotionUrl = mainUrl + "/szukaj,promocja.html?sections=EBOOK";

    private final PageLoader pageLoader;
    private final PublioUrlParser publioUrlParser;

    public PublioUrlCrawler(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
        this.publioUrlParser = new PublioUrlParser();
    }

    public List<String> getLinksToAllBooks() {
        BlockingQueue queue = new ArrayBlockingQueue<>(32);
        NextPageFinder producer = new NextPageFinder(queue,promotionUrl);
        BookDetailsUrlFinder consumer = new BookDetailsUrlFinder(queue);

        Thread nextPageThread=new Thread(producer);

        Thread bookDetailsThread= new Thread(consumer);
        nextPageThread.start();
        bookDetailsThread.start();
        try {
            bookDetailsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return consumer.getBookDetailsUrls();
    }
}
