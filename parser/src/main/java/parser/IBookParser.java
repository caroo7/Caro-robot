package parser;

import DTO.BookDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.Optional;
import java.util.Set;


public interface IBookParser {

    Logger logger = LogManager.getRootLogger();


    String getTitle(Optional<Document> document);

    //String in set contains surname and name ex. Wroński Marcin
    Set<String> getAuthors(Optional<Document> document);

    String getPrice(Optional<Document> document);

    String getPercentageDiscount(Optional<Document> document);

    String getGenre(Optional<Document> document);

    String getDescription(Optional<Document> document);

    Set<String> getTags(Optional<Document> document);

    String getUrl(Optional<Document> document);

    String getCoverUrl(Optional<Document> document);


    default BookDetails parse(Optional<Document> document) {

        BookDetails book = BookDetails.builder().authors(getAuthors(document)).title(getTitle(document))
                .price(getPrice(document)).percentageDiscount(getPercentageDiscount(document))
                .genre(getGenre(document)).description(getDescription(document)).tags(getTags(document))
                .url(getUrl(document)).coverUrl(getCoverUrl(document)).build();

        logger.debug(book);
        return book;
    }

}
