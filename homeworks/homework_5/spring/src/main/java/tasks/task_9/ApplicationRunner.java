package tasks.task_9;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tasks.task_9.config.ProjectConfig;
import tasks.task_9.beans.Parrot;
import tasks.task_9.beans.Person;

public class ApplicationRunner {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person = context.getBean("person", Person.class);
		Parrot parrot = context.getBean("parrot", Parrot.class);
		Parrot gosha = context.getBean("parrotGosha", Parrot.class);

		System.out.println("Person's name: " + person.getName());
		System.out.println("Parrot's name: " + parrot.getName());
		System.out.println("Parrot's name: " + gosha.getName());
		System.out.println("Person's parrot: " + person.getParrot());
	}
}
