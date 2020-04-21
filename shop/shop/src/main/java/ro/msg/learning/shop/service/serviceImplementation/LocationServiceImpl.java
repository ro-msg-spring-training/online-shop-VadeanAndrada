package ro.msg.learning.shop.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.LocationDTO;
import ro.msg.learning.shop.DTO.builder.LocationBuilder;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.service.LocationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDTO> findAll() {
        List<Location> location = locationRepository.findAll();
        List<LocationDTO> locationDTOS = new ArrayList<>();
        for(Location l: location){
            locationDTOS.add(LocationBuilder.generateDTOFromEntity(l));
        }
        return locationDTOS;
    }
}
