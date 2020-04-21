package ro.msg.learning.shop.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class SupplierDTO {

    private Integer supplierId;
    private String supplierName;

    public SupplierDTO(Integer supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }
}
