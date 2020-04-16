package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Location location;

    @Id
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    private Integer quantity;
}
