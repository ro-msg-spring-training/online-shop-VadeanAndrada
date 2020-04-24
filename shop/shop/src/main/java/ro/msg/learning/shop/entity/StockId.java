package ro.msg.learning.shop.entity;

import lombok.*;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StockId implements Serializable {

    private Integer productId;
    private Integer locationId;

}
