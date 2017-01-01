package myRetail.controller;

import com.sun.org.apache.regexp.internal.RE;
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
@RestController
public class HelloController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String hello() {
        return "Hello";
    }
}
