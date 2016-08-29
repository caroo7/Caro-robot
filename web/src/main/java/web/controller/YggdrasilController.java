package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bartlomiej on 26.08.16.
 */

@Controller
public class YggdrasilController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
        public ModelAndView index(){
        ModelAndView model = new ModelAndView();

        return model;
    }
}
