package parser.publio;

import parser.BookCrawler;
import parser.PageLoader;

/**
 * Created by Grzesiek on 2016-08-27.
 */
class PublioBookCrawler extends BookCrawler {

    public PublioBookCrawler(PageLoader pageLoader) {
        super(pageLoader, new PublioBookParser());
    }
}
