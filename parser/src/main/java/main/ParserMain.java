package main;

import DTO.BookDetails;
import config.DatabaseConfiguration;
import converter.BookConverter;
import entities.Book;
import mapper.EmpikGenreMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parser.PageLoader;
import parser.PromotionLibrary;
import parser.empik.EmpikPromotionLibrary;
import repositories.BookRepository;
import repositories.GenreRepository;

import java.util.Set;

public class ParserMain {

    public static void main(String[] args) {
        PromotionLibrary empikLibrary = new EmpikPromotionLibrary(new PageLoader());
        Set<BookDetails> empikBooks = empikLibrary.collect();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository repo = ctx.getBean(BookRepository.class);
        GenreRepository genreRepo = ctx.getBean(GenreRepository.class);

        BookConverter converter = new BookConverter(new EmpikGenreMapper(), genreRepo);
        Set<Book> books = converter.convert(empikBooks);

        repo.save(books);
    }

}