package parser.empik;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parser.UrlParser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 24.08.16.
 */
class EmpikUrlParser extends UrlParser{


    List<String> getLinksToGenreDetailsPromotion( Document document) {
        return getLinksList(document, "a.more_lnk");
    }

    List<String> getLinksToBooksDetails(Document document) {
        return getLinksList(document, "a.productBox-450Pic");
    }

    List<String> getLinksToNextPages( Document document) {
        return getLinksList(document, "div.pageNumerationBox > a");
    }

}
