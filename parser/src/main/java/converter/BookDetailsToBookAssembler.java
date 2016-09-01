package converter;

import DTO.BookDetails;
import entities.Author;
import entities.Book;
import entities.Genre;
import mapper.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repositories.AuthorRepository;
import repositories.BookRepository;
import repositories.GenreRepository;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Responsible for converting book DTO retrieved from web parser to book entity which will be saved on database
 */
public class BookDetailsToBookAssembler {

    private static final Logger logger = LogManager.getRootLogger();

    private static final int STANDARD_FIELD_LENGTH = 256;

    private static final int STANDARD_DESCRIPTION_LENGTH = 10000;

    private final BookFieldPreparator preparator = new BookFieldPreparator();

    private final Mapper genreMapper;

    private final GenreRepository genreRepo;

    private final AuthorRepository authorRepo;

    private final BookRepository bookRepo;

    private List<Author> authorsCache;
    private List<Book> booksCache;

    public BookDetailsToBookAssembler(Mapper genreMapper, GenreRepository genreRepo, AuthorRepository authorRepo,BookRepository bookRepo) {
        this.genreMapper = genreMapper;
        this.genreRepo = genreRepo;
        this.bookRepo=bookRepo;
        this.authorRepo = authorRepo;
        this.authorsCache=authorRepo.findAll();
        this.booksCache=bookRepo.findAll();
    }

    public Set<Book> convert(Set<BookDetails> booksDetails) {
        return booksDetails.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    private Book convert(BookDetails bookDetails) {
        String title = preparator.validateField(bookDetails.getTitle(), STANDARD_FIELD_LENGTH);
        String description = preparator.validateField(bookDetails.getDescription(), STANDARD_DESCRIPTION_LENGTH);
        String discount = preparator.validateField(bookDetails.getPercentageDiscount(), STANDARD_FIELD_LENGTH);
        String price = preparator.validateField(bookDetails.getPrice(), STANDARD_FIELD_LENGTH);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Set<Author> authors = retrieveAuthors(bookDetails);
        Set<Genre> genres = retrieveGenres(bookDetails);

        Book book=Book.builder().title(title).authors(authors).description(description).
                discount(discount).price(price).timestamp(timestamp).genres(genres).build();
        if(booksCache.contains(book)){
            int indexInCache=booksCache.indexOf(book);
            book=booksCache.get(indexInCache);
        }

        return book;
    }

    private Set<Author> retrieveAuthors(BookDetails bookDetails) {
        Set<String> authorsString = bookDetails.getAuthors();

        return authorsString.stream().map(s -> {
            Author author=new Author(s);
            if (!this.authorsCache.contains(author)) {
                authorRepo.save(author);
                this.authorsCache.add(author);
            }else{
                int indexInCache=authorsCache.indexOf(author);
                author=this.authorsCache.get(indexInCache);
            }
            return author;
        }).collect(Collectors.toSet());
    }

    private Set<Genre> retrieveGenres(BookDetails bookDetails) {
        Set<Genre> genres = new HashSet<>();
        String genreString = genreMapper.getGenresMap().get(bookDetails.getGenre());
        Genre genre = genreRepo.findByName(genreString);
        genres.add(genre != null ? genre : genreRepo.findByName("no category"));
        return genres;
    }

}