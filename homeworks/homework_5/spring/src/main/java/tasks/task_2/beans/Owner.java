package tasks.task_2.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class Owner {

	private String name;

	private String lastName;

	private int age;

	@Autowired
	private Car car;
}
