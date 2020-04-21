package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.DTO.OrderDTOView;
import ro.msg.learning.shop.DTO.StockDTO;

import java.util.List;

public interface LocationStrategy {
    List<StockDTO>  getLocation (OrderDTOView orderDTOView);
}
