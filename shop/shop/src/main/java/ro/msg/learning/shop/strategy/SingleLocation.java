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

@Component
@RequiredArgsConstructor
public class SingleLocation implements LocationStrategy {
    private final LocationService locationService;
    private final StockService stockService;

    @Override
    @Transactional
    public Map<Location, Map<Product, Integer>> getLocation(Map<Product, Integer> productQuantity) {
        Map<Location, Map<Product, Integer>> productLocationQuantity = new HashMap<>();
        List<Location> locations = locationService.findAll();
        Location locationFound = locations.stream()
                .findFirst()
                .filter(location -> productQuantity.entrySet().stream()
                        .allMatch(productsList -> stockService.findByLocationAndProduct(location, productsList.getKey()).isPresent() ?
                                stockService.findByLocationAndProduct(location, productsList.getKey()).get().getQuantity() >= productsList.getValue() : false))
                .orElseThrow(() -> new StrategyException("Cannot find a location using `Single Location` strategy!"));
        updateStock(productQuantity, locationFound);
        productLocationQuantity.put(locationFound, productQuantity);
        return productLocationQuantity;
    }

    @Transactional
    private void updateStock(Map<Product, Integer> productQuantity, Location location) {
        for (Map.Entry<Product, Integer> entry : productQuantity.entrySet()) {
            Stock stockForUpdate = stockService.findByLocationAndProduct(location, entry.getKey()).get();
            stockForUpdate.setQuantity(stockForUpdate.getQuantity() - entry.getValue());
        }
    }

}
