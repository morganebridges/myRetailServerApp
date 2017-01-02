package myRetail.controller;

import myRetail.repository.ProductRepository;
import myRetail.service.ProductService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import myRetail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fjorgeDevelopers on 12/28/16.
 */
@EnableAutoConfiguration
@RequestMapping(path = "/products")
@RestController
public class ProductController {

    @Autowired
    ProductRepository pRepo;
    @Autowired
    ProductService pService;

    @RequestMapping(path="/{sequence}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer sequence, HttpServletResponse response){
        System.out.println("Running get method");
        System.out.println(sequence);
        Product prod = pRepo.findBySequence(sequence);
        System.out.println(prod.toString());

        if(prod != null)
            return new ResponseEntity<>(prod, HttpStatus.OK);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

   /* @RequestMapping(path="/{ids}", method=RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(@RequestParam  List<String> ids, HttpServletResponse response){
        List<Product> returnList = new ArrayList<>();
        ids.forEach(
                id -> {
                    Product itProd = pRepo.findById(id);
                    if(itProd != null)
                        returnList.add(itProd);
                }
        );
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }*/

    @RequestMapping(path = "/hello", method=RequestMethod.POST)
    String hello(@RequestBody String name ) {
        return "Hello, " + name + "!";
    }

    @RequestMapping(path="/getlist", method=RequestMethod.GET)
    ResponseEntity<List<Product>> getProductTest(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        List<Product> prod2 = pRepo.findAll();
        if(prod2 != null)
            return new ResponseEntity<>(prod2, HttpStatus.OK);
        return new ResponseEntity<>(prod2, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    ResponseEntity<Product> insertProduct(Product insertProd, HttpServletResponse response){
        Product returnProd = pRepo.insert(insertProd);

        if(returnProd != null)
            return new ResponseEntity<Product>(returnProd, HttpStatus.OK);
        return new ResponseEntity<Product>(returnProd, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path="/update", method = RequestMethod.PUT)
    ResponseEntity<Product> updateProduct(@RequestBody Product product){
        System.out.println(product.toString());
        Integer sequence = product.sequence;
        Integer sequence = product.sequence;
        Product updateProd = pRepo.findById(sequence);
        if(updateProd != null){
            System.out.println(updateProd.toString());
            updateProd.name = product.name;
            updateProd.price = product.price;
            Product savedProd = pService.save(updateProd);
            if(savedProd != null){
                System.out.println("Successfully saved");
                return new ResponseEntity<Product>(savedProd, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Product>(updateProd, HttpStatus.BAD_REQUEST);

    }

}
