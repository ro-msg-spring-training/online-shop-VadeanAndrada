package ro.msg.learning.shop.DTO.builder;

import ro.msg.learning.shop.DTO.StockDTO;
import ro.msg.learning.shop.entity.Stock;

public class StockBuilder {
    public static Stock generateEntityFromDTO(StockDTO stockDTO){
        return new Stock(LocationBuilder.generateEntityFromDTO(stockDTO.getLocation()), ProductBuilder.generateEntityFromDTO(stockDTO.getProduct()), stockDTO.getQuantity());
    }
    public static StockDTO generateDTOFromEntity (Stock stock){
        return new StockDTO(LocationBuilder.generateDTOFromEntity(stock.getLocation()), ProductBuilder.generateDTOFromEntity(stock.getProduct()), stock.getQuantity());
    }
}
