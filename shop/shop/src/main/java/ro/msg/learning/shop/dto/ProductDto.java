package ro.msg.learning.shop.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ProductDto {

    private Integer productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Double productWeight;
    private String imageUrl;
    private Integer supplierId;
    private String supplierName;
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

}
