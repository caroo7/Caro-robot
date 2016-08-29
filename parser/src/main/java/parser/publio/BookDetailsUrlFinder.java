package parser.publio;

import org.jsoup.nodes.Document;
import parser.BookCrawler;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Grzesiek on 2016-08-29.
 */
class BookDetailsUrlFinder implements Runnable {
    BlockingQueue<String> queue;
    PublioUrlParser publioUrlParser;
    PageLoader pageLoader;
    List<String> bookDetailsUrls;

    public BookDetailsUrlFinder(BlockingQueue queue) {
        this.queue = queue;
        this.pageLoader=new PageLoader();
        this.publioUrlParser=new PublioUrlParser();
        bookDetailsUrls=new ArrayList<>();
    }

    List<String> getListToBookDetails(String page) {
        List<String> pages = new ArrayList<>();
        Optional<Document> document=pageLoader.getPage(page);
        pages.addAll(publioUrlParser.getLinksToBooksDetails(document));
        System.out.println(pages.size());
        return pages;
    }

    @Override
    public void run() {
        System.out.println("BookDetailsUrlFinder.run");

        while (true) {
            try {
                String element = queue.take();
                if ("DONE".equals(element)) {
                    System.out.println("Exiting consumer thread, " +
                            "end of data reached.");
                    break;
                }
                bookDetailsUrls.addAll(getListToBookDetails(element));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    List<String> getBookDetailsUrls(){
        return this.bookDetailsUrls;
    }
}
