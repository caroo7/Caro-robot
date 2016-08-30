package parser.publio;

import org.jsoup.nodes.Document;
import parser.IBookParser;
import parser.utils.ParserUtils;

import java.util.Optional;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class PublioBookParser implements IBookParser {

    @Override
    public String getTitle(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("h1.title").first().text();
        }
        return null;
    }

    @Override
    public String getAuthor(Optional<Document> document) {
        if (document.isPresent()) {
            return document.get().select("div.product-detail-value > a").first().text();
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

    @Override
    public String getPercentageDiscount(Optional<Document> document) {
        if (document.isPresent()) {
            //25,90 zł
            String priceWithoutDiscount = document.get().select("del.product-card-price-old").first().text();
            String priceAfterDiscount = getPrice(document);

            priceWithoutDiscount = ParserUtils.extractDataFromRegex("^([0-9]+[,]?[0-9]?).*", priceWithoutDiscount).replaceAll(",", ".");
            priceAfterDiscount = ParserUtils.extractDataFromRegex("^([0-9]+[,]?[0-9]?).*", priceAfterDiscount).replaceAll(",", ".");

            return ParserUtils.calculatePercentageDiscount( new Float(priceWithoutDiscount),new Float(priceAfterDiscount));
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

}
