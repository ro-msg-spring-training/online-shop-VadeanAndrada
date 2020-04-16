package ro.msg.learning.shop.entity.idClass;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class StockID implements Serializable {

    private Product product;
    private Location location;

    public StockID(Product product, Location location) {
        this.product = product;
        this.location = location;
    }

}
