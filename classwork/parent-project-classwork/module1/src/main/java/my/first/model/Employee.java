package my.first.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "t_employee")
public class Employee {

    //resolve null pointer ex in case submit on html form
    public Employee() {
//        if (employeeDetail == null) {
//            setEmployeeDetail(new EmployeeDetail());
//        }
//        getEmployeeDetail().setEmployee(this);
//        setMeetings(new HashSet<>());
    }

    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "t_employee_seq")
    private long id;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "CELL_PHONE")
    private String cellPhone;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeDetail employeeDetail;

    @ManyToOne
    @JoinColumn(name = "F_DEPARTMENTID")
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "t_employee_meeting",
            joinColumns = @JoinColumn(name = "F_EMPLOYEEID"),
            inverseJoinColumns = @JoinColumn(name = "F_MEETING_ID"))
    @ToString.Exclude
    private Set<Meeting> meetings;

//    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
//    private EmployeePhoto photo;
}
