package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.repository.LocationRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService  {
    private final LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }
    public void saveLocation(Location location){
        locationRepository.save(location);
    }
    public void deleteAll() {
        locationRepository.deleteAll();
    }
}
