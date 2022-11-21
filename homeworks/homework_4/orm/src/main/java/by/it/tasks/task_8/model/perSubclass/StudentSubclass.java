package by.it.tasks.task_8.model.perSubclass;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "ID")
public class StudentSubclass extends PersonSubclass {

	@Column
	private String faculty;

	@Column
	private Double mark;

	@Override
	public String toString() {
		return "StudentSubclass(" +
				"id='" + getId() + '\'' +
				", faculty='" + faculty + '\'' +
				", mark=" + mark +
				')';
	}
}
