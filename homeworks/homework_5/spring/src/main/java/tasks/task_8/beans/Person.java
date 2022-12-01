package tasks.task_8.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class Person {

	private String name = "valera";

	private final Parrot parrot;

	@Autowired(required = false)
	public Person(Parrot parrot) {
		this.parrot = parrot;
	}

	@Autowired(required = false)
	public Person(String name, Parrot parrot) {
		this.parrot = parrot;
		this.name = name;
	}
}
