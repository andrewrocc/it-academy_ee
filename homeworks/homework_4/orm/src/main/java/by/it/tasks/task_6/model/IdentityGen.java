package by.it.tasks.task_6.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class IdentityGen {

	@Id
	@Column
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "org.hibernate.id.IdentityGenerator")
	private long id;

	@Column
	private String name;
}
