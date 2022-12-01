package tasks.task_10.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tasks.task_10.annotations.Animal;

@Setter
@Getter
@ToString
@Animal
public class Elephant {

	private String name = "karl";

	private int age = 9;
}

