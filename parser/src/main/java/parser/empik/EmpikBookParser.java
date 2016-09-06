package parser.empik;

import org.jsoup.nodes.Document;
import parser.IBookParser;
import parser.utils.ParserUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class EmpikBookParser implements IBookParser {

    @Override
     public String getTitle(Optional<Document> document) {
        if(document.isPresent()){
            return document.get().select("h1.productMainTitle > span").first().text();
        }
        return null;
    }

    @Override
    public Set<String> getAuthors(Optional<Document> document) {
        if (document.isPresent()) {
            return  document.get().select("span.pDAuthorList > a").stream().map(element -> element.text()).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
    @Override
    public String getPrice(Optional<Document> document) {
        if(document.isPresent()) {
            return document.get().select("span.currentPrice").first().text();
        }
        return null;
    }

    /**
     * Percentage discount on web page is presented as format: 12,80 zł (40%)
     * The percentage value is exctrated by ParserUtils
     * @param document
     * @return
     */
    @Override
    public String getPercentageDiscount(Optional<Document> document) {
        if(document.isPresent()) {
            String priceWithDiscount = document.get().select("span.saving").first().text();
            return ParserUtils.extractDataFromRegex("\\((.*?)\\)", priceWithDiscount);
        }
        return null;
    }
    @Override
    public String getGenre(Optional<Document> document) {
        if(document.isPresent()) {
            // Ebooki
            return document.get().select("div.productLocalizer > a").get(3).text();
        }
        return null;
    }
    @Override
    public String getDescription(Optional<Document> document) {
        if(document.isPresent()) {
            return document.get().select("div.contentPacketText").first().text();
        }
        return null;
    }

    @Override
    public Set<String> getTags(Optional<Document> document) {
        return Collections.emptySet();
    }

    @Override
    public String getUrl(Optional<Document> document) {
        if(document.isPresent()) {
            return document.get().baseUri();
        }
        return null;
    }

    @Override
    public String getCoverUrl(Optional<Document> document) {
        if(document.isPresent()) {
            return document.get().select("div.productMainPic > a > img ").first().attributes().get("src");
        }
        return null;
    }

}
