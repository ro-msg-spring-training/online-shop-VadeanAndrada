package ro.msg.learning.shop.service;

import ro.msg.learning.shop.DTO.ProductDTO;

import java.util.List;

public interface ProductService {
     List<ProductDTO> readAll();
     ProductDTO readById(Integer id);
     void create(ProductDTO productDTO);
     void delete(Integer idProduct);
     void update(ProductDTO productDTO);
}
