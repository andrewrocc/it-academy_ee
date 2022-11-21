package by.it.tasks.task_7.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Integer age;

	@Column
	private String name;

	@Column
	private String surname;

	@Embedded
	private Address address;
}
