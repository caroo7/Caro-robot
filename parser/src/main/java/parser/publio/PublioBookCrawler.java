package parser.publio;

import parser.BookCrawler;
import parser.PageLoader;

class PublioBookCrawler extends BookCrawler {

    public PublioBookCrawler(PageLoader pageLoader) {
        super(pageLoader, new PublioBookParser());
    }
}
