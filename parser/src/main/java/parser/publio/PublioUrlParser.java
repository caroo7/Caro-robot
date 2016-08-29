package parser.publio;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.UrlParser;

import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class PublioUrlParser extends UrlParser {

    String getLinkToNextPage(Optional<Document> document) {
        Element element=document.get().select("div.pages-list > a.page--next").first();
        if (element!=null){
            return element.absUrl("href");
        }
       return null;
    }

    List<String> getLinksToBooksDetails(Optional<Document> document) {
        return getLinksList(document, "h3.product-tile-title > a");
    }

}
