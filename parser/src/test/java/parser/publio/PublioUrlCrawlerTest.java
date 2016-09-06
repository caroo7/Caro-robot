package parser.publio;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.PageLoader;
import parser.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublioUrlCrawlerTest {

    private final String MAIN_URL="http://www.publio.pl";
    private final String PROMOTION_URL=MAIN_URL+"/szukaj,promocja.html?sections=EBOOK";

    @Test
    public void testPrepareLinksToAllBooks() throws IOException, URISyntaxException {
        //arrange
        PageLoader pageLoader = mock(PageLoader.class);

        PublioUrlCrawler publioUrlCrawler = new PublioUrlCrawler(pageLoader);

        when(pageLoader.getPage(PROMOTION_URL)).thenReturn(Utils.loadHtmlDocument("/publio/book_list_p1.html", MAIN_URL));
        when(pageLoader.getPage(MAIN_URL+"/szukaj,strona2,promocja.html?sections=EBOOK")).thenReturn(Utils.loadHtmlDocument("/publio/book_list_p2.html", MAIN_URL));

        List<String> expectedResult = Utils.loadFileByLineToList("/publio/urlsToBookDetails.txt");

        //act
        List<String> result = publioUrlCrawler.getLinksToAllBooks();

        //assert
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(result.size(), expectedResult.size());
        sf.assertTrue(result.containsAll(expectedResult));
        sf.assertAll();
    }
}