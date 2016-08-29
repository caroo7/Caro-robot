package parser.empik;

import org.jsoup.nodes.Document;
import parser.UrlParser;

import java.util.List;
import java.util.Optional;

/**
 * Created by grzegorz_sledz on 24.08.16.
 */
class EmpikUrlParser extends UrlParser{


    List<String> getLinksToGenreDetailsPromotion(Optional<Document> document) {
        return getLinksList(document, "a.more_lnk");
    }

    List<String> getLinksToBooksDetails(Optional<Document> document) {
        return getLinksList(document, "a.productBox-450Pic");
    }

    List<String> getLinksToNextPages(Optional<Document> document) {
        return getLinksList(document, "div.pageNumerationBox > a");
    }

}
