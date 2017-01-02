package myRetail.service;

import myRetail.model.Product;
import myRetail.model.ProductDTO;
import myRetail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjorgeDevelopers on 1/1/17.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository pRepo;
    public Product save(Product product){
        if(product == null){
            return product;
        }
        if(product.sequence == null){
            List<Product>pList = pRepo.findAll();
            product.sequence = pList.size() + 1;
        }
        Product theReturn = pRepo.save(product);
        return theReturn;
    }
    public List<ProductDTO> getAllDTO(){
        List<Product> prod2 = pRepo.findAll();
        List<ProductDTO> returnList = new ArrayList<>();

        prod2.forEach(
                product -> {
                    returnList.add(ProductDTO.dtoFromProduct(product));
                }
        );
        return returnList;
    }
}
