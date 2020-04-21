package ro.msg.learning.shop.DTO.builder;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.DTO.CustomerDTO;
import ro.msg.learning.shop.entity.Customer;

@Data
@NoArgsConstructor
public class CustomerBuilder {
    public static Customer generateEntityFromDTO (CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(),customerDTO.getFirstName(), customerDTO.getLastName(),customerDTO.getUsername(),customerDTO.getPassword(),customerDTO.getEmailAddress());
    }

    public static CustomerDTO generateDTOFromEntity(Customer customer){
        return new CustomerDTO(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getUsername(),customer.getPassword(),customer.getEmailAddress());
    }
}
