package ro.msg.learning.shop.service;

import ro.msg.learning.shop.DTO.LocationDTO;

import java.util.List;

public interface LocationService {
    List<LocationDTO> findAll();
}
