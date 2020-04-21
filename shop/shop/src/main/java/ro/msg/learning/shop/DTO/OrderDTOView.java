package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTOView {
    private LocalDateTime timestamp;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private List<OrderDetailDTO> productsList;

    public OrderDTOView(LocalDateTime timestamp, String country, String city, String county, String streetAddress, List<OrderDetailDTO> productsList) {
        this.timestamp = timestamp;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
        this.productsList = productsList;
    }
}
