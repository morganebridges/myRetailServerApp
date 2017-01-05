package myRetail.controller;

import myRetail.model.DTO.ProductDTO;
import myRetail.repository.ProductRepository;
import myRetail.service.ProductService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import myRetail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by fjorgeDevelopers on 12/28/16.
 */
@EnableAutoConfiguration
@RequestMapping(path = "/products")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository pRepo;
    @Autowired
    private ProductService pService;

    @CrossOrigin
    @RequestMapping(path="/{sequence}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer sequence){
        System.out.println("Running get method");
        System.out.println(sequence);
        Product prod = pRepo.findBySequence(sequence);
        System.out.println(prod.toString());

        return new ResponseEntity<>(prod, HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(path="/getlist", method=RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> getProductTest(HttpServletResponse response){
        List<ProductDTO> returnList = pService.getAllDTO();
        if(returnList != null)
            return new ResponseEntity<>(returnList, HttpStatus.OK);
        return new ResponseEntity<>(returnList, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductDTO insertProd){
        //save to a product
        Product saveProd = pService.save(insertProd);
        //if no exceptions, turn back into DTO and return
        ProductDTO returnProd = pService.dtoFromProduct(saveProd);
        if(returnProd != null)
            return new ResponseEntity<ProductDTO>(returnProd, HttpStatus.OK);
        return new ResponseEntity<ProductDTO>(returnProd, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path="/update", method = RequestMethod.PUT)
    ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product){
        int searchId = product.getId();
        Product updateProd = pRepo.findBySequence(searchId);

        if(updateProd != null){
            System.out.println(updateProd.toString());
            updateProd.name = product.getName();
            updateProd.price = product.getPrice();

            Product savedProd = pService.save(updateProd);
            if(savedProd != null){
                System.out.println("Successfully saved");
                return new ResponseEntity<Product>(savedProd, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Product>(updateProd, HttpStatus.BAD_REQUEST);

    }

}
