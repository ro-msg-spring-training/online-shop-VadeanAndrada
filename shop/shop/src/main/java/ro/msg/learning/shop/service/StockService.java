package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Optional<Stock> findStockByLocationAndProduct(Location location, Product product) {
        return stockRepository.findStockByLocationAndProduct(location, product);
    }

    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> findAllProduct(Product product) {
        return stockRepository.findAllStockByProduct(product);
    }

    public List<Stock> deleteAllStocks() {
        List<Stock> deletedStocks = stockRepository.findAll();
        stockRepository.deleteAll();
        return deletedStocks;
    }
}
