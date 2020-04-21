package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;

@Data
@Entity
@Table(name="product")
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    private Double weight;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplier", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    private List<Stock> stocks;

    @OneToMany(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    private  List<OrderDetail>  orderDetails;

    public Product(Integer id, String name, String description, BigDecimal price, Double weight, ProductCategory productCategory, Supplier supplier, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.imageUrl = imageUrl;
    }
}
