package web.controller;

import entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repositories.BookRepository;

import java.util.List;

/**
 * Created by bartlomiej on 26.08.16.
 */

@Controller
public class YggdrasilController {

    @Autowired
    private BookRepository repo;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        List<Book> books = repo.findAll();
        //books.forEach(b -> System.out.println(b.getTitle()));
        model.addObject("books", books);
        return model;
    }
}
