package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="product_category")
@Data
@NoArgsConstructor
public class ProductCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

}
