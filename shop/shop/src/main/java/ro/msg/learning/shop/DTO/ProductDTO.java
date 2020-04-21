package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDTO {

    private Integer productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Double productWeight;
    private ProductCategoryDTO productCategoryDTO;
    private SupplierDTO supplierDTO;
    private String imageUrl;

    public ProductDTO(Integer productId, String productName, String productDescription, BigDecimal productPrice, Double productWeight, ProductCategoryDTO productCategoryDTO, SupplierDTO supplierDTO, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productCategoryDTO = productCategoryDTO;
        this.supplierDTO = supplierDTO;
        this.imageUrl=imageUrl;
    }
    public ProductDTO(String productName, String productDescription, BigDecimal productPrice, Double productWeight, ProductCategoryDTO productCategoryDTO, SupplierDTO supplierDTO, String imageUrl) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productCategoryDTO = productCategoryDTO;
        this.supplierDTO = supplierDTO;
        this.imageUrl=imageUrl;
    }

}
