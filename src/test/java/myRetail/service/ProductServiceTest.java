package myRetail.service;

import myRetail.model.DTO.ProductDTO;
import myRetail.model.Price;
import myRetail.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    ProductService productService = new ProductService();

    @Test
    public void save() throws Exception {
        ProductDTO dto1 = new ProductDTO(1, "Test Prod 1", new Price(13.35, "USD"));
        Product dto1Prod = productService.save(dto1);
        System.out.println(dto1Prod.toString());
        ProductDTO dto2 = new ProductDTO(105, "Test Prod 2", new Price(13.39, "CAN"));
        System.out.println(dto1.toString());
        Product dto2Prod = productService.save(dto2);
        System.out.println(dto2Prod.toString());

        //Test that we properly handled an update instead of
        //Creating a new row.
        assert(dto1Prod.id.equals(dto2Prod.id)  );

        //test that the same is true when we


    }

    @Test
    public void getAllDTO() throws Exception {

    }

}