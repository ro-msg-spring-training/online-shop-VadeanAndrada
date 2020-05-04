package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoObjectFoundException("There is no Customer with id: " + id));
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() -> new NoObjectFoundException("No customer with username: " + username));
    }
}
