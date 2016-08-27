package parser.empik;

import org.jsoup.nodes.Document;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class UrlParserTest {

    private final String mainPageUrl = "http://www.empik.com";

    private UrlParser urlParser;

    @BeforeTest
    public void beforeTest(){
        this.urlParser = new UrlParser();
    }

    @Test
    public void getLinksToGenreDetailsTest() throws Exception {

        //arrange
        Document document=Utils.loadHtmlDocument("/empik/ebook_promotions_main.html",mainPageUrl);

        List<String> expectedUrls = new ArrayList<>();
        expectedUrls.add("http://www.empik.com/ebooki/kryminaly-i-sensacje");
//        expectedUrls.add("http://www.empik.com/ebooki/gwf");

        //act
        List<String> results = urlParser.getLinksToGenreDetailsPromotion(document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(results.size(),expectedUrls.size());
        sf.assertTrue(results.containsAll(expectedUrls));
        sf.assertAll();
    }

    @Test
    public void getLinksToBooksDetailsTest() throws IOException, URISyntaxException {
        //arrange
        Document document=Utils.loadHtmlDocument("/empik/book_list_p1.html",mainPageUrl);
        List<String> expectedResult= Utils.loadFileByLineToList("/empik/book_list_p1_urls.txt");

        //act
        List<String> results= urlParser.getLinksToBooksDetails(document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(results.size(),expectedResult.size());
        sf.assertTrue(results.containsAll(expectedResult));
        sf.assertAll();
    }

    @Test
    public void getLinksToNextPagesTest() throws Exception {

        //arrange
        Document document=Utils.loadHtmlDocument("/empik/genre_promotion_details.html","http://www.empik.com/ebooki/kryminaly-i-sensacje");

        List<String> expectedResults=new ArrayList<>();
        expectedResults.add("http://www.empik.com/ebooki/kryminaly-i-sensacje?productPoolId=74791&hideUnavailable=true&start=41&sort=scoreDesc");
        expectedResults.add("http://www.empik.com/ebooki/kryminaly-i-sensacje?productPoolId=74791&hideUnavailable=true&start=81&sort=scoreDesc");
        expectedResults.add("http://www.empik.com/ebooki/kryminaly-i-sensacje?productPoolId=74791&hideUnavailable=true&start=121&sort=scoreDesc");

        //act
        List<String> pagesUrl= urlParser.getLinksToNextPages(document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(pagesUrl.size(),expectedResults.size());
        sf.assertTrue(pagesUrl.containsAll(expectedResults));
        sf.assertAll();
    }

}