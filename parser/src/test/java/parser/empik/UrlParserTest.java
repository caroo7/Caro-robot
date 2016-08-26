package parser.empik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class UrlParserTest {

    public static final String mainPageUrl = "http://www.empik.com";
    public static final String promotionUrl = mainPageUrl + "/ebooki/promocje";

    private UrlParser urlParser;

    @BeforeTest
    public void beforemethod(){
        this.urlParser = new UrlParser();
    }

    private Document loadHtmlDocument(String path) throws URISyntaxException, IOException {
        String htmlPage = new String(Files.readAllBytes(Paths.get(getClass().getResource(path).toURI())));
        Document document = Jsoup.parse(htmlPage);
        return document;
    }

    private List<String> loadFileByLineToList(String path) throws URISyntaxException {
        Path fileName = Paths.get(getClass().getResource(path).toURI());

        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(fileName)) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Test
    public void getLinksToGenreDetailsTest() throws Exception {

        //arrange
        Document document=loadHtmlDocument("/empik/bookPromotionPage.html");

        List<String> expectedUrls = new ArrayList<>();
        expectedUrls.add("http://www.empik.com/ebooki/kryminaly-i-sensacje");
        expectedUrls.add("http://www.empik.com/ebooki/gwf");

        //act
        List<String> results = urlParser.getLinksToGenreDetailsPromotion(mainPageUrl,document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(results.size(),expectedUrls.size());
        sf.assertTrue(results.containsAll(expectedUrls));
        sf.assertAll();
    }



    @Test
    public void getLinksToBooksDetailsTest() throws Exception {

        //arrange
        Document document=loadHtmlDocument("/empik/booksOnPromotion_p1.html");
        List<String> expectedResult= loadFileByLineToList("/empik/bookDeatailsUrls.txt");

        //act
        List<String> results= urlParser.getLinksToBooksDetails(mainPageUrl,document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(results.size(),expectedResult.size());
        sf.assertTrue(results.containsAll(expectedResult));
        sf.assertAll();
    }

    @Test
    public void getLinksToNextPagesTest() throws Exception {

        //arrange
        Document document=loadHtmlDocument("/empik/booksOnPromotion_p1.html");

        List<String> expectedResults=new ArrayList<>();
        expectedResults.add("?productPoolId=74791&hideUnavailable=true&start=41&sort=scoreDesc");
        expectedResults.add("?productPoolId=74791&hideUnavailable=true&start=81&sort=scoreDesc");
        expectedResults.add("?productPoolId=74791&hideUnavailable=true&start=121&sort=scoreDesc");

        //act
        List<String> pagesUrl= urlParser.getLinksToNextPages("", document);

        //assert
        SoftAssert sf=new SoftAssert();
        sf.assertEquals(pagesUrl.size(),expectedResults.size());
        sf.assertTrue(pagesUrl.containsAll(expectedResults));
        sf.assertAll();
    }

}