package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

}
