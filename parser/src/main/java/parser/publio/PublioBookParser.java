package parser.publio;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parser.IBookParser;
import parser.utils.ParserUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PublioBookParser implements IBookParser {

    @Override
    public String getTitle(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("h1.title").first().text();
        }
        return null;
    }

    @Override
    public Set<String> getAuthors(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("div.product-detail-value > a").stream().filter(element -> element.getElementsByAttributeValueMatching("data-seo-id", "authors").size() != 0).map(element -> ParserUtils.moveLastWordOnBeginning(element.attr("title"))).collect(Collectors.toSet());
        }
        return null;
    }


    @Override
    public String getPrice(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("ins.product-card-price-promotion").first().text();
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

            String priceWithoutDiscount = document.get().select("del.product-card-price-old").first().text();
            String priceAfterDiscount = getPrice(document);

            priceWithoutDiscount = ParserUtils.extractDataFromRegex("^([0-9]+[,]?[0-9]?).*", priceWithoutDiscount).replaceAll(",", ".");
            priceAfterDiscount = ParserUtils.extractDataFromRegex("^([0-9]+[,]?[0-9]?).*", priceAfterDiscount).replaceAll(",", ".");

            return ParserUtils.calculatePercentageDiscount(new Float(priceWithoutDiscount), new Float(priceAfterDiscount));
        }
        return null;
    }

    @Override
    public String getGenre(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("ul.breadcrumb > li.breadcrumb-item").get(1).text();
        }
        return null;

    }

    @Override
    public String getDescription(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("div.product-card-block-content").first().text();
        }
        return null;
    }

    @Override
    public Set<String> getTags(Optional<Document> document) {
        if (!document.isPresent()) {
            return new HashSet<>();
        }

        Elements tagsElements = document.get().select("div.product-card-labels-info > a");
        return tagsElements.stream().map(element -> element.text()).collect(Collectors.toSet());

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
            return document.get().select("div.product-card-cover > a > img").first().attr("abs:src");
        }
        return null;
    }


}
