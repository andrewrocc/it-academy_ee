package my.first.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "t_employee_photo")
public class EmployeePhoto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Lob
    @Column(name = "EMPLOYEE_PHOTO", columnDefinition = "MEDIUMBLOB NOT NULL")
    private byte[] photo;
}
