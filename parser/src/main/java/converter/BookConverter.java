package converter;

import DTO.BookDetails;
import entities.Book;
import entities.Genre;
import mapper.EmpikGenreMapper;
import repositories.GenreRepository;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BookConverter {

    private static final int STANDARD_FIELD_LENGTH = 256;

    private static final int STANDARD_DESCRIPTION_LENGTH = 10000;

    private BookValidator validator = new BookValidator();

    private EmpikGenreMapper genreMapper = new EmpikGenreMapper();

    private GenreRepository genreRepo;

    public Set<Book> convert(Set<BookDetails> booksDetails) {
        return booksDetails.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    private Book convert(BookDetails bookDetails) {
        String title = validator.validateField(bookDetails.getTitle(), STANDARD_FIELD_LENGTH);
        String authors = validator.validateField(bookDetails.getAuthor(), STANDARD_FIELD_LENGTH);
        String description = validator.validateField(bookDetails.getDescription(), STANDARD_DESCRIPTION_LENGTH);
        String discount = validator.validateField(bookDetails.getPercentageDiscount(), STANDARD_FIELD_LENGTH);
        String price = validator.validateField(bookDetails.getPrice(), STANDARD_FIELD_LENGTH);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Set<Genre> genres = new HashSet<>();
        String genreString = genreMapper.getGenresMap().get(bookDetails.getGenre());
        Genre genre = genreRepo.findByName(genreString);
        genres.add(genre != null ? genre : genreRepo.findByName("no category"));

        return Book.builder().title(title).authors(authors).description(description).
                discount(discount).price(price).timestamp(timestamp).genres(genres).build();
    }

    public void setGenreRepo(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }
}
