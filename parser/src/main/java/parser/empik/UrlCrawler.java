package parser.empik;

import org.jsoup.nodes.Document;
import parser.PageLoader;
import parser.empik.UrlParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzegorz_sledz on 26.08.16.
 */
class UrlCrawler {

    private final String mainPageUrl = "http://www.empik.com";
    private final String promotionUrl = mainPageUrl + "/ebooki/promocje";

    private final PageLoader pageLoader = new PageLoader();
    private final UrlParser urlParser =new UrlParser();


    List<String> prepareLinksToAllBooks() {

        List<String> booksDetailsUrl = new ArrayList<>();

        Document mainPageDoc = pageLoader.getPage(promotionUrl);

        mainPageDoc.setBaseUri(mainPageUrl);
        List<String> genrePageUrls = urlParser.getLinksToGenreDetailsPromotion(mainPageUrl,mainPageDoc);

        for (String genreUrl : genrePageUrls) {
            Document promotionPage = pageLoader.getPage(genreUrl);
            promotionPage.setBaseUri(genreUrl);
            List<String> genreNextPageUrls = urlParser.getLinksToNextPages(genreUrl,promotionPage);
            promotionPage.setBaseUri(mainPageUrl);
            booksDetailsUrl.addAll(urlParser.getLinksToBooksDetails(mainPageUrl,promotionPage));

            getUrlsForGenre(booksDetailsUrl, genreNextPageUrls);
        }
        return booksDetailsUrl;
    }

    private void getUrlsForGenre(List<String> booksDetailsUrl, List<String> genreNextPageUrls) {
        for (String genreNextPageUrl : genreNextPageUrls) {
            Document subPage = pageLoader.getPage(genreNextPageUrl);
            subPage.setBaseUri(mainPageUrl);
            booksDetailsUrl.addAll(urlParser.getLinksToBooksDetails(mainPageUrl,subPage));
        }
    }
}
