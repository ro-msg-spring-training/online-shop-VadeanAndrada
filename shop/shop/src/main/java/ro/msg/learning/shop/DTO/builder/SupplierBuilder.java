package ro.msg.learning.shop.DTO.builder;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.DTO.SupplierDTO;
import ro.msg.learning.shop.entity.Supplier;

@NoArgsConstructor
public class SupplierBuilder {

    public static SupplierDTO generateDTOFromEntity (Supplier supplier){
        return new SupplierDTO(supplier.getId(), supplier.getName());
    }

    public static Supplier generateEntityFromDTO (SupplierDTO supplierDTO){
        return new Supplier(supplierDTO.getSupplierId(), supplierDTO.getSupplierName());
    }

}
