package parser.publio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.DTO.Book;
import parser.IBookParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static org.testng.Assert.*;

/**
 * Created by grzegorz_sledz on 30.08.16.
 */
public class PublioBookParserTest {

    @Test
    public void testOfParsingHtmlDocumentToBook() throws Exception {

        //arrange
        String htmlPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/publio/07_zgłasza_się.html").toURI())));
        Document document = Jsoup.parse(htmlPage);

        IBookParser bookParser = new PublioBookParser();
        //act
        Book book = bookParser.parse(Optional.of(document));

        //assert
        SoftAssert sf = new SoftAssert();
        sf.assertEquals("07 zgłasza się", book.getTitle());
        sf.assertEquals("Piotr Piotrowski", book.getAuthor());
        sf.assertEquals("12,80 zł", book.getPrice());
        sf.assertEquals("50%", book.getPercentageDiscount());
        sf.assertEquals("Kultura, sztuka, media", book.getGenre());
        sf.assertEquals("Przygody porucznika Borewicza wciąż przyciągają przed telewizory miliony Polaków. Gdzie tkwi sekret fenomenalnej popularności serialu „07 zgłoś się”? „07 zgłasza się” to możliwie pełna wersja historii produkcji do dziś chętnie oglądanej przez miliony Polaków i cały czas zdobywającej nowych widzów. Kilkadziesiąt osób z zakamarków pamięci wydobywa zabawne, dramatyczne lub zwyczajnie ciekawe szczegóły na temat swojej pracy w serialu, którego popularność trwa już czwarte dziesięciolecie. Większość z nich na początku rozmowy mówiła: „To było tak dawno... Nie pamiętam”. Dlatego między innymi był to ostatni moment na napisanie takiej książki, w której znalazło się również miejsce na historię Milicji Obywatelskiej i odnotowanie istotnych epizodów z dziejów naszego kraju. Smaczkiem dla wszystkich wielbicieli przygód porucznika będzie „Londyńska misja Borewicza” – opowiadanie, które jest próbą rekonstrukcji wydarzeń z udziałem tytułowego bohatera, poprzedzających jego pojawienie się w pierwszym odcinku serialu.", book.getDescription());
        sf.assertTrue(book.getTags().contains("Promocja"));
        sf.assertAll();
    }


}