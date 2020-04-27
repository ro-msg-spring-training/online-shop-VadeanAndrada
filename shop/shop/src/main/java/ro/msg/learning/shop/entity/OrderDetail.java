package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_detail")
@NoArgsConstructor
@IdClass(OrderDetailId.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderDetail implements Serializable {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Product product;

    private Integer quantity;

}
