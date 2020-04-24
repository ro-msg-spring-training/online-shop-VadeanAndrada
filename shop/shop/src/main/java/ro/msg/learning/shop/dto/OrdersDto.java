package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDto {

    private Integer id;
    private LocalDateTime createAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private Integer locationId;
    private Integer customerId;

}
