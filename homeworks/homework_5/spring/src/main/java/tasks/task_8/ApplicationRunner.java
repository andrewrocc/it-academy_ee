package tasks.task_8;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tasks.task_8.beans.Parrot;
import tasks.task_8.beans.Person;
import tasks.task_8.config.ProjectConfig;

public class ApplicationRunner {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Parrot e = context.getBean(Parrot.class);
		Person q = context.getBean("person", Person.class);
		Person m = context.getBean("personMark", Person.class);
		System.out.println(e);
		System.out.println(q);
		System.out.println(m);
	}
}
