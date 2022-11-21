package by.it.tasks.task_8.model.perClass;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({@AttributeOverride(name = "ID", column = @Column(name = "ID")),
					@AttributeOverride(name = "name", column = @Column(name = "name")),
					@AttributeOverride(name = "surname", column = @Column(name = "surname"))})
public class StudentPerClass extends PersonPerClass{

	@Column
	private String faculty;

	@Column
	private Double mark;

	@Override
	public String toString() {
		return "StudentPerClass(" +
				"id='" + getId() + '\'' +
				"name='" + getName() + '\'' +
				", surname='" + getSurname() + '\'' +
				", faculty='" + faculty + '\'' +
				", mark=" + mark +
				')';
	}
}
