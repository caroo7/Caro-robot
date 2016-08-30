package parser.publio;

import parser.PageLoader;
import parser.PromotionLibrary;
import parser.empik.*;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class PublioPromotionLibrary extends PromotionLibrary {

    public PublioPromotionLibrary(PageLoader pageLoader) {
        super(new PublioUrlCrawler(pageLoader),new PublioBookCrawler(pageLoader));
    }

}
