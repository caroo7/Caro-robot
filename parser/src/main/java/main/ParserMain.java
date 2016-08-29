package main;

import config.DatabaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ParserMain {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
    }

}