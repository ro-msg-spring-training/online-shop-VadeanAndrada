package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="supplier")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
    private List<Product> products;

}
