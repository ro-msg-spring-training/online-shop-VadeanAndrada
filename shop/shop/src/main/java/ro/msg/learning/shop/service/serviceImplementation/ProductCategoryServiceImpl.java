package ro.msg.learning.shop.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductCategoryDTO;
import ro.msg.learning.shop.DTO.builder.ProductCategoryBuilder;
import ro.msg.learning.shop.exception.EmptyDTOObjectException;
import ro.msg.learning.shop.exception.NoObjectFoundException;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void create(ProductCategoryDTO productCategoryDTO) {
        if(!productCategoryDTO.getCategoryName().isEmpty()){
            productCategoryRepository.save(ProductCategoryBuilder.generateEntityFromDTO(productCategoryDTO));
        } else throw new EmptyDTOObjectException("Empty ProductCategoryDTO object");

    }

    @Override
    public ProductCategoryDTO findByName(String name) {
       if(productCategoryRepository.findProductCategoryByName(name) != null){
           return ProductCategoryBuilder.generateDTOFromEntity(productCategoryRepository.findProductCategoryByName(name));
       } else throw new NoObjectFoundException("There is no productCategory with this name: " + name);
    }
}
