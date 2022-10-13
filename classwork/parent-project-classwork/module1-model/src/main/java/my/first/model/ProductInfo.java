package my.first.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "product_info")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "org.hibernate.id.IncrementGenerator")
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column
    private double price;
}
