package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import parser.DTO.Book;

import java.util.Optional;
import java.util.Set;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public interface IBookParser {

    Logger logger = LogManager.getRootLogger();


    String getTitle(Optional<Document> document);

    String getAuthor(Optional<Document> document);

    String getPrice(Optional<Document> document);

    String getPercentageDiscount(Optional<Document> document);

    String getGenre(Optional<Document> document);

    String getDescription(Optional<Document> document);

    Set<String> getTags(Optional<Document> document);

    default Book parse(Optional<Document> document) {
        Book book = new Book();
        book.setTitle(getTitle(document));
        book.setAuthor(getAuthor(document));
        book.setPrice(getPrice(document));
        book.setPercentageDiscount(getPercentageDiscount(document));
        book.setGenre(getGenre(document));
        book.setDescription(getDescription(document));
        book.setTags(getTags(document));

        logger.debug(book);
        return book;
    }

}
