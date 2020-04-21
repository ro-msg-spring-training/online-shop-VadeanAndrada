package ro.msg.learning.shop.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.SupplierDTO;
import ro.msg.learning.shop.DTO.builder.SupplierBuilder;
import ro.msg.learning.shop.exception.EmptyDTOObjectException;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void create(SupplierDTO supplierDTO) {
        if(!supplierDTO.getSupplierName().isEmpty()){
            supplierRepository.save(SupplierBuilder.generateEntityFromDTO(supplierDTO));
        } else throw new EmptyDTOObjectException("Empty SupplierDTO object");
    }

    @Override
    public SupplierDTO findByName(String name){
        if (supplierRepository.findSupplierByName(name) != null){
            return SupplierBuilder.generateDTOFromEntity(supplierRepository.findSupplierByName(name));
        } else throw new NoObjectFoundException("There is no supplier with this name: " + name );
    }
}
