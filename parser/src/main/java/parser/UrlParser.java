package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Grzesiek on 2016-08-28.
 */
public abstract class UrlParser {

    protected final Logger logger = LogManager.getRootLogger();


    protected List<String> getLinksList(Optional<Document> document, String cssQuery) {
        if(document.isPresent()) {
            logger.debug("cssQuery: '" + cssQuery + "' document: " + document.get().baseUri());

            Elements element = document.get().select(cssQuery);
            return element.stream().map(e -> e.absUrl("href")).filter(s -> !s.contains("#")).distinct().collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
