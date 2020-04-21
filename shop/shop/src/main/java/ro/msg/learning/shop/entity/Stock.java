package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.DTO.LocationDTO;
import ro.msg.learning.shop.DTO.ProductDTO;
import ro.msg.learning.shop.entity.idClass.StockID;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="stock")
@Data
@Entity
@NoArgsConstructor
@IdClass(StockID.class)
public class Stock implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Location location;

    @Id
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Product product;

    private Integer quantity;

    public Stock(Location location, Product product, Integer quantity) {
        this.location = location;
        this.product = product;
        this.quantity = quantity;
    }
}
