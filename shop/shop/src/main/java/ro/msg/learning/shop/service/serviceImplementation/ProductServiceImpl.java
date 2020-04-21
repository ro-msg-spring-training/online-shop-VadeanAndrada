package ro.msg.learning.shop.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductCategoryDTO;
import ro.msg.learning.shop.DTO.ProductDTO;
import ro.msg.learning.shop.DTO.SupplierDTO;
import ro.msg.learning.shop.DTO.builder.ProductBuilder;
import ro.msg.learning.shop.DTO.builder.ProductCategoryBuilder;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.exception.EmptyDTOObjectException;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.exception.ProductExistsException;
import ro.msg.learning.shop.exception.UpdateProductException;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.SupplierService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private SupplierService supplierService;

    @Override
    public List<ProductDTO> readAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p: products){
            productDTOS.add(ProductBuilder.generateDTOFromEntity(p));
        }
        return productDTOS;
    }

    @Override
    public ProductDTO readById(Integer id) {
        if (productRepository.findById(id).isPresent()){
           return ProductBuilder.generateDTOFromEntity(productRepository.findById(id).get());
        } else throw new NoObjectFoundException("There is no product with id: " + id);
    }

    @Override
    public void create(ProductDTO productDTO) {
        if(!productDTO.getProductName().isEmpty()){
            if(productRepository.findProductByName(productDTO.getProductName()) != null)
                throw new ProductExistsException();
            SupplierDTO supplierDTO = supplierService.findByName(productDTO.getSupplierDTO().getSupplierName());
            ProductCategoryDTO productCategoryDTO = productCategoryService.findByName(productDTO.getProductCategoryDTO().getCategoryName());
            ProductDTO productForSave = new ProductDTO(productDTO.getProductName(),productDTO.getProductDescription(),productDTO.getProductPrice(),productDTO.getProductWeight(),productCategoryDTO,supplierDTO,productDTO.getImageUrl());
            productRepository.save(ProductBuilder.generateEntityFromDTO(productForSave));
        }else {
            throw new EmptyDTOObjectException("The productDTO is empty");
        }
    }

    @Override
    public void delete(Integer idProduct) {
        if(idProduct != null){
            ProductDTO productDTO = readById(idProduct);
            productRepository.delete(ProductBuilder.generateEntityFromDTO(productDTO));
        }else {
            throw new EmptyDTOObjectException("The product id is empty");
        }
    }

    @Override
    public void update(ProductDTO productDTO) {
        ProductDTO oldProduct = readById(productDTO.getProductId());
        if( checkUpdateProductFields(productDTO) ){
            oldProduct.setProductName(productDTO.getProductName());
            oldProduct.setImageUrl(productDTO.getImageUrl());
            oldProduct.setProductDescription(productDTO.getProductDescription());
            oldProduct.setProductPrice(productDTO.getProductPrice());
            oldProduct.setProductWeight(productDTO.getProductWeight());
            oldProduct.setProductCategoryDTO(productCategoryService.findByName(productDTO.getProductCategoryDTO().getCategoryName()));
            oldProduct.setSupplierDTO(supplierService.findByName(productDTO.getSupplierDTO().getSupplierName()));
            productRepository.save(ProductBuilder.generateEntityFromDTO(oldProduct));
        } else throw new UpdateProductException();
    }

    private boolean checkUpdateProductFields(ProductDTO productDTO){
        if (productDTO.getProductName().isEmpty() || productDTO.getProductWeight() == null || productDTO.getProductPrice() == null || productDTO.getImageUrl().isEmpty() || productDTO.getProductDescription().isEmpty())
            return false;
        if (productRepository.findProductByName(productDTO.getProductName()) != null){
            if(!productRepository.findProductByName(productDTO.getProductName()).getId().equals(productDTO.getProductId())){
                throw new ProductExistsException();
            }
        }
        if (productCategoryService.findByName(productDTO.getProductCategoryDTO().getCategoryName()) == null)
            return  false;
        if (supplierService.findByName(productDTO.getSupplierDTO().getSupplierName()) == null)
            return false;
        return true;
    }
}
