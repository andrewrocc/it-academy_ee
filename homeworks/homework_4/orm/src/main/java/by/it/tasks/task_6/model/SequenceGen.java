package by.it.tasks.task_6.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class SequenceGen {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_sequence")
	@SequenceGenerator(name = "t_sequence", sequenceName = "t_seq")
	private Long id;

	@Column(name = "name")
	private String name;
}
