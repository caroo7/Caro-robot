package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import DTO.BookDetails;
import org.jsoup.nodes.Document;

import java.util.Optional;

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

    default BookDetails parse(Optional<Document> document) {
        BookDetails book = new BookDetails();
        book.setTitle(getTitle(document));
        book.setAuthor(getAuthor(document));
        book.setPrice(getPrice(document));
        book.setPercentageDiscount(getPercentageDiscount(document));
        book.setGenre(getGenre(document));
        book.setDescription(getDescription(document));

        logger.debug(book);
        return book;
    }

}
