package my.first.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "t_employee_photo")
public class EmployeePhoto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_photo_seq")
    @SequenceGenerator(name = "employee_photo_seq", sequenceName = "t_employee_photo_seq")
    private long id;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Lob
    @Column(name = "EMPLOYEE_PHOTO", columnDefinition = "BLOB NOT NULL")
    private byte[] photo;
}
