package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductDTO;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductDTO productDTO){
        productService.create(productDTO);
    }

    @GetMapping("/readAll")
    public List<ProductDTO> readAllProducts(){
        return productService.readAll();
    }

    @GetMapping("/readById")
    public ProductDTO readProductById(@RequestParam Integer idProduct){
        return productService.readById(idProduct);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam Integer idProduct){
        productService.delete(idProduct);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductDTO productDTO){
        productService.update(productDTO);
    }
}
