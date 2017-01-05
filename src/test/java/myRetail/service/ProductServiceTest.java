package myRetail.service;

import com.mongodb.DBCollection;
import myRetail.model.DTO.ProductDTO;
import myRetail.model.Price;
import myRetail.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fjorgeDevelopers on 1/3/17.
 */
/*Remember that david told us we could include
    an "expected" param - as provided by junit
    this is how we perform negative tests and get
    our exceptions thrown for sucesss

 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;
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
        //DBCollection list = mongoTemp.getDb().getCollection();

        System.out.println("Look in the list");


    }

}