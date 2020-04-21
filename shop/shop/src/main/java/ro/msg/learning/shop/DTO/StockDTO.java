package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDTO {
    private LocationDTO location;
    private ProductDTO product;
    private Integer quantity;

    public StockDTO(LocationDTO location, ProductDTO product, Integer quantity) {
        this.location = location;
        this.product = product;
        this.quantity = quantity;
    }
}
