package parser.helion;

import parser.BookCrawler;
import parser.PageLoader;


class HelionBookCrawler extends BookCrawler {

    HelionBookCrawler(PageLoader pageLoader) {
        super(pageLoader, new HelionBookParser());
    }
}
