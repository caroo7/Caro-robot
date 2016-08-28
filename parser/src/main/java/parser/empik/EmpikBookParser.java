package parser.empik;

import org.jsoup.nodes.Document;
import parser.BookParser;
import parser.utils.ParserUtils;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
class EmpikBookParser extends BookParser {

    protected String getTitle(Document document) {
        return document.select("h1.productMainTitle > span").first().text();
    }

    protected String getAuthor(Document document) {
        return document.select("span.pDAuthorList > a").first().text();
    }

    protected String getPrice(Document document) {
        return document.select("span.currentPrice").first().text();
    }

    protected String getPercentageDiscount(Document document) {
        //12,80 zł (40%)
        String priceWithDiscount = document.select("span.saving").first().text();
        return ParserUtils.extractDataFromRegex("\\((.*?)\\)", priceWithDiscount);
    }

    protected String getGenre(Document document) {
        return document.select("div.productLocalizer > a").last().text();
    }

    protected String getDescription(Document document) {
        return document.select("div.contentPacketText").first().text();
    }


}
