package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.strategy.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService  {

    private final AppConfiguration appConfiguration;
    private final StockService stockService;

    public LocationStrategy getStrategy(String type) {
        switch (type) {
            case "Single Location":
                return appConfiguration.strategyFactory().getStrategy("locationStrategy");
            case "Most Abundant":
                return appConfiguration.strategyFactory().getStrategy("mostAbundantStrategy");
            default: throw new StrategyException("Chose a valid strategy!");
        }
    }

    @Transactional
    public void getProducts(List<Stock> productsList){
        String type = "Single Location";
        List<Stock> stocks = getStrategy(type).getLocation(productsList);
        for(Stock stock: stocks){
            Stock updatedStock = stockService.findByLocationAndProduct(stock.getLocation(), stock.getProduct()).get();
            Integer newQuantity = updatedStock.getQuantity() - stock.getQuantity();
            System.out.println("stock: " + updatedStock.getQuantity() + " current value: " + stock.getQuantity() + " => " + newQuantity);
            updatedStock.setQuantity(newQuantity);
            stockService.save(updatedStock);
        }
    }
}
