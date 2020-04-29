package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.exception.StrategyException;

import java.util.Map;

public interface LocationStrategy {
    Map<Location, Map<Product, Integer>>  getLocation (Map<Product, Integer> productQuantity4) throws StrategyException;
}
