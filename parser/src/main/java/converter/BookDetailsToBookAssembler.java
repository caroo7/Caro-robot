package converter;

import DTO.BookDetails;
import entities.*;
import lombok.extern.log4j.Log4j2;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.AuthorRepository;
import repositories.BookRepository;
import repositories.GenreRepository;
import repositories.TagRepository;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Responsible for converting book DTO retrieved from web parser to book entity which will be saved on database
 */
@Log4j2
public class BookDetailsToBookAssembler {

    private static final int STANDARD_FIELD_LENGTH = 256;

    private static final int STANDARD_DESCRIPTION_LENGTH = 10000;

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    GenreRepository genreRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    TagRepository tagRepo;

    private final BookFieldPreparator preparator = new BookFieldPreparator();

    private Mapper genreMapper;

    private Mapper tagMapper;

    private List<Author> authorsCache;

    private List<Book> booksCache;

    private Library library;

    public void initialize(Mapper genreMapper, Mapper tagMapper, Library library) {
        this.genreMapper = genreMapper;
        this.tagMapper = tagMapper;
        this.library = library;
        authorsCache = authorRepo.findAll();
        booksCache = bookRepo.findAll();
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
        Timestamp timestamp = new Timestamp(System.nanoTime());

        Set<Author> authors = retrieveAuthors(bookDetails);
        Set<Genre> genres = retrieveGenres(bookDetails);
        Set<Tag> tags = retrieveTags(bookDetails);

        String url=bookDetails.getUrl();
        String coverUrl=bookDetails.getCoverUrl();

        Book book = Book.builder().title(title).authors(authors).description(description).
                discount(discount).price(price).timestamp(timestamp).genres(genres).tags(tags).library(library).url(url).coverUrl(coverUrl).build();

        if (!booksCache.contains(book)) {
            log.debug("Book will be saved on database: " + book);
            return book;
        }

        return retrieveBookFromCache(book);
    }

    private Book retrieveBookFromCache(Book book) {
        int indexInCache = booksCache.indexOf(book);
        book = booksCache.get(indexInCache);
        book.setTimestamp(new Timestamp(System.nanoTime()));
        return book;
    }

    private Set<Author> retrieveAuthors(BookDetails bookDetails) {
        Set<String> authorsString = bookDetails.getAuthors();

        return authorsString.stream().map(s -> {
            Author author = new Author(s);
            if (!this.authorsCache.contains(author)) {
                authorRepo.save(author);
                log.debug("Author will be saved on database: " + author);
                this.authorsCache.add(author);
            } else {
                int indexInCache = authorsCache.indexOf(author);
                author = this.authorsCache.get(indexInCache);
            }
            return author;
        }).collect(Collectors.toSet());
    }

    private Set<Genre> retrieveGenres(BookDetails bookDetails) {
        Set<Genre> genres = new HashSet<>();
        String genreString = genreMapper.getMap().get(bookDetails.getGenre());
        Genre genre = genreRepo.findByName(genreString);
        genres.add(genre != null ? genre : genreRepo.findByName("no category"));
        return genres;
    }

    private Set<Tag> retrieveTags(BookDetails bookDetails) {
        return bookDetails.getTags().stream().map(s -> tagRepo.findByName(tagMapper.getMap().get(s)))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}