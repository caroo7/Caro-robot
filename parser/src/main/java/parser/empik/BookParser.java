package parser.empik;

import org.jsoup.nodes.Document;
import parser.DTO.Book;
import parser.IBookParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
class BookParser implements IBookParser{

    private String getBookTitle(Document document){
       return document.select("h1.productMainTitle > span").first().text();
    }

    private String getAuthor(Document document){
        return document.select("span.pDAuthorList > a").first().text();
    }

    private String getPrice(Document document){
        return document.select("span.currentPrice").first().text();
    }

    private String getPercentageDiscount(Document document){
        //12,80 zł (40%)
        String priceWithDiscount=document.select("span.saving").first().text();

        Pattern patter = Pattern.compile("\\((.*?)\\)");
        Matcher matchPattern = patter.matcher(priceWithDiscount);

        String percentageDiscount="";
        while(matchPattern.find()) {
            percentageDiscount=matchPattern.group(1);
        }
        return percentageDiscount;
    }

    private String getGenre(Document document){
        return document.select("div.productLocalizer > a").last().text();
    }

    private String getDescription(Document document){
        return document.select("div.contentPacketText").first().text();
    }


    public Book parse(Document document) {
        Book book=new Book();
        book.setTitle(getBookTitle(document));
        book.setAuthor(getAuthor(document));
        book.setPrice(getPrice(document));
        book.setPercentageDiscount(getPercentageDiscount(document));
        book.setGenre(getGenre(document));
        book.setDescription(getDescription(document));
        return book;
    }

}
