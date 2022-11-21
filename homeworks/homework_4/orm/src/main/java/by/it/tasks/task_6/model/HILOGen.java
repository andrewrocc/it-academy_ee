package by.it.tasks.task_6.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class HILOGen {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_gen")
		@GenericGenerator(name = "hilo_gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
				parameters = { @Parameter(name = "sequence_name", value = "hilo_sequence"),
						@Parameter(name = "initial_value", value = "1"),
						@Parameter(name = "increment_size", value = "2"),
						@Parameter(name = "optimizer", value = "hilo")
	})
	private long id;

	@Column
	private String name;
}
