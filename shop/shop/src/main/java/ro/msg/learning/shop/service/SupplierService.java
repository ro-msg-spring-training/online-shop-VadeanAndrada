package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Supplier findByName(String name){
        return supplierRepository.findSupplierByName(name)
                                .orElseThrow(() -> new NoObjectFoundException("There is no supplier with this name: " + name ));
    }
}
