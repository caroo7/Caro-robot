package parser.empik;

import org.jsoup.nodes.Document;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpikUrlParserTest {

    private final String mainPageUrl = "http://www.empik.com";

    private EmpikUrlParser empikUrlParser;

    @BeforeTest
    public void beforeTest(){
        this.empikUrlParser = new EmpikUrlParser();
    }

    @Test
    public void getLinksToGenreDetailsTest() throws Exception {

        //arrange
        Optional<Document> document= Utils.loadHtmlDocument("/empik/ebook_promotions_main.html",mainPageUrl);

        List<String> expectedUrls = new ArrayList<>();
        expectedUrls.add("http://www.empik.com/ebooki/kryminaly-i-sensacje");

        //act
        List<String> results = empikUrlParser.getLinksToGenreDetailsPromotion(document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(results.size(),expectedUrls.size());
        sf.assertTrue(results.containsAll(expectedUrls));
        sf.assertAll();
    }

    @Test
    public void getLinksToBooksDetailsTest() throws IOException, URISyntaxException {
        //arrange
        Optional<Document>  document=Utils.loadHtmlDocument("/empik/book_list_p1.html",mainPageUrl);
        List<String> expectedResult= Utils.loadFileByLineToList("/empik/book_list_p1_urls.txt");

        //act
        List<String> results= empikUrlParser.getLinksToBooksDetails(document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(results.size(),expectedResult.size());
        sf.assertTrue(results.containsAll(expectedResult));
        sf.assertAll();
    }

    @Test
    public void getLinksToNextPagesTest() throws Exception {

        //arrange
        Optional<Document>  document=Utils.loadHtmlDocument("/empik/genre_promotion_details.html","http://www.empik.com/ebooki/kryminaly-i-sensacje");

        List<String> expectedResults=new ArrayList<>();
        expectedResults.add("http://www.empik.com/ebooki/kryminaly-i-sensacje?productPoolId=74791&hideUnavailable=true&start=41&sort=scoreDesc");
        expectedResults.add("http://www.empik.com/ebooki/kryminaly-i-sensacje?productPoolId=74791&hideUnavailable=true&start=81&sort=scoreDesc");
        expectedResults.add("http://www.empik.com/ebooki/kryminaly-i-sensacje?productPoolId=74791&hideUnavailable=true&start=121&sort=scoreDesc");

        //act
        List<String> pagesUrl= empikUrlParser.getLinksToNextPages(document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(pagesUrl.size(),expectedResults.size());
        sf.assertTrue(pagesUrl.containsAll(expectedResults));
        sf.assertAll();
    }

}