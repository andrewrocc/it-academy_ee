package by.it.tasks.task_8.model.perSubclass;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "ID")
public class EmployeeSubclass extends PersonSubclass {

	@Column
	private String company;

	@Column
	private Double salary;

	@Override
	public String toString() {
		return "EmployeeSubclass(" +
				"id='" + getId() + '\'' +
				"company='" + company + '\'' +
				", salary=" + salary +
				')';
	}
}
