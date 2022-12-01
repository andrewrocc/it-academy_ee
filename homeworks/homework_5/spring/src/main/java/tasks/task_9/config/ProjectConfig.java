package tasks.task_9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tasks.task_9.beans.Parrot;
import tasks.task_9.beans.Person;

@Configuration
@ComponentScan(basePackages = "tasks.task_9.beans")
public class ProjectConfig {

	@Bean
	public Parrot parrotGosha() {
		Parrot p = new Parrot();
		p.setName("Gosha");
		return p;
	}

	@Bean
	public Parrot parrotNameless() {
		Parrot p = new Parrot();
		p.setName("nameless");
		return p;
	}

	@Bean
	public Person personJohn() {
		return new Person("John");
	}
}
