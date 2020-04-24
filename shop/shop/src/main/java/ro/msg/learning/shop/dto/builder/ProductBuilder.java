package ro.msg.learning.shop.dto.builder;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;

public class ProductBuilder {

    public static ProductDto returnDtoFromEntity (Product product){
        return ProductDto.builder().categoryId(product.getId())
                .categoryName(product.getProductCategory().getName())
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .productWeight(product.getWeight())
                .imageUrl(product.getImageUrl())
                .supplierId(product.getSupplier().getId())
                .supplierName(product.getSupplier().getName())
                .build();
    }

    public static Product returnEntityFromDto (ProductDto productDto, Supplier supplier, ProductCategory productCategory){
        return Product.builder()
                .id(productDto.getProductId())
                .name(productDto.getProductName())
                .imageUrl(productDto.getImageUrl())
                .description(productDto.getProductDescription())
                .supplier(supplier)
                .weight(productDto.getProductWeight())
                .price(productDto.getProductPrice())
                .productCategory(productCategory)
                .build();
    }

}
