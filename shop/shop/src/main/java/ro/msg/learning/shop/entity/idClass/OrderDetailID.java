package ro.msg.learning.shop.entity.idClass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Product;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class OrderDetailID implements Serializable {

    private Orders orders;
    private Product product;

    public OrderDetailID(Orders orders, Product product) {
        this.orders = orders;
        this.product = product;
    }

}
