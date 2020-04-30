package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="stock")
@Entity
@IdClass(StockId.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {
    @Id
    @Column(name = "location_id")
    private Integer locationId;

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Product product;
    private Integer quantity;
}
