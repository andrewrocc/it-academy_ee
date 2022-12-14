package it.academy.controller;

import it.academy.model.Person;
import it.academy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(value = "/add_person")
	public ModelAndView getAddPersonPage() {
		System.out.println("person get controller.");
		return new ModelAndView("add_person");
	}

	@PostMapping(value = "/add_person")
	public String addNewPerson(Person p) {
		System.out.println("person post controller");
		boolean success = service.addNewPerson(p);
//		System.out.println(service.getPeople());
//		ModelAndView view = new ModelAndView("redirect:/person_list");
//		view.addObject("message", success ? "success" : "unsuccessful");
		return "redirect:/person_list";
	}
}
