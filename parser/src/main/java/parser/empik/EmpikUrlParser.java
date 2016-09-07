package parser.empik;

import org.jsoup.nodes.Document;
import parser.IPageUrlParser;

import java.util.List;
import java.util.Optional;

class EmpikUrlParser implements IPageUrlParser{

    @Override
    public List<String> getLinksToBooksDetails(Optional<Document> document) {
        return getLinksList(document, "a.productBox-450Pic");
    }

    @Override
    public String getLinkToNextPage(Optional<Document> document) {
        return null;
    }

    List<String> getLinksToGenreDetailsPromotion(Optional<Document> document) {
        return getLinksList(document, "a.more_lnk");
    }

    public List<String> getLinksToNextPages(Optional<Document> document) {
        return getLinksList(document, "div.pageNumerationBox > a");
    }

}
