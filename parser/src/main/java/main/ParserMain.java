package main;

import DTO.BookDetails;
import config.DatabaseConfiguration;
import converter.BookDetailsToBookAssembler;
import entities.Book;
import library.LibraryMapContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parser.PromotionLibrary;
import repositories.BookRepository;
import repositories.GenreRepository;
import repositories.LibraryRepository;

import java.util.Set;

public class ParserMain {

    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        logger.info("Parser application starts");

        if(args.length == 0) {
            logger.error("No library name provided!");
            System.exit(-1);
        }

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository repo = ctx.getBean(BookRepository.class);
        GenreRepository genreRepo = ctx.getBean(GenreRepository.class);
        LibraryRepository libraryRepo = ctx.getBean(LibraryRepository.class);

        LibraryMapContainer libraryMapper = new LibraryMapContainer();
        libraryMapper.initialize(libraryRepo);

        PromotionLibrary actualPromotionLibrary = libraryMapper.getLibrary(args[0]);
        if(actualPromotionLibrary == null) {
            logger.error("Invalid library name!");
            System.exit(-1);
        }
        Set<BookDetails> booksDetails = actualPromotionLibrary.collect();

        BookDetailsToBookAssembler converter = new BookDetailsToBookAssembler(actualPromotionLibrary.getGenreMapper(), genreRepo);
        Set<Book> books = converter.convert(booksDetails);

        repo.save(books);

        logger.info("Parser finish his work.");
    }

}