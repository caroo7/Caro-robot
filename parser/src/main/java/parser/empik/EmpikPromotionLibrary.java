package parser.empik;

import parser.PageLoader;
import parser.PromotionLibrary;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class EmpikPromotionLibrary extends PromotionLibrary{
    public EmpikPromotionLibrary(PageLoader pageLoader) {
        super(new EmpikUrlCrawler(pageLoader),new EmpikBookCrawler(pageLoader));
    }

}
