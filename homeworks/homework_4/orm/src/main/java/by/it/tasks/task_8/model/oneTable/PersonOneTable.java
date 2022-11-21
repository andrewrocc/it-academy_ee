package by.it.tasks.task_8.model.oneTable;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonOneTable {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Character p_type;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String faculty;

	@Column
	private Double mark;

	@Column
	private String company;

	@Column
	private Double salary;
}
