package parser.empik;

import org.jsoup.nodes.Document;
import parser.UrlCrawler;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class EmpikUrlCrawler extends UrlCrawler {

    private final EmpikUrlParser empikUrlParser;

    EmpikUrlCrawler(PageLoader pageLoader) {
        super("http://www.empik.com","http://www.empik.com/ebooki/promocje");
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

    @Override
    public List<String> getLinksToAllBooks() {
        Optional<Document> promotionPage = pageLoader.getPage(promotionURL);
        List<String> genrePageUrls = empikUrlParser.getLinksToGenreDetailsPromotion(promotionPage);
        List<String> bookListUrls = getUrlsToPagesWithBooksList(genrePageUrls);

        return getUrlsToBookDetails(bookListUrls);
    }

}
