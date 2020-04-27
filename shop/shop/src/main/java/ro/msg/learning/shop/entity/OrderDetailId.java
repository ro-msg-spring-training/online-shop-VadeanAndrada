package ro.msg.learning.shop.entity;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailId implements Serializable {

    private Integer orderId;
    private Integer productId;

}
