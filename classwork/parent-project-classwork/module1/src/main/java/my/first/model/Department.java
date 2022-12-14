package my.first.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "t_department")
public class Department {

    @Id
    @Column(name = "F_DEPARTMENTID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "org.hibernate.id.IncrementGenerator")
    private long id;

    @Column(name = "F_DEPARTMENTNAME")
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Employee> employees;
}
