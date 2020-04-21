package ro.msg.learning.shop.service;

import ro.msg.learning.shop.DTO.LocationDTO;
import ro.msg.learning.shop.DTO.ProductDTO;
import ro.msg.learning.shop.DTO.StockDTO;
import ro.msg.learning.shop.entity.Location;

import java.util.List;

public interface StockService {
    List<StockDTO> findAll();
    StockDTO findByLocationAndProduct(LocationDTO locationDTO, ProductDTO productDTO);
}
