package parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Grzesiek on 2016-08-28.
 */
public abstract class UrlParser {

    protected List<String> getLinksList(Document document, String cssQuery) {
        Elements element = document.select(cssQuery);
        return element.stream().map(e -> e.absUrl("href")).filter(s -> !s.contains("#")).distinct().collect(Collectors.toList());
    }
}
