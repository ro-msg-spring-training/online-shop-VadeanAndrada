package ro.msg.learning.shop.DTO.builder;

import ro.msg.learning.shop.DTO.ProductDTO;
import ro.msg.learning.shop.entity.Product;

public class ProductBuilder {

    public static Product generateEntityFromDTO(ProductDTO productDTO){
        return new Product(productDTO.getProductId(),
                           productDTO.getProductName(),
                           productDTO.getProductDescription(),
                           productDTO.getProductPrice(),
                           productDTO.getProductWeight(),
                           ProductCategoryBuilder.generateEntityFromDTO(productDTO.getProductCategoryDTO()),
                           SupplierBuilder.generateEntityFromDTO(productDTO.getSupplierDTO()),
                           productDTO.getImageUrl());
    }

    public static ProductDTO generateDTOFromEntity(Product product){
        return new ProductDTO(product.getId(),
                              product.getName(),
                              product.getDescription(),
                              product.getPrice(),
                              product.getWeight(),
                              ProductCategoryBuilder.generateDTOFromEntity(product.getProductCategory()),
                              SupplierBuilder.generateDTOFromEntity(product.getSupplier()),
                              product.getImageUrl());
    }
}
