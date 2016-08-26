package parser.empik;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.PageLoader;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 24.08.16.
 */
class UrlParser {

    private List<String> getLinksList(String prefixUrl,Elements aHrefElement) {
        return aHrefElement.stream().map(element ->prefixUrl+ element.attr("href")).filter(s -> !s.contains("#")).distinct().collect(Collectors.toList());
    }

    public List<String> getLinksToGenreDetailsPromotion(String prefixUrl,Document document){
        return getLinksList(prefixUrl,document.select("a.more_lnk" ));
    }

    public List<String> getLinksToBooksDetails(String prefixUrl,Document document){
        return getLinksList(prefixUrl,document.select( "a.productBox-450Pic"));
    }

    public List<String> getLinksToNextPages(String prefixUrl,Document document){
        return getLinksList(prefixUrl,document.select("div.pageNumerationBox > a"));
    }

}
