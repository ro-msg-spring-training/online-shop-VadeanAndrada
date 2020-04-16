package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.idClass.OrderDetailID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="order_detail")
@NoArgsConstructor
@IdClass(OrderDetailID.class)
public class OrderDetail implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "orders", referencedColumnName = "id")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    private Integer quantity;
}
