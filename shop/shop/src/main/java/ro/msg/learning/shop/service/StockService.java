package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> findAll() {
        return  stockRepository.findAll();
    }

    public Optional<Stock> findByLocationAndProduct(Location location, Product product) {
     return stockRepository.findStockByLocationAndProduct(location,product);
    }

    public Optional<Stock> findByProduct(Product product){
        return stockRepository.findStockByProduct(product);
    }

    public Optional<Stock> findByLocation(Location location){
        return stockRepository.findStockByLocation(location);
    }

    public void save(Stock stock){
        stockRepository.save(stock);
    }

    public List<Stock> findAllByProduct (Product product){
        return  stockRepository.findAllStockByProduct(product);
    }
}
