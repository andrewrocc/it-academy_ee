package by.it.pojos;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int age;

    @Column
    private String name;

    @Column
    private String surname;
}
