package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.OrderDetailId;
import ro.msg.learning.shop.entity.Product;

import javax.persistence.criteria.Order;
import java.util.List;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    OrderDetail findOrderDetailByProductAndOrder(Product product, Order order);
    List<OrderDetail> findAOrderDetailsByProduct(Product product);
    List<OrderDetail> findAllOrderDetailsByOrder(Order order);

}
