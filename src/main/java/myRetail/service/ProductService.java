package myRetail.service;

import myRetail.model.Product;
import myRetail.model.DTO.ProductDTO;
import myRetail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A services layer class to handle the business logic interactions
 * between controllers and data layer.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository pRepo;


    public Product save(Product product) throws IllegalArgumentException{
        if(product == null){
            throw new IllegalArgumentException("Attempting to save a null product");
        }

        if(product.sequence == null){
            List<Product>pList = pRepo.findAll();
            product.sequence = pList.size() + 1;
        }
        Product theReturn = pRepo.save(product);
        return theReturn;
    }

    /**
     * Saves a product DTO into the products table.
     * @param prodDTO - A data transfer object, usually directly from
     *                the API for the purposes of
     * @return
     */
    public Product save(ProductDTO prodDTO){

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
