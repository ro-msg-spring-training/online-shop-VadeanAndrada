package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.OrderDtoView;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entity.Stock;

import java.util.List;

public interface LocationStrategy {
    List<Stock>  getLocation (List<Stock> productsList);
}
