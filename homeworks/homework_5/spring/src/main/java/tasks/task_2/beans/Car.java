package tasks.task_2.beans;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Car {

	private String brand;

	private String model;

	private String number;

	private String type;

	public Car() {
		System.out.println("car constructor.");
	}

	private void postConstruct() {
		System.out.println("bean init method is here. configure type car...");
		setType("hypercar");
	}

	@PostConstruct
	private void annotationPostConstruct() {
		System.out.println("annotation post construct. configure number car...");
		setNumber("00000q");
	}

	private void destroyMethodCar() {
		System.out.println("bean destroy method is here. all connections are closed...");
	}

	@PreDestroy
	public void annotationPreDestroy() {
		System.out.println("annotation pre destroy. the app is closing.");
	}
}
