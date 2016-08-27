package parser.empik;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.PageLoader;
import parser.ParserUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 24.08.16.
 */
class UrlParser {

    private List<String> getLinksList(Document document, String cssQuery, String hostUrl) {
        Elements element = document.select(cssQuery);
        return element.stream().map(e -> hostUrl + e.attr("href")).filter(s -> !s.contains("#")).distinct().collect(Collectors.toList());
    }

    List<String> getLinksToGenreDetailsPromotion( Document document) {
        return getLinksList(document, "a.more_lnk", ParserUtils.getHostUrlFromDocument(document));
    }

    List<String> getLinksToBooksDetails(Document document) {
        return getLinksList(document, "a.productBox-450Pic", ParserUtils.getHostUrlFromDocument(document));
    }

    List<String> getLinksToNextPages( Document document) {
        return getLinksList(document, "div.pageNumerationBox > a", document.baseUri());
    }

}
