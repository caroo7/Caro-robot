package parser.publio;

import dto.BookDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.IBookParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class PublioBookParserTest {

    @Test
    public void testOfParsingHtmlDocumentToBook() throws Exception {

        //arrange
        String htmlPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/publio/07_zgłasza_się.html").toURI())));
        Document document = Jsoup.parse(htmlPage);
        document.setBaseUri("http://www.publio.pl");
        IBookParser bookParser = new PublioBookParser();

        Set<String> expectedAuthors=new HashSet<>();
        expectedAuthors.add("Piotrowski Piotr");

        //act
        BookDetails book = bookParser.parse(Optional.of(document));

        //assert
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(book.getTitle(),"07 zgłasza się");
        sf.assertEquals(book.getAuthors(),expectedAuthors);
        sf.assertEquals(book.getPrice(),"12,80 zł");
        sf.assertEquals(book.getPercentageDiscount(),"50%");
        sf.assertEquals(book.getGenre(),"Kultura, sztuka, media");
        sf.assertEquals(book.getDescription(),"Przygody porucznika Borewicza wciąż przyciągają przed telewizory miliony Polaków. Gdzie tkwi sekret fenomenalnej popularności serialu „07 zgłoś się”? „07 zgłasza się” to możliwie pełna wersja historii produkcji do dziś chętnie oglądanej przez miliony Polaków i cały czas zdobywającej nowych widzów. Kilkadziesiąt osób z zakamarków pamięci wydobywa zabawne, dramatyczne lub zwyczajnie ciekawe szczegóły na temat swojej pracy w serialu, którego popularność trwa już czwarte dziesięciolecie. Większość z nich na początku rozmowy mówiła: „To było tak dawno... Nie pamiętam”. Dlatego między innymi był to ostatni moment na napisanie takiej książki, w której znalazło się również miejsce na historię Milicji Obywatelskiej i odnotowanie istotnych epizodów z dziejów naszego kraju. Smaczkiem dla wszystkich wielbicieli przygód porucznika będzie „Londyńska misja Borewicza” – opowiadanie, które jest próbą rekonstrukcji wydarzeń z udziałem tytułowego bohatera, poprzedzających jego pojawienie się w pierwszym odcinku serialu.");
        sf.assertTrue(book.getTags().contains("Promocja"));
        sf.assertEquals(book.getUrl(),"http://www.publio.pl");
        sf.assertEquals(book.getCoverUrl(),"http://www.publio.pl/files/product/card/85/31/fd/87959-07-zglasza-sie-piotr-piotrowski-1.jpg");
        sf.assertAll();
    }


}