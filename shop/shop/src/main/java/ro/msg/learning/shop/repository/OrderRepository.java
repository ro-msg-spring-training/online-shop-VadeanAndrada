package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Order;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
  //  @Query(value = "INSERT INTO `ORDER ` VALUES ")
    Order save(Order order);

}
