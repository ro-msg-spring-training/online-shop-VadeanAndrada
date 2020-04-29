package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, StockId> {
    Optional<Stock> findStockByLocationAndProduct(Location location, Product product);

    List<Stock> findAllStockByProduct(Product product);
}
