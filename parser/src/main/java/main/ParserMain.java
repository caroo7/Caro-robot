package main;

import config.DatabaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parser.DTO.Book;
import parser.PageLoader;
import parser.PromotionLibrary;
import parser.publio.PublioPromotionLibrary;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParserMain {

    public static void main(String[] args) {

        Instant first = Instant.now();

        long startTime = System.currentTimeMillis();
        PromotionLibrary publio=new PublioPromotionLibrary(new PageLoader());
        List<Book> books=publio.collect();
        books.stream().forEach(book -> System.out.println(book));

        System.out.println(books.size());
        Instant second = Instant.now();
        Duration duration = Duration.between(first, second);
        System.out.println(duration.getSeconds()+" "+duration.toMinutes());

//        new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
    }

}