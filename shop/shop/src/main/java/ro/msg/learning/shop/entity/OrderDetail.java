package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @EqualsAndHashCode.Exclude
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Product product;

    private Integer quantity;

    public OrderDetail(Orders orders, Product product, Integer quantity) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }
}
