package parser.helion;

import parser.PageLoader;
import parser.PromotionLibrary;

public class HelionPromotionLibrary extends PromotionLibrary {

    public HelionPromotionLibrary(PageLoader pageLoader) {
        super(new HelionUrlCrawler(pageLoader),new HelionBookCrawler(pageLoader));
    }

}
