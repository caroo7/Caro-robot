package parser.helion;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.IBookParser;
import parser.utils.ParserUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class HelionBookParser implements IBookParser {

    @Override
    public String getTitle(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("div.title-group > h1").first().text();
        }
        return null;
    }

    @Override
    public Set<String> getAuthors(Optional<Document> document) {
        if (document.isPresent()) {
            return Stream.of(document.get().select("div.author-group > dl.author > dd").first().text().split(",")).map(s -> ParserUtils.moveLastWordOnBeginning(s.trim())).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public String getPrice(Optional<Document> document) {
        if (document.isPresent()) {
            Element element = document.get().select("fieldset#box_ebook > p.book-price > ins").first();
            if (element != null) {
                return element.text();
            }
            return "0 zł";
        }
        return null;
    }

    /**
     * Price has format 25,90 zł. For extract some data ParserUtils is used.
     *
     * @param document
     * @return
     */
    @Override
    public String getPercentageDiscount(Optional<Document> document) {
        if (document.isPresent()) {

            Element element = document.get().select("fieldset#box_ebook > p.book-price > del").first();
            if (element != null) {
                String priceWithoutDiscount = document.get().select("fieldset#box_ebook > p.book-price > del").first().text();
                String priceAfterDiscount = getPrice(document);

                priceWithoutDiscount = ParserUtils.extractDataFromRegex("^([0-9]+[,]?[0-9]?).*", priceWithoutDiscount).replaceAll(",", ".");
                priceAfterDiscount = ParserUtils.extractDataFromRegex("^([0-9]+[,]?[0-9]?).*", priceAfterDiscount).replaceAll(",", ".");

                return ParserUtils.calculatePercentageDiscount(new Float(priceWithoutDiscount), new Float(priceAfterDiscount));
            }
            return "100 %";

        }
        return null;
    }

    @Override
    public String getGenre(Optional<Document> document) {
        if (document.isPresent()) {
            Elements elements = document.get().select("div.breadcrumb > nav > ul > li > a");
            if (elements.size() > 1) {
                return elements.get(1).text().replaceAll("» ", "");
            }
        }
        return null;

    }

    @Override
    public String getDescription(Optional<Document> document) {
        if (document.isPresent()) {
            Elements elements = document.get().select("div.center-body-center");
            elements.select("audio").remove();
            elements.select("strong").remove();

            return elements.stream().map(Element::text).reduce(String::concat).get();
        }
        return null;
    }

    @Override
    public Set<String> getTags(Optional<Document> document) {
        if (document.isPresent()) {
            Set<String> tags = new HashSet<>();
            Elements tagsElements = document.get().select("ul.tags > li");
            return tagsElements.stream().filter(e -> !e.text().contains("#")).map(element -> element.text().replaceAll("Przejdź", "").trim()).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public String getUrl(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().baseUri();
        }
        return null;
    }

    @Override
    public String getCoverUrl(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("div.book-details > div > div img").first().attr("abs:src");
        }
        return null;
    }

}
