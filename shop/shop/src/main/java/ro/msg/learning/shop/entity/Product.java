package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplier", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "product")
    private  List<OrderDetail>  orderDetails;
}
