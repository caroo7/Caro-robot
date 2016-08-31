package parser.publio;

import parser.PageLoader;
import parser.PromotionLibrary;
import parser.empik.*;

public class PublioPromotionLibrary extends PromotionLibrary {

    public PublioPromotionLibrary(PageLoader pageLoader) {
        super(new PublioUrlCrawler(pageLoader),new PublioBookCrawler(pageLoader));
    }

}
