package parser.helion;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.PageLoader;
import parser.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelionUrlCrawlerTest {

    @Test
    public void testPrepareLinksToAllBooks() throws IOException, URISyntaxException {
        //arrange
        PageLoader pageLoader = mock(PageLoader.class);

        HelionUrlCrawler helionUrlCrawler = new HelionUrlCrawler(pageLoader);

        when(pageLoader.getPage(HelionUrlCrawler.PROMOTION_URL)).thenReturn(Utils.loadHtmlDocument("/helion/book_list_p1.html", HelionUrlCrawler.MAIN_URL));
        when(pageLoader.getPage("http://helion.pl/kategorie/ebooki/2?ceny=-&formaty=p,em&jezyk=&wydawca=")).thenReturn(Utils.loadHtmlDocument("/helion/book_list_p2.html", HelionUrlCrawler.MAIN_URL));

        List<String> expectedResult = Utils.loadFileByLineToList("/helion/urlsToBookDetails.txt");


        //act
        List<String> result = helionUrlCrawler.getLinksToAllBooks();

        //assert
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(result.size(), expectedResult.size());
        sf.assertTrue(result.containsAll(expectedResult));
        sf.assertAll();
    }

}