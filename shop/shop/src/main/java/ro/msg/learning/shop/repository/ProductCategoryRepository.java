package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.ProductCategory;

import java.util.Optional;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    Optional<ProductCategory> findProductCategoryByName(String name);
}
