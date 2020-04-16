package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.idClass.StockID;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockID> {
}
