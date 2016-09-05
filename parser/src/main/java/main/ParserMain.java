package main;

import DTO.BookDetails;
import cache.Cache;
import config.CacheConfiguration;
import config.DatabaseConfiguration;
import config.ParserConfiguration;
import converter.BookDetailsToBookAssembler;
import entities.Book;
import entities.Library;
import library.Libraries;
import library.LibraryMapContainer;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parser.PromotionLibrary;
import repositories.BookRepository;
import repositories.LibraryRepository;

import java.util.Set;

@Log4j2
public class ParserMain {

    public static void main(String[] args) {
        log.info("Parser application starts");

        if (Libraries.values().length == 0) {
            log.error("No library name provided!");
            System.exit(-1);
        }

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class, ParserConfiguration.class, CacheConfiguration.class);
        LibraryRepository libraryRepo = ctx.getBean(LibraryRepository.class);
        BookRepository bookRepo = ctx.getBean(BookRepository.class);

        LibraryMapContainer libraryMapper = new LibraryMapContainer();
        libraryMapper.initialize(libraryRepo);

        for(Libraries lib: Libraries.values()) {
            log.info("Start parser for " + lib.toString() + " library.");

            Library library = libraryRepo.findByName(lib.toString());
            PromotionLibrary actualPromotionLibrary = libraryMapper.getLibrary(library);

            if (actualPromotionLibrary == null) {
                log.error("Promotion library for name " + lib.toString() + " doesn't exist!");
                continue;
            }

            Set<BookDetails> booksDetails = actualPromotionLibrary.collect();
            BookDetailsToBookAssembler converter = ctx.getBean(BookDetailsToBookAssembler.class);
            converter.initialize(actualPromotionLibrary.getGenreMapper(), actualPromotionLibrary.getTagMapper(), library);
            Set<Book> books = converter.convert(booksDetails);

            bookRepo.save(books);

            Cache cache = ctx.getBean(Cache.class);
            cache.initializeCache(bookRepo, libraryRepo.findByName(lib.name()));
            log.info("End parser for " + lib.toString() + " library.");
        }

        log.info("Parser finish his work.");
    }

}