package by.it.tasks.task_6.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ForeignGen {

	@Id
	@Column
	@GeneratedValue(generator = "foreign_key_gen")
	@GenericGenerator(name = "foreign_key_gen",
			strategy = "foreign",
			parameters = @org.hibernate.annotations.Parameter(name = "property", value = "simpleClass"))
	private UUID id;

	@Column
	private String name;

	@OneToOne
	@PrimaryKeyJoinColumn
	private SimpleClass simpleClass;
}
