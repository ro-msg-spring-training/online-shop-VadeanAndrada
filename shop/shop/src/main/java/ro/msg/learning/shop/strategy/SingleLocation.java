package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SingleLocation implements LocationStrategy {
    private final LocationService locationService;
    private final StockService stockService;

    @Override
    @Transactional
    public Map<Location, Map<Product, Integer>> getLocation(Map<Product, Integer> productQuantity) throws StrategyException {
        Map<Location, Map<Product, Integer>> productLocationQuantity = new HashMap<>();
        List<Location> locations = locationService.findAll();
        List<Location> locationFound = locations.stream()
                .filter(location -> productQuantity.entrySet().stream()
                        .allMatch(productsList -> {
                            Optional<Stock> stock = stockService.findStockByLocationAndProduct(location, productsList.getKey());
                            return stock.filter(value -> value.getQuantity() >= productsList.getValue()).isPresent();
                        }))
                .collect(Collectors.toList());
        if (!locationFound.isEmpty()) {
            productLocationQuantity.put(locationFound.get(0), productQuantity);
            return productLocationQuantity;
        } else throw new StrategyException("Cannot find a location using `Single Location` strategy!");
    }
}
