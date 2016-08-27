package main;

import parser.DTO.Book;
import parser.PageLoader;
import parser.empik.BookCrawler;
import parser.empik.UrlCrawler;

import java.util.List;

public class ParserMain {

    public static void main(String[] args)  {
        //Empik
        PageLoader pageLoader=new PageLoader();

        UrlCrawler urlCrawler=new UrlCrawler(pageLoader);
        BookCrawler empik=new BookCrawler(pageLoader);
        List<Book> empikBooks=empik.getBooks(urlCrawler.prepareLinksToAllBooks());

        for(Book book: empikBooks){
            System.out.println(book);
        }
        System.out.println(empikBooks.size());
    }

}