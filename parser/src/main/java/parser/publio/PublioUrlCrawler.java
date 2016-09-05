package parser.publio;

import parser.UrlCrawler;
import parser.PageLoader;

class PublioUrlCrawler extends UrlCrawler {

    static final String MAIN_URL = "http://www.publio.pl";
    static final String PROMOTION_URL = MAIN_URL + "/szukaj,promocja.html?sections=EBOOK";

    PublioUrlCrawler(PageLoader pageLoader) {
        super(MAIN_URL,PROMOTION_URL,pageLoader,new PublioUrlParser());
    }

}
