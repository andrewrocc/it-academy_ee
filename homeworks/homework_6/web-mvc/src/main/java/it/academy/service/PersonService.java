package it.academy.service;

import it.academy.model.Person;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class PersonService {

	private List<Person> people;

	public PersonService() {
		people = new ArrayList<>();
	}

	public boolean addNewPerson(Person p) {
		if (people.size() >= 3) {
			return false;
		}
		int beforeInsert = people.size();
		people.add(p);
		int afterInsert = people.size();
		return (afterInsert - beforeInsert) == 1;
	}
}
