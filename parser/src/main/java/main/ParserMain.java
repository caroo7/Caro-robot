package main;

import DTO.BookDetails;
import config.DatabaseConfiguration;
import converter.BookDetailsToBookAssembler;
import entities.Book;
import library.LibraryMapContainer;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parser.PromotionLibrary;
import repositories.AuthorRepository;
import repositories.BookRepository;
import repositories.GenreRepository;
import repositories.LibraryRepository;

import java.util.Set;

@Log4j2
public class ParserMain {

    public static void main(String[] args) {
        log.info("Parser application starts");

        if(args.length == 0) {
            log.error("No library name provided!");
            System.exit(-1);
        }

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository bookRepo = ctx.getBean(BookRepository.class);
        GenreRepository genreRepo = ctx.getBean(GenreRepository.class);
        LibraryRepository libraryRepo = ctx.getBean(LibraryRepository.class);
        AuthorRepository authorRepo = ctx.getBean(AuthorRepository.class);

        LibraryMapContainer libraryMapper = new LibraryMapContainer();
        libraryMapper.initialize(libraryRepo);

        PromotionLibrary actualPromotionLibrary = libraryMapper.getLibrary(args[0]);
        if(actualPromotionLibrary == null) {
            log.error("Invalid library name!");
            System.exit(-1);
        }
        Set<BookDetails> booksDetails = actualPromotionLibrary.collect();

        BookDetailsToBookAssembler converter = new BookDetailsToBookAssembler(actualPromotionLibrary.getGenreMapper(), genreRepo, authorRepo,bookRepo);
        Set<Book> books = converter.convert(booksDetails);

        bookRepo.save(books);

        log.info("Parser finish his work.");
    }

}