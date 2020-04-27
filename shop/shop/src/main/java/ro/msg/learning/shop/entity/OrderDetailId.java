package ro.msg.learning.shop.entity;

import lombok.*;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Product;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailId implements Serializable {

    private Integer orderId;
    private Integer productId;

}
