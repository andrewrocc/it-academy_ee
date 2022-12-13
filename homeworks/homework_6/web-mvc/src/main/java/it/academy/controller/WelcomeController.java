package it.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        System.out.println("welcome page");
        ModelAndView view = new ModelAndView("welcome");
        view.addObject("greeting", "Hello Person.");
        return view;
    }
}
