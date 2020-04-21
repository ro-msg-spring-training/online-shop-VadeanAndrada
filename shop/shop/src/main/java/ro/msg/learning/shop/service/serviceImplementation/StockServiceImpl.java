package ro.msg.learning.shop.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.LocationDTO;
import ro.msg.learning.shop.DTO.ProductDTO;
import ro.msg.learning.shop.DTO.StockDTO;
import ro.msg.learning.shop.DTO.builder.LocationBuilder;
import ro.msg.learning.shop.DTO.builder.ProductBuilder;
import ro.msg.learning.shop.DTO.builder.StockBuilder;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.List;
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<StockDTO> findAll() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockDTO> stockDTOS = new ArrayList<>();
        for (Stock s: stocks){
            stockDTOS.add(StockBuilder.generateDTOFromEntity(s));
        }
        return stockDTOS;
    }

    @Override
    public StockDTO findByLocationAndProduct(LocationDTO locationDTO, ProductDTO productDTO) {
        return StockBuilder.generateDTOFromEntity(stockRepository.findStockByLocationAndProduct(
                LocationBuilder.generateEntityFromDTO(locationDTO),
                ProductBuilder.generateEntityFromDTO(productDTO)));
    }
}
