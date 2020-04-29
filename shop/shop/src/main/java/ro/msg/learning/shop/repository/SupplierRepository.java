package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Supplier;

import java.util.Optional;


public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Optional<Supplier> findSupplierByName(String name);
}
