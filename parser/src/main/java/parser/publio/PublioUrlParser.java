package parser.publio;

import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.UrlParser;

import java.util.List;
import java.util.Optional;

@Log4j2
public class PublioUrlParser extends UrlParser {

    String getLinkToNextPage(Optional<Document> document) {

        if(document.isPresent()) {
            log.debug("document: " + document.get().baseUri());

            Element element = document.get().select("div.pages-list > a.page--next").first();
            if (element != null) {
                return element.absUrl("href");
            }
        }
        return null;
    }

    List<String> getLinksToBooksDetails(Optional<Document> document) {
        return getLinksList(document, "h3.product-tile-title > a");
    }

}
