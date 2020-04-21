package ro.msg.learning.shop.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private OrdersDTO ordersDTO;
    private ProductDTO productDTO;
    private Integer quantity;

    public OrderDetailDTO(OrdersDTO ordersDTO, ProductDTO productDTO, Integer quantity) {
        this.ordersDTO = ordersDTO;
        this.productDTO = productDTO;
        this.quantity = quantity;
    }

    public OrderDetailDTO(ProductDTO productDTO, Integer quantity) {
        this.productDTO = productDTO;
        this.quantity = quantity;
    }
}
