package by.it.tasks.task_6.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@ToString
public class AssignedGen {

	@Id
	@Column
	private long id;

	@Column
	private String name;
}

