package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.builder.ProductBuilder;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.SupplierService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Supplier supplier = supplierService.findSupplierByName(productDto.getSupplierName());
        ProductCategory productCategory = productCategoryService.findByName(productDto.getCategoryName());
        Product product = ProductBuilder.returnEntityFromDto(productDto, supplier, productCategory);
        Product savedProduct = productService.create(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body(ProductBuilder.returnDtoFromEntity(savedProduct));
    }

    @GetMapping(value = "/readAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> readAllProducts() {
        List<ProductDto> productDtos = productService.readAll()
                .stream()
                .map(ProductBuilder::returnDtoFromEntity)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body(productDtos);
    }

    @GetMapping(value = "/readById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> readProductById(@RequestParam Integer idProduct) {
        Product product = productService.readById(idProduct);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body(ProductBuilder.returnDtoFromEntity(product));
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> deleteProduct(@RequestParam Integer idProduct) {
        Product deletedProduct = productService.delete(idProduct);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body(ProductBuilder.returnDtoFromEntity(deletedProduct));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Supplier supplier = supplierService.findSupplierByName(productDto.getSupplierName());
        ProductCategory productCategory = productCategoryService.findByName(productDto.getCategoryName());
        Product product = ProductBuilder.returnEntityFromDto(productDto, supplier, productCategory);
        Product updatedProduct = productService.update(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body(ProductBuilder.returnDtoFromEntity(updatedProduct));
    }

}
