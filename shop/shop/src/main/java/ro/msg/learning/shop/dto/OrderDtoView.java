package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoView {

    private LocalDateTime timestamp;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private List<ProductOrderDto> productsList;

}
