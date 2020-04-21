package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCategoryDTO {

    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

    public ProductCategoryDTO(Integer categoryId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
