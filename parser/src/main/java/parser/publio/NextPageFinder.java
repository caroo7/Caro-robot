package parser.publio;

import org.jsoup.nodes.Document;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Grzesiek on 2016-08-29.
 */
public class NextPageFinder implements Runnable {

    BlockingQueue queue;
    String startUrl;
    PublioUrlParser publioUrlParser;
    PageLoader pageLoader;

    public NextPageFinder(BlockingQueue queue,String startUrl) {
        this.queue = queue;
        this.startUrl=startUrl;
        this.publioUrlParser=new PublioUrlParser();
        this.pageLoader=new PageLoader();
    }

    @Override
    public void run() {
        System.out.println("NextPageFinder.run");
        Optional<Document> document = pageLoader.getPage(startUrl);
        String nextPage= publioUrlParser.getLinkToNextPage(document);
        try {
            System.out.println("start---->"+startUrl);
            queue.put(startUrl);
            while (nextPage!=null){
                System.out.println("produce: "+nextPage);
                queue.put(nextPage);
                document = pageLoader.getPage(nextPage);
                nextPage = publioUrlParser.getLinkToNextPage(document);
            }
            queue.put("DONE");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}