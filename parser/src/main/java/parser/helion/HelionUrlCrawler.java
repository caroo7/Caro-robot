package parser.helion;

import lombok.extern.log4j.Log4j2;
import parser.PageLoader;
import parser.UrlCrawler;

@Log4j2
class HelionUrlCrawler extends UrlCrawler {

    static final String MAIN_URL = "http://helion.pl";
    static final String PROMOTION_URL = MAIN_URL + "/kategorie/ebooki?formaty=p,em&ceny=-&wydawca=&jezyk=";

    HelionUrlCrawler(PageLoader pageLoader) {
        super(MAIN_URL,PROMOTION_URL,pageLoader,new HelionUrlParser());
    }
}
