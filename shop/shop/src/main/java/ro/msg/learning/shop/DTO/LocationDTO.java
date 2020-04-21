package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDTO {
    private Integer id;
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    public LocationDTO(Integer id, String name, String country, String city, String county, String streetAddress) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }
}
