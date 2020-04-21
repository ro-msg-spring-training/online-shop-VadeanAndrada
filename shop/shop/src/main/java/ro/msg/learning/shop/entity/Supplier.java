package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="supplier")
@Data
@NoArgsConstructor
public class Supplier implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Product> products;

    public Supplier(Integer id, String name) {
         this.id = id;
        this.name = name;
    }

}
