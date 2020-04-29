package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.exception.ProductExistsException;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public Product readById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoObjectFoundException("There is no product with id: " + id));
    }

    public Product create(Product product) {
        if (!productRepository.findProductByName(product.getName()).isPresent()) {
            return productRepository.save(product);
        } else throw new ProductExistsException("There is a product with the same name!");

    }

    public Product delete(Integer idProduct) {
        Product product = readById(idProduct);
        productRepository.deleteById(idProduct);
        return product;
    }

    public Product update(Product product) {
        if (productRepository.findProductByName(product.getName()).isPresent()
                && !productRepository.findProductByName(product.getName()).get().getId().equals(product.getId())) {
            throw new ProductExistsException("There is a product with the same name!");
        } else {
            return productRepository.save(product);
        }
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

}
