package parser.helion;

import lombok.extern.log4j.Log4j2;
import parser.PageLoader;
import parser.UrlCrawler;

@Log4j2
class HelionUrlCrawler extends UrlCrawler {

    HelionUrlCrawler(PageLoader pageLoader) {
        super("http://helion.pl","http://helion.pl/kategorie/ebooki?formaty=p,em&ceny=-&wydawca=&jezyk=",pageLoader,new HelionUrlParser());
    }
}
