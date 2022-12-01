package tasks.task_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tasks.task_2.beans.Car;

@Configuration
@ComponentScan(basePackages = "tasks.task_2")
public class ProjectConfig {

	@Bean(initMethod = "postConstruct", destroyMethod = "destroyMethodCar")
	public Car car() {
		Car c = new Car();
		c.setBrand("Audi");
		c.setModel("RS 7");
		return c;
	}
}
