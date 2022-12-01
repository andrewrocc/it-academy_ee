package tasks.task_8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tasks.task_8.beans.Parrot;
import tasks.task_8.beans.Person;

@Configuration
@ComponentScan(basePackages = "tasks.task_8.beans")
public class ProjectConfig {

	@Bean
	public Person personMark() {
		return new Person("mark", new Parrot());
	}
}
