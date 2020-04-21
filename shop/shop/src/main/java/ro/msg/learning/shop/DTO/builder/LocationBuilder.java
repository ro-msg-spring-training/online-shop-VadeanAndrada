package ro.msg.learning.shop.DTO.builder;

import ro.msg.learning.shop.DTO.LocationDTO;
import ro.msg.learning.shop.entity.Location;

public class LocationBuilder {
    public static Location generateEntityFromDTO (LocationDTO locationDTO){
        return  new Location(locationDTO.getId(), locationDTO.getName(), locationDTO.getCountry(),locationDTO.getCity(),locationDTO.getCounty(),locationDTO.getStreetAddress());
    }
    public static LocationDTO generateDTOFromEntity (Location location){
        return new LocationDTO(location.getId(),location.getName(),location.getCountry(),location.getCity(),location.getCounty(),location.getStreetAddress());
    }
}
