package converter;

import DTO.BookDetails;
import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Library;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parser.empik.EmpikGenreMapper;
import repositories.AuthorRepository;
import repositories.BookRepository;
import repositories.GenreRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class BookDetailsToBookAssemblerTest {

    private BookDetailsToBookAssembler assembler;

    @BeforeMethod
    public void setUp() {
        GenreRepository genreRepo = mock(GenreRepository.class);
        when(genreRepo.findByName(anyString())).thenReturn(new Genre("law"));

        AuthorRepository authorRepo = mock(AuthorRepository.class);
        when(authorRepo.findAll()).thenReturn(new ArrayList<>());

        BookRepository bookRepo = mock(BookRepository.class);
        when(bookRepo.findAll()).thenReturn(new ArrayList<>());

        assembler = new BookDetailsToBookAssembler();
        assembler.authorRepo = authorRepo;
        assembler.bookRepo = bookRepo;
        assembler.genreRepo = genreRepo;
        assembler.initialize(new EmpikGenreMapper(), new Library("EMPIK", ""));
    }

    @Test
    public void testAppropriateBookConversion() {
        // arrange
        Set<BookDetails> booksDetails = prepareBookDetails(true, false);

        // act
        Set<Book> convertedBooks = assembler.convert(booksDetails);

        // assert
        for (Book book : convertedBooks) {
            Assert.assertEquals(book.getTitle(), "Firma");
            Assert.assertEquals(book.getPrice(), "29,90 zl");
            Assert.assertEquals(book.getDiscount(), "30%");
            Assert.assertEquals(book.getDescription(), "opis");

            Set<Author> authors = book.getAuthors();
            for (Author author : authors) {
                Assert.assertEquals(author.getName(), "Grisham John");
            }

            Set<Genre> genres = book.getGenres();
            for (Genre genre : genres) {
                Assert.assertEquals(genre.getName(), "law");
            }
        }
    }

    @Test
    public void testInvalidTitleConversion() {
        // arrange
        Set<BookDetails> booksDetails = prepareBookDetails(false, false);

        // act
        Set<Book> convertedBooks = assembler.convert(booksDetails);

        // assert
        for (Book book : convertedBooks) {
            Assert.assertEquals(book.getTitle(), "");
        }
    }

    @Test
    public void testTooLongPriceConversion() {
        // arrange
        Set<BookDetails> booksDetails = prepareBookDetails(false, true);

        // act
        Set<Book> convertedBooks = assembler.convert(booksDetails);

        // assert
        for (Book book : convertedBooks) {
            Assert.assertEquals(book.getPrice().length(), 256);
        }
    }

    private Set<BookDetails> prepareBookDetails(boolean isValid, boolean isTooLongPrice) {
        Set<BookDetails> bookDetails = new HashSet<>();


        Set<String> authors = new HashSet<>();
        authors.add("Grisham John");
        BookDetails.BookDetailsBuilder builder = BookDetails.builder().description("opis").authors(authors).price("29,90 zl").percentageDiscount("30%").genre("Prawo");

        if (isValid) {
            builder.title("Firma");
        } else {
            builder.title(null);
        }

        if (isTooLongPrice) {
            builder.price("29,9000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 zl");
        } else {
            builder.price("29,90 zl");
        }

        bookDetails.add(builder.build());
        return bookDetails;
    }

}