package converter;

import DTO.BookDetails;
import entities.Author;
import entities.Book;
import entities.Genre;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parser.empik.EmpikGenreMapper;
import repositories.AuthorRepository;
import repositories.GenreRepository;

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
        when(authorRepo.findAuthor(anyString())).thenReturn(new Author("Grisham John"));

        assembler = new BookDetailsToBookAssembler(new EmpikGenreMapper(), genreRepo, authorRepo);
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

        BookDetails details = new BookDetails();

        if (isValid) {
            details.setTitle("Firma");
        } else {
            details.setTitle(null);
        }

        details.setDescription("opis");
        details.setAuthor("Grisham John");
        details.setPrice("29,90 zl");
        details.setPercentageDiscount("30%");
        details.setGenre("Prawo");

        if (isTooLongPrice) {
            details.setPrice("29,9000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 zl");
        } else {
            details.setPrice("29,90 zl");
        }

        bookDetails.add(details);
        return bookDetails;
    }

}