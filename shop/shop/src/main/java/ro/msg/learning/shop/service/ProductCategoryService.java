package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory findByName(String name) {
        return productCategoryRepository.findProductCategoryByName(name)
                .orElseThrow(() -> new NoObjectFoundException("There is no productCategory with this name: " + name));
    }

    public void saveProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    public void deleteAll(){
        productCategoryRepository.deleteAll();
    }
}
