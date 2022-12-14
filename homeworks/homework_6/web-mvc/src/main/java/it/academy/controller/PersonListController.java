package it.academy.controller;

import it.academy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PersonListController {

    @Autowired
    private PersonService service;

    @GetMapping("/person_list")
    public ModelAndView getPersonList() {
        return new ModelAndView("person_list",
                Map.of("persons", service.getPeople()));
    }
}
