package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.StockService;

import javax.transaction.Transactional;
import java.util.*;


@Component
@RequiredArgsConstructor
public class MostAbundant implements LocationStrategy {
    private final StockService stockService;

    @Override
    @Transactional
    public Map<Location, Map<Product, Integer>> getLocation(Map<Product, Integer> productQuantity) throws StrategyException{
        Map<Location, Map<Product, Integer>> productLocationQuantity = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : productQuantity.entrySet()) {
            Location location = stockService.findAllProduct(entry.getKey())
                    .stream()
                    .filter(s -> s.getQuantity() >= entry.getValue())
                    .max(Comparator.comparing(Stock::getQuantity))
                    .map(Stock::getLocation)
                    .orElseThrow(() -> new StrategyException("Cannot find a location using `Most Abundant` strategy!"));
            if (productLocationQuantity.containsKey(location)) {
                Map<Product, Integer> productQuantityUpdated = productLocationQuantity.get(location);
                productQuantityUpdated.put(entry.getKey(), entry.getValue());
            } else {
                Map<Product, Integer> productQuantityUpdated = new HashMap<>();
                productQuantityUpdated.put(entry.getKey(), entry.getValue());
                productLocationQuantity.put(location, productQuantityUpdated);
            }
        }
        return productLocationQuantity;
    }

}
