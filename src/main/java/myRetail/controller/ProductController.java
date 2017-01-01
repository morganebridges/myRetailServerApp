package myRetail.controller;

import myRetail.repository.ProductRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import myRetail.model.Product;
import myRetail.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    /*@RequestMapping(path="/")
    public ResponseEntity<Product> getProduct(@RequestBody String id, HttpServletRequest request, HttpServletResponse response){
        Product prod = pRepo.findById(id);
        if(prod != null)
            return new ResponseEntity<>(prod, HttpStatus.OK);;
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }*/

    @RequestMapping(path="/")
    public ResponseEntity<List<Product>> getProducts(@RequestBody List<String> ids, HttpServletResponse response){
        List<Product> returnList = new ArrayList<>();
        ids.forEach(
                id -> {
                    Product itProd = pRepo.findById(id);
                    if(itProd != null)
                        returnList.add(itProd);
                }
        );
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @RequestMapping(path = "/hello", method=RequestMethod.POST)
    String hello(@RequestBody String name ) {
        return "Hello, " + name + "!";
    }

    @RequestMapping(path="/getlist", method=RequestMethod.GET)
    ResponseEntity<List<Product>> getProductTest(HttpServletResponse response){
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
        String id = product.id;
        Product updateProd = pRepo.findById(id);
        if(updateProd != null){
            System.out.println(updateProd.toString());
            updateProd.name = product.name;
            updateProd.price = product.price;
            Product savedProd = pRepo.save(updateProd);
            if(savedProd != null){
                return new ResponseEntity<Product>(savedProd, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Product>(updateProd, HttpStatus.BAD_REQUEST);

    }

}
