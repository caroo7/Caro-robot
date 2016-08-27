package parser.empik;

import org.jsoup.nodes.Document;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 26.08.16.
 */
public class UrlCrawler {

    final String mainUrl = "http://www.empik.com";
    final String promotionUrl =mainUrl+ "/ebooki/promocje";

    private final PageLoader pageLoader;
    private final UrlParser urlParser;

    public UrlCrawler(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
        this.urlParser = new UrlParser();
    }

    List<String> getUrlsToPagesWithBooksList(List<String> genrePageUrls) {
        List<String> bookListUrls = new ArrayList<>();

        bookListUrls.addAll(genrePageUrls);
        bookListUrls.addAll(genrePageUrls.stream().map(s -> {
            Document doc = pageLoader.getPage(s);
            return urlParser.getLinksToNextPages(doc);
        }).flatMap(strings -> strings.stream()).collect(Collectors.toList()));

        return bookListUrls;
    }

    List<String> getUrlsToBookDetails(List<String> bookListUrls) {
        List<String> bookDetailsUrls = bookListUrls.stream().map(s -> {
            Document doc = pageLoader.getPage(s);
            return urlParser.getLinksToBooksDetails(doc);
        }).flatMap(strings -> strings.stream()).collect(Collectors.toList());
        return bookDetailsUrls;
    }

    public List<String> prepareLinksToAllBooks() {
        Document promotionPage = pageLoader.getPage(promotionUrl);
        List<String> genrePageUrls = urlParser.getLinksToGenreDetailsPromotion(promotionPage);
        List<String> bookListUrls = getUrlsToPagesWithBooksList(genrePageUrls);
        List<String> bookDetailsUrls = getUrlsToBookDetails(bookListUrls);

        return bookDetailsUrls;
    }

}
