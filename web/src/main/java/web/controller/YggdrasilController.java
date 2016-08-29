package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repositories.BookRepository;

/**
 * Created by bartlomiej on 26.08.16.
 */

@Controller
public class YggdrasilController {

    @Autowired
    private BookRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
         ModelAndView model = new ModelAndView();
         return model;
    }

    @RequestMapping(value="/books")
    public ModelAndView booksList(){
        ModelAndView mv = new ModelAndView("/books");
        mv.addObject("books",repo);
        return mv;
    }
}
