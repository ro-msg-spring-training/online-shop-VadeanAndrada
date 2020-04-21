package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Location;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrdersDTO {

    private Integer id;
    private LocalDateTime createAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private LocationDTO locationDTO;
    private CustomerDTO customerDTO;

    public OrdersDTO(Integer id, LocalDateTime createAt, String country, String city, String county, String streetAddress, LocationDTO locationDTO, CustomerDTO customerDTO) {
        this.id = id;
        this.createAt = createAt;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
        this.locationDTO = locationDTO;
        this.customerDTO = customerDTO;
    }
}
