package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.SupplierDTO;


public interface SupplierService {
    void create (SupplierDTO supplierDTO);
    SupplierDTO findByName(String name);

}
