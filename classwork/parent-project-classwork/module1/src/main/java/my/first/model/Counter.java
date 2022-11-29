package my.first.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Counter")
public class Counter {

    @Id
//    @Column(name = "id")
    private long id;

//    @Column(name = "count")
    private int count;
}
