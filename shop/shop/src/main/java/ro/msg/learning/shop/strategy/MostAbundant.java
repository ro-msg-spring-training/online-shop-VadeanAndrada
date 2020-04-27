package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Component(value = "mostAbundantStrategy")
@RequiredArgsConstructor
public class MostAbundant implements LocationStrategy {
    private final LocationService locationService;
    private final StockService stockService;

    @Override
    @Transactional
    public List<Stock> getLocation(List<Stock> productsList) {
        List<Location> locations = locationService.findAll();
        List<Stock> stocks = new ArrayList<>();
        for (Stock stock : productsList) {
            Stock productLocation = stockService.findAllByProduct(stock.getProduct())
                    .stream()
                    .filter(s -> s.getQuantity() >= stock.getQuantity())
                    .max(Comparator.comparing(Stock::getQuantity))
                    .orElseThrow(() -> new StrategyException("Cannot find a location using `Most Abundant` strategy!"));
            productLocation.setQuantity(stock.getQuantity());
            stocks.add(productLocation);
        }
        return stocks;
    }
}
