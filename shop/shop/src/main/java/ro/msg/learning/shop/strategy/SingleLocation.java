package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "locationStrategy")
@RequiredArgsConstructor
public class SingleLocation implements LocationStrategy {
    private final LocationService locationService;
    private final StockService stockService;

    @Override
    @Transactional
    public List<Stock> getLocation(List<Stock> productsList) {
        List<Location> locations = locationService.findAll();
        List<Location> loc = locations.stream().filter(location ->
                productsList.stream()
                        .allMatch(stock -> stockService.findByLocationAndProduct(location, stock.getProduct()).isPresent()))
                .collect(Collectors.toList());
        if (!loc.isEmpty()) {
            productsList.stream().forEach(i -> i.setLocation(loc.get(0)));
            return productsList;
        } else throw new StrategyException("Cannot find a location using `Single Location` strategy!");
    }

}
