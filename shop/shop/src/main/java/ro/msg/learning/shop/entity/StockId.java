package ro.msg.learning.shop.entity;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StockId implements Serializable {

    private Integer productId;
    private Integer locationId;

}
