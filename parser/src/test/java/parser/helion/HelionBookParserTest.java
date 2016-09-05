package parser.helion;

import DTO.BookDetails;
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


public class HelionBookParserTest {
    @Test
    public void testOfParsingHtmlDocumentToBook() throws Exception {

        //arrange
        String htmlPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/helion/model-biznesowy.html").toURI())));
        Document document = Jsoup.parse(htmlPage);
        document.setBaseUri(HelionUrlCrawler.MAIN_URL);
        IBookParser bookParser = new HelionBookParser();

        Set<String> expectedAuthors=new HashSet<>();
        expectedAuthors.add("Pigneur Yves");
        expectedAuthors.add("Clark Timothy");
        expectedAuthors.add("Osterwalder Alexander");

        //act
        BookDetails book = bookParser.parse(Optional.of(document));

        //assert
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(book.getTitle(),"Model biznesowy. TY");
        sf.assertEquals(book.getAuthors(),expectedAuthors);
        sf.assertEquals(book.getPrice(),"47,20 zł");
        sf.assertEquals(book.getPercentageDiscount(),"20%");
        sf.assertEquals(book.getGenre(),"Biznes IT");
        sf.assertEquals(book.getDescription(),"Modelowanie własnej kariery. Jednostronicowe sposoby na życiową przemianę Timothy Clark, Alexander Osterwalder, Yves Pigneur twórcy międzynarodowego bestsellera Tworzenie modeli biznesowych. Podręcznik wizjonera Stworzony przez 328 profesjonalistów z całego świata Zawiera praktyczny kurs analizowania modeli biznesowych Opisuje innowacyjne metody modyfikacji Twojej kariery zawodowej   Osobisty model biznesowy to innowacyjne narzędzie komunikacji wizualnej, dzięki któremu łatwiej ocenisz, kim jesteś i co możesz robić.       Kryzys? Trudna sytuacja na rynku pracy? Kłopoty ze sprostaniem wygórowanym wymaganiom pracodawców? Ciebie to nie dotyczy! Zamień nerwowe wątpliwości na wyluzowaną pewność w swoim życiu zawodowym. Działaj jak najwięksi współcześni wizjonerzy i wypróbuj genialny sposób na optymalizację absolutnie najważniejszego - bo Twojego osobistego - modelu biznesowego. Tylko na tym kursie zdobędziesz umiejętności analizowania modeli biznesowych i myślenia przez ich pryzmat - umiejętności, które pozwolą Ci odzyskać kontrolę nad Twoim życiem. To prosta i skuteczna metoda kształtowania i rozwijania Twojej kariery zawodowej, dostosowana do realiów współczesnego rynku pracy oraz Twoich osobistych potrzeb. Gotowy na potrząśnięcie skostniałymi strukturami?     Tim Clark przewodzi ruchowi osobistego modelu biznesowego, skupionemu wokół strony BusinessModelYou.com. To utalentowany nauczyciel i trener, były przedsiębiorca, a także doktor nauk ekonomicznych. Clark jest autorem lub redaktorem pięciu książek poświęconych przedsiębiorczości, modelom biznesowym i rozwojowi osobistemu. Alexander Osterwalder jest przedsiębiorcą i prelegentem. Przemawia często na forum firm z listy \"Fortune 500\" i prowadzi gościnne wykłady na najbardziej szanowanych uczelniach wyższych, takich jak Wharton, Stanford, Berkeley, IESE czy IMD. Obronił doktorat na Uniwersytecie Lozańskim. Yves Pigneur jest profesorem systemów zarządzania informacjami na Uniwersytecie Lozańskim. Wykłada gościnnie na Uniwersytecie Stanu Georgia oraz Uniwersytecie Kolumbii Brytyjskiej. Jest redaktorem naczelnym czasopisma akademickiego \"Systemes d’Information et Management\". Doktorat obronił na Uniwersytecie w Namur w Belgii.");
        sf.assertTrue(book.getTags().contains("Promocja"));
        sf.assertEquals(book.getUrl(),"http://helion.pl");
        sf.assertEquals(book.getCoverUrl(),"https://static01.helion.com.pl/global/okladki/326x466/tymobi.jpg");
        sf.assertAll();
    }
}