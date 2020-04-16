package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
