package parser.publio;

import parser.UrlCrawler;
import parser.PageLoader;

class PublioUrlCrawler extends UrlCrawler {

    PublioUrlCrawler(PageLoader pageLoader) {
        super("http://www.publio.pl/szukaj,promocja.html?sections=EBOOK",pageLoader,new PublioUrlParser());
    }

}
