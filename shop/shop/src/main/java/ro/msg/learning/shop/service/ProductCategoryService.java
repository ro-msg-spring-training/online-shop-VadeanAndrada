package ro.msg.learning.shop.service;


import ro.msg.learning.shop.DTO.ProductCategoryDTO;


public interface ProductCategoryService {
    void create(ProductCategoryDTO productCategoryDTO);
    ProductCategoryDTO findByName(String name);
}
