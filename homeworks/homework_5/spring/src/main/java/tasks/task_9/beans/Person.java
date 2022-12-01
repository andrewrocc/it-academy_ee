package tasks.task_9.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Person {

	private String name = "Ivan";

	private Parrot parrot;

	@Autowired(required = true)
	public Person(@Qualifier("parrotGosha") Parrot parrot) {
		this.parrot = parrot;
	}

	public Person(String name) {
		this.name = name;
	}
}
