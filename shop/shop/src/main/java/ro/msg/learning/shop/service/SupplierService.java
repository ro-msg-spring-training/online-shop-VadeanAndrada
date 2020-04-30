package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Supplier findSupplierByName(String name) {
        return supplierRepository.findSupplierByName(name)
                .orElseThrow(() -> new NoObjectFoundException("There is no supplier with this name: " + name));
    }

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteAll(){
        supplierRepository.deleteAll();
    }
}
