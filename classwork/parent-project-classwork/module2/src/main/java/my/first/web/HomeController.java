package my.first.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({ "/", "/index.html"} )
    public ModelAndView getHomePage() {
        System.out.println("Home controller call");
        return new ModelAndView("index");
    }
}
