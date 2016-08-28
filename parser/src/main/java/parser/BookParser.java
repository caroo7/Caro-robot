package parser;

import org.jsoup.nodes.Document;
import parser.DTO.Book;

/**
 * Created by Grzesiek on 2016-08-28.
 */
public abstract class BookParser implements IBookParser {

    protected abstract String getTitle(Document document);
    protected abstract String getAuthor(Document document);
    protected abstract String getPrice(Document document);
    protected abstract String getPercentageDiscount(Document document);
    protected abstract String getGenre(Document document);
    protected abstract String getDescription(Document document);

    public Book parse(Document document) {
        Book book=new Book();
        book.setTitle(getTitle(document));
        book.setAuthor(getAuthor(document));
        book.setPrice(getPrice(document));
        book.setPercentageDiscount(getPercentageDiscount(document));
        book.setGenre(getGenre(document));
        book.setDescription(getDescription(document));
        return book;
    }

}
