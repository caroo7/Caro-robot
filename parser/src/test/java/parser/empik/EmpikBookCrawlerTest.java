package parser.empik;

import dto.BookDetails;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.PageLoader;
import parser.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmpikBookCrawlerTest {

    private EmpikBookCrawler bookCrawler;
    private final String bookDetailsUrl="http://www.empik.com/uwiklanie-miloszewski-zygmunt,p1046125476,ebooki-i-mp3-p";


    @BeforeTest
    public void beforeTest() throws IOException, URISyntaxException {
        PageLoader pageLoader = mock(PageLoader.class);
        bookCrawler=new EmpikBookCrawler(pageLoader);
        when(pageLoader.getPage(bookDetailsUrl)).thenReturn(Utils.loadHtmlDocument("/empik/uwiklanie-miloszewski-zygmunt.html", "http://www.empik.com"));
    }
    @Test
    public void testGetBooks() throws Exception {
        //arrange
        List<String> urlsToBookDetails= Collections.singletonList(bookDetailsUrl);

        Set<String>authors=new HashSet<>();
        authors.add("Miłoszewski Zygmunt");

        BookDetails expectedBook=BookDetails.builder().authors(authors).title("Uwikłanie").price("19,19 zł").percentageDiscount("40%").genre("Literatura").description("Nagroda Wielkiego Kalibru dla najlepszej polskiej powieści kryminalnej i sensacyjnej roku 2007 Teodora Szackiego, warszawskiego prokuratora, łatwo rozpoznać na miejscu zbrodni. Wysoki, szczupły, w zbyt dobrym jak na urzędnika garniturze, o młodej twarzy, z którą kontrastują zupełnie siwe włosy. Stoi trochę z boku, wściekły, że znów kogoś zamordowano po siedemnastej, dokonując zamachu na jego poukładane życie rodzinne lub – zależnie od dnia – przeszkadzając mu w nieudolnym flircie, który może to poukładane życie doszczętnie zburzyć. W chłodną niedzielę 5 czerwca 2005 roku Szacki rozpoczyna nowe śledztwo. W klasztorze,w centrum miasta, zamordowano jednego z uczestników niekonwencjonalnej terapii grupowej, w czasie której pacjenci wcielali się w role swoich bliskich. Przypadkowe zabójstwo podczas włamania? Taka jest oficjalna wersja, gdyż Szackiemu trudno uwierzyć w hipotezę, że sprawcą zbrodni jest któryś z uczestników terapii. A jeśli tak, to dlaczego? Czy motywu należy szukać w nich samych, czy w osobach, które odgrywali podczas terapii? Każde przesłuchanie dostarcza informacji jeszcze bardziej wikłających sprawę. Prokurator ma nadzieję, że dokładne zbadanie przeszłości denata – osoby pozornie nieciekawej i bezbarwnej – pozwoli wykryć przyczynę zabójstwa i znaleźć sprawcę. Są jednak tajemnice, których nie odkrywa się bezkarnie – rodzinne tajemnice strzeżone przez siły potężniejsze niż rodzina. Jerzy Pilch o \"Uwikłaniu\" „Ukazało się Uwikłanie – doskonały polski kryminał współczesny. Tak jest: w literaturze polskiej pojawił się nie lada majster: Zygmunt Miloszewski – pisarz rasowy.” „Miloszewski potrafi dać napięcie nawet w scenie przeglądania starych gazet” „pokazała się bardzo dobra, polska, współczesna powieść kryminalna”").build();

        //act
        Set<BookDetails> books=bookCrawler.getBooks(urlsToBookDetails);
        //assert
        for(BookDetails resultBook: books) {
            SoftAssert sf=new SoftAssert();
            sf.assertEquals(1,books.size());
            sf.assertEquals(resultBook.getAuthors(),expectedBook.getAuthors());
            sf.assertEquals(resultBook.getTitle(),expectedBook.getTitle());
            sf.assertEquals(resultBook.getPrice(),expectedBook.getPrice());
            sf.assertEquals(resultBook.getPercentageDiscount(),expectedBook.getPercentageDiscount());
            sf.assertEquals(resultBook.getGenre(),expectedBook.getGenre());
            sf.assertEquals(resultBook.getDescription(),expectedBook.getDescription());
            sf.assertAll();
        }
    }
}
