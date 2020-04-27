package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.OrderDtoView;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;

import java.util.List;
import java.util.Map;

public interface LocationStrategy {
    Map<Location, Map<Product, Integer>>  getLocation (Map<Product, Integer> productQuantity4);
}
