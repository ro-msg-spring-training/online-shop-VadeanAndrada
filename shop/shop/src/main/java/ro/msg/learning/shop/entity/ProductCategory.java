package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="product_category")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> products;

}
