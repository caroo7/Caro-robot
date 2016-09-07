package parser.empik;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.PageLoader;
import parser.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class EmpikUrlCrawlerTest {

    private final String MAIN_URL="http://www.empik.com";
    private final String PROMOTION_URL=MAIN_URL+"/ebooki/promocje";

    @Test
    public void testPrepareLinksToAllBooks() throws IOException, URISyntaxException {
        //arrange
        PageLoader pageLoader = mock(PageLoader.class);
        EmpikUrlCrawler empikUrlCrawler = new EmpikUrlCrawler(pageLoader);
        String genreUrl = MAIN_URL+"/ebooki/kryminaly-i-sensacje";
        when(pageLoader.getPage(PROMOTION_URL)).thenReturn(Utils.loadHtmlDocument("/empik/ebook_promotions_main.html", MAIN_URL));
        when(pageLoader.getPage(genreUrl)).thenReturn(Utils.loadHtmlDocument("/empik/genre_promotion_details.html", MAIN_URL));


        when(pageLoader.getPage(genreUrl)).thenReturn(Utils.loadHtmlDocument("/empik/book_list_p1.html", genreUrl));
        when(pageLoader.getPage(genreUrl + "?productPoolId=74791&hideUnavailable=true&start=41&sort=scoreDesc")).thenReturn(Utils.loadHtmlDocument("/empik/book_list_p2.html", genreUrl));
        when(pageLoader.getPage(genreUrl + "?productPoolId=74791&hideUnavailable=true&start=81&sort=scoreDesc")).thenReturn(Utils.loadHtmlDocument("/empik/book_list_p3.html", genreUrl));
        when(pageLoader.getPage(genreUrl + "?productPoolId=74791&hideUnavailable=true&start=121&sort=scoreDesc")).thenReturn(Utils.loadHtmlDocument("/empik/book_list_p4.html", genreUrl));

        List<String> expectedResult = Utils.loadFileByLineToList("/empik/urlsToBookDetails.txt");
        //act
        List<String> result = empikUrlCrawler.getLinksToAllBooks();

        //assert
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(result.size(), expectedResult.size());
        sf.assertTrue(result.containsAll(expectedResult));
        sf.assertAll();
    }


}