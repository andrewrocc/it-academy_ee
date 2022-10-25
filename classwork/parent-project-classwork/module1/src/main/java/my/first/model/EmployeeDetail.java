package my.first.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_employeedetail")
public class EmployeeDetail {

    @Id
    @Column(name = "F_EMPLOYEEID")
    @GeneratedValue(generator = "foreign_key_gen")
    @GenericGenerator(name = "foreign_key_gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "employee"))
    private long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STREET")
    private String street;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee employee;
}
