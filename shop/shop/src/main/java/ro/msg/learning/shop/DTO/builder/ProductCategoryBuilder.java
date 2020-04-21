package ro.msg.learning.shop.DTO.builder;

import ro.msg.learning.shop.DTO.ProductCategoryDTO;
import ro.msg.learning.shop.entity.ProductCategory;

public class ProductCategoryBuilder {

    public static ProductCategory generateEntityFromDTO (ProductCategoryDTO productCategoryDTO){
        return new ProductCategory(productCategoryDTO.getCategoryId(), productCategoryDTO.getCategoryName(), productCategoryDTO.getCategoryDescription());
    }

    public  static ProductCategoryDTO generateDTOFromEntity (ProductCategory productCategory){
        return new ProductCategoryDTO(productCategory.getId(), productCategory.getName(), productCategory.getDescription());
    }

}
