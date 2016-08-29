package parser.empik;

import org.jsoup.nodes.Document;
import parser.IUrlCrawler;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 26.08.16.
 */
public class EmpikUrlCrawler implements IUrlCrawler {

    final String MAIN_URL = "http://www.empik.com";
    final String PROMOTION_URL = MAIN_URL + "/ebooki/promocje";

    private final PageLoader pageLoader;
    private final EmpikUrlParser empikUrlParser;

    EmpikUrlCrawler(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
        this.empikUrlParser = new EmpikUrlParser();
    }

    private List<String> getUrlsToPagesWithBooksList(List<String> genrePageUrls) {
        List<String> bookListUrls = new ArrayList<>();

        bookListUrls.addAll(genrePageUrls);
        bookListUrls.addAll(genrePageUrls.stream().map(s -> {
            Optional<Document> doc = pageLoader.getPage(s);
            return empikUrlParser.getLinksToNextPages(doc);
        }).flatMap(Collection::stream).collect(Collectors.toList()));

        return bookListUrls;
    }

    private List<String> getUrlsToBookDetails(List<String> bookListUrls) {
        return bookListUrls.stream().map(s -> {
            Optional<Document> doc = pageLoader.getPage(s);
            return empikUrlParser.getLinksToBooksDetails(doc);
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<String> getLinksToAllBooks() {
        Optional<Document> promotionPage = pageLoader.getPage(PROMOTION_URL);
        List<String> genrePageUrls = empikUrlParser.getLinksToGenreDetailsPromotion(promotionPage);
        List<String> bookListUrls = getUrlsToPagesWithBooksList(genrePageUrls);

        return getUrlsToBookDetails(bookListUrls);
    }

}
