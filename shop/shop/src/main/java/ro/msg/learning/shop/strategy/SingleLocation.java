package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.DTO.*;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.List;

public class SingleLocation implements LocationStrategy {
    @Autowired
    private LocationService locationService;
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductService productService;

    @Override
    public List<StockDTO> getLocation(OrderDTOView orderDTOView) {
        List<StockDTO> stockDTOS = new ArrayList<>();
        List<LocationDTO> locationDTO = locationService.findAll();
        loc: for(LocationDTO loc: locationDTO){
            for(OrderDetailDTO prod: orderDTOView.getProductsList()){
                if(stockService.findByLocationAndProduct(loc,prod.getProductDTO()) != null && stockService.findByLocationAndProduct(loc,prod.getProductDTO()).getQuantity() >= prod.getQuantity()){
                    StockDTO stockDTO = stockService.findByLocationAndProduct(loc,prod.getProductDTO());
                    stockDTO.setQuantity(prod.getQuantity());
                    stockDTOS.add(stockDTO);
                } else {
                    stockDTOS.clear();
                    continue loc;
                }
            }
        }
        if (!stockDTOS.isEmpty())
            return stockDTOS;
        else{
             new StrategyException("Cannot find a single location");
             return null;
        }
    }

}
