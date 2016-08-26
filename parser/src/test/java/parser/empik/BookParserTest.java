package parser.empik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.DTO.Book;
import parser.IBookParser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class BookParserTest {

    @Test
    public void testOfParsingHtmlDocumentToBook() throws Exception {
        String htmlPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/empik/sampleBookDetails.html").toURI())));

        Document document = Jsoup.parse(htmlPage);

        IBookParser bookParser = new BookParser();
        Book book = bookParser.parse(document);
        SoftAssert sf = new SoftAssert();

        sf.assertEquals("Uwikłanie", book.getTitle());
        sf.assertEquals("Miłoszewski Zygmunt", book.getAuthor());
        sf.assertEquals("19,19 zł", book.getPrice());
        sf.assertEquals("40%", book.getPercentageDiscount());
        sf.assertEquals("Kryminał", book.getGenre());
        sf.assertEquals("Nagroda Wielkiego Kalibru dla najlepszej polskiej powieści kryminalnej i sensacyjnej roku 2007 Teodora Szackiego, warszawskiego prokuratora, łatwo rozpoznać na miejscu zbrodni. Wysoki, szczupły, w zbyt dobrym jak na urzędnika garniturze, o młodej twarzy, z którą kontrastują zupełnie siwe włosy. Stoi trochę z boku, wściekły, że znów kogoś zamordowano po siedemnastej, dokonując zamachu na jego poukładane życie rodzinne lub – zależnie od dnia – przeszkadzając mu w nieudolnym flircie, który może to poukładane życie doszczętnie zburzyć. W chłodną niedzielę 5 czerwca 2005 roku Szacki rozpoczyna nowe śledztwo. W klasztorze,w centrum miasta, zamordowano jednego z uczestników niekonwencjonalnej terapii grupowej, w czasie której pacjenci wcielali się w role swoich bliskich. Przypadkowe zabójstwo podczas włamania? Taka jest oficjalna wersja, gdyż Szackiemu trudno uwierzyć w hipotezę, że sprawcą zbrodni jest któryś z uczestników terapii. A jeśli tak, to dlaczego? Czy motywu należy szukać w nich samych, czy w osobach, które odgrywali podczas terapii? Każde przesłuchanie dostarcza informacji jeszcze bardziej wikłających sprawę. Prokurator ma nadzieję, że dokładne zbadanie przeszłości denata – osoby pozornie nieciekawej i bezbarwnej – pozwoli wykryć przyczynę zabójstwa i znaleźć sprawcę. Są jednak tajemnice, których nie odkrywa się bezkarnie – rodzinne tajemnice strzeżone przez siły potężniejsze niż rodzina. Jerzy Pilch o \"Uwikłaniu\" „Ukazało się Uwikłanie – doskonały polski kryminał współczesny. Tak jest: w literaturze polskiej pojawił się nie lada majster: Zygmunt Miloszewski – pisarz rasowy.” „Miloszewski potrafi dać napięcie nawet w scenie przeglądania starych gazet” „pokazała się bardzo dobra, polska, współczesna powieść kryminalna”", book.getDescription());
        sf.assertAll();
    }


}