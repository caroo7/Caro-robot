package parser.publio;

import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import parser.PageLoader;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * This thread is needed for getting url addresses to next pages by using ">>" button
 * Publio page is really slow so it is need to produce url next pages and this result is
 * consume by NextPageUrlConsumer which produce url to book details
 */
@Log4j2
class NextPageUrlProducer implements Runnable {


    private BlockingQueue urlToNextPageQueue;
    private String startUrl;
    private PublioUrlParser publioUrlParser;
    private PageLoader pageLoader;

    public NextPageUrlProducer(BlockingQueue urlToNextPageQueue, PageLoader pageLoader,String startUrl) {
        this.urlToNextPageQueue = urlToNextPageQueue;
        this.startUrl=startUrl;
        this.publioUrlParser=new PublioUrlParser();
        this.pageLoader=pageLoader;
    }

    @Override
    public void run() {
        log.debug("start thread");

        Optional<Document> document = pageLoader.getPage(startUrl);
        String nextPage= publioUrlParser.getLinkToNextPage(document);

        try {
            log.debug("putting to urlToNextPageQueue "+startUrl);
            urlToNextPageQueue.put(startUrl);

            while (nextPage!=null){
                log.debug("putting to urlToNextPageQueue "+nextPage);
                urlToNextPageQueue.put(nextPage);

                document = pageLoader.getPage(nextPage);
                nextPage = publioUrlParser.getLinkToNextPage(document);
            }
            urlToNextPageQueue.put("DONE");

            log.debug("finished work - thread exit - putting DONE to urlToNextPageQueue");
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}