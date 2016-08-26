package main;

import parser.DTO.Book;
import parser.empik.BookCrawler;

import java.util.List;

public class ParserMain {

    public static void main(String[] args)  {
        //Empik
        BookCrawler empik=new BookCrawler();
        List<Book> empikBooks=empik.getBooks();

        for(Book book: empikBooks){
            System.out.println(book);
        }
        System.out.println(empikBooks.size());
    }

}