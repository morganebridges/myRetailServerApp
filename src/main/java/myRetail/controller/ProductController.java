package myRetail.controller;

import myRetail.repository.ProductRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import myRetail.model.Product;
import myRetail.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;


/**
 * Created by fjorgeDevelopers on 12/28/16.
 */
@EnableAutoConfiguration
@RequestMapping(path = "/product")
@RestController
public class ProductController {
    @Autowired
    ProductRepository pRepo;
    @RequestMapping(path = "/hello", method=RequestMethod.POST)
    String hello(@RequestBody String name) {
        return "Hello, " + name + "!";
    }
    @RequestMapping(path="gettest", method=RequestMethod.GET)
    Product getProductTest(){
        Product prod = new Product("Shoes", 34.12);
        Product prod2 = pRepo.save(prod);

        return prod2;
    }
    @RequestMapping(path="update", method = RequestMethod.POST)
    Product updateProduct(@RequestBody Product product){
        System.out.println(product.toString());
        String id = product.id;
        Product updateProd = pRepo.findById(id);
        System.out.println(updateProd.toString());
        updateProd.name = product.name;
        updateProd.price = product.price;
        Product savedProd = pRepo.save(updateProd);
        System.out.println(savedProd.toString());
        return savedProd;
    }
}
