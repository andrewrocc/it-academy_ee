package tasks.task_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tasks.task_2.config.ProjectConfig;
import tasks.task_2.beans.Car;
import tasks.task_2.beans.Owner;

public class ApplicationRunner {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Car car = context.getBean(Car.class);
		Owner owner = context.getBean(Owner.class);
		System.out.println("\n" + car);
		System.out.println(owner + "\n");
		context.close();
	}
}
