package parser;

import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Grzesiek on 2016-08-28.
 */
@Log4j2
public abstract class UrlParser {

    protected List<String> getLinksList(Optional<Document> document, String cssQuery) {
        if(document.isPresent()) {
            log.debug("cssQuery: '" + cssQuery + "' document: " + document.get().baseUri());

            Elements element = document.get().select(cssQuery);
            return element.stream().map(e -> e.absUrl("href")).filter(s -> !s.contains("#")).distinct().collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
