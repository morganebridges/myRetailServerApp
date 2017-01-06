package myRetail.service;
import myRetail.model.DTO.ProductDTO;
import myRetail.model.Price;
import myRetail.model.Product;
import myRetail.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.HashMap;
import java.util.List;

/**
 * A class created to test our product class' services layer.
 */

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testUpdatingFromDto() throws Exception {
        ProductDTO dto1 = new ProductDTO(1, "TEST Prod 1", new Price(13.35, "USD"));
        Product dto1Prod = productService.save(dto1);
        ProductDTO dto2 = new ProductDTO(1, "TEST Prod 1.2", new Price(13.39, "CAN"));
        Product dto2Prod = productService.save(dto2);
        logger.warn("TEST: Assert  Updating Product model from DTO");
        //If the same UID exists on both objects, we are properly updating.
        assert(dto1Prod.id.equals(dto2Prod.id));
        if(dto1Prod.id.equals(dto2Prod.id))
            logger.warn("TEST: Updating product from DTO - Passed");

        //test that the same is true when we
    }
    /**
     * This test ensures that we are throwing an exception if we try to save a product with an out
     * of bounds ID (not generated by our sequence method in the product service), it will throw an exception.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveNonExistDTO() throws Exception{
        ProductDTO dto2 = new ProductDTO(productService.getAllDTO().size() + 2, "Invalid sequence bounds", new Price(24.2, "USD"));
        //create a productDTO, then arbitrarily set the sequence number to zero, which shouldn't ever exist.
        try{
            productService.save(dto2);
        }catch(IllegalArgumentException e){
            logger.warn("TEST: Saving out of bounds ID should throw an exception(Instantiation) - PASS");
            throw e;
        }



    }
    /**
     * This test ensures that we are throwing an exception if we try to save invalid products (once with a
     * 0 sequence- illegal with the system.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveInvalidId() throws Exception{
        //create a productDTO, then arbitrarily set the sequence number to zero, which shouldn't ever exist.
        try{
            ProductDTO dto = new ProductDTO(0, "Invalid 0 sequence", new Price(13.35, "USD"));
            productService.save(dto);
        }catch(IllegalArgumentException e){
            logger.warn("TEST: Saving 0 ID should throw an exception(Instantiation) - PASS");
            throw e;
        }

    }
    /**
     * This test makes sure that our list of DTOS maps correctly to the persistent collection of Product
     * abstractions.
     * @throws Exception
     */
    @Test
    public void getAllDTO() throws Exception {
        logger.warn("Testing that getting all products for DTO transfer is valid");
        //A list of all products
        Product p1 = new Product("Prod 1", new Price(1.25, "USD"));
        p1 = productService.save(p1);
        logger.warn(p1.toString());

        Product p2 = new Product("Prod 2", new Price(1.40, "USD"));
        p2 = productService.save(p2);
        logger.warn(p2.toString());

        List<Product> pList = productRepository.findAll();
        HashMap<Integer, Product> prodMap = new HashMap<>();
        //Create a map of sequences numbers to products, consequently
        pList.forEach(
                product -> {

                    prodMap.put(product.sequence, product);
            }
        );
        /*A list of product DTOs from the services layer, should match
         * the list from the repository.
         */
        List<ProductDTO> dtoList = productService.getAllDTO();
        dtoList.forEach(
              dto ->{
                  logger.warn("Asserting validity:" + dto.toString());
                  /*for each of our dtos, there should be a corresponding
                   * entry in the hashmap based on sequence key */
                  assert(prodMap.keySet().contains(dto.getId()));
              }
        );

    }

}