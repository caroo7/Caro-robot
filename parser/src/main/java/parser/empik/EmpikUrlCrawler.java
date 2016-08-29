package parser.empik;

import org.jsoup.nodes.Document;
import parser.IUrlCrawler;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 26.08.16.
 */
public class EmpikUrlCrawler implements IUrlCrawler {

    final String mainUrl = "http://www.empik.com";
    final String promotionUrl =mainUrl+ "/ebooki/promocje";

    private final PageLoader pageLoader;
    private final EmpikUrlParser empikUrlParser;

    public EmpikUrlCrawler(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
        this.empikUrlParser = new EmpikUrlParser();
    }

    List<String> getUrlsToPagesWithBooksList(List<String> genrePageUrls) {
        List<String> bookListUrls = new ArrayList<>();

        bookListUrls.addAll(genrePageUrls);
        bookListUrls.addAll(genrePageUrls.stream().map(s -> {
            Optional<Document> doc = pageLoader.getPage(s);
            return empikUrlParser.getLinksToNextPages(doc);
        }).flatMap(strings -> strings.stream()).collect(Collectors.toList()));

        return bookListUrls;
    }

    List<String> getUrlsToBookDetails(List<String> bookListUrls) {
        List<String> bookDetailsUrls = bookListUrls.stream().map(s -> {
            Optional<Document> doc = pageLoader.getPage(s);
            return empikUrlParser.getLinksToBooksDetails(doc);
        }).flatMap(strings -> strings.stream()).collect(Collectors.toList());
        return bookDetailsUrls;
    }

    public List<String> getLinksToAllBooks() {
        Optional<Document> promotionPage = pageLoader.getPage(promotionUrl);
        List<String> genrePageUrls = empikUrlParser.getLinksToGenreDetailsPromotion(promotionPage);
        List<String> bookListUrls = getUrlsToPagesWithBooksList(genrePageUrls);
        List<String> bookDetailsUrls = getUrlsToBookDetails(bookListUrls);

        return bookDetailsUrls;
    }

}
