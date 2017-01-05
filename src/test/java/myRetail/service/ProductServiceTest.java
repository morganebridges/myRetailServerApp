package myRetail.service;
import myRetail.model.DTO.ProductDTO;
import myRetail.model.Price;
import myRetail.model.Product;
import myRetail.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MongoTemplate mongoTemp;


    @Test
    public void testUpdatingFromDto() throws Exception {
        ProductDTO dto1 = new ProductDTO(1, "Test Prod 1", new Price(13.35, "USD"));
        Product dto1Prod = productService.save(dto1);
        ProductDTO dto2 = new ProductDTO(1, "Test Prod 1.2", new Price(13.39, "CAN"));
        Product dto2Prod = productService.save(dto2);

        //If the same UID exists on both objects, we are properly updating.
        assert(dto1Prod.id.equals(dto2Prod.id)  );

        //test that the same is true when we
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNonExistDTO() throws Exception{
        //create a productDTO, then arbitrarily set the sequence number to zero, which shouldn't ever exist.
        ProductDTO dto = new ProductDTO(0, "Invalid 0 sequence", new Price(13.35, "USD"));
        //Trying to save a product with an invalid sequence number should throw an exception.
        try{
            productService.save(dto);
        }catch(IllegalArgumentException e){
            //If we've reached this block, we have the desired result.
            assert(true);
        }
        //Create a DTO that is
        ProductDTO dto2 = new ProductDTO(productService.getAllDTO().size() + 2, "Invalid sequence bounds", new Price(24.2, "USD"));
        productService.save(dto2);

    }

    @Test
    public void getAllDTO() throws Exception {
        //A list of all products
        Product p1 = new Product("Prod 1", new Price(1.25, "USD"));
        p1 = productService.save(p1);
        System.out.println(p1.toString());

        Product p2 = new Product("Prod 2", new Price(1.40, "USD"));
        p2 = productService.save(p2);
        System.out.println(p2.toString());

        List<Product> pList = productRepository.findAll();
        HashMap<Integer, Product> prodMap = new HashMap<>();
        //Create a map of sequences numbers to products, consequently
        pList.forEach(
                product -> {
                    System.out.println("Mapping:" + product.toString());
                    prodMap.put(product.sequence, product);
            }
        );
        /*A list of product DTOs from the services layer, should match
         * the list from the repository.
         */
        List<ProductDTO> dtoList = productService.getAllDTO();
        dtoList.forEach(
              dto ->{
                  System.out.println("Asserting validity:" + dto.toString());
                  /*for each of our dtos, there should be a corresponding
                   * entry in the hashmap based on sequence key */
                  assert(prodMap.keySet().contains(dto.getId()));
              }
        );

    }

}