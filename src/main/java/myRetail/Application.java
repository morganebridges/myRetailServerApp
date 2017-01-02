package myRetail;

import java.util.Arrays;

import myRetail.model.Price;
import myRetail.model.Product;
import myRetail.repository.ProductRepository;
import myRetail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    ProductRepository pRepo;
    @Autowired
    ProductService pService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            System.out.println("Clearing out the repository");
            pRepo.deleteAll();

            System.out.println("Adding shoes");
            pService.save(new Product("Shoes", new Price(43.21, "USD")));

            System.out.println("Adding pants");
            pService.save(new Product("Pants", new Price(65.72, "USD")));

            System.out.println("Adding gloves");
            pService.save(new Product("Gloves", new Price(17.72, "USD")));

            System.out.println("Adding shoes");
            pService.save(new Product("Hat", new Price(9.72, "USD")));

            System.out.println("Adding watch");
            pService.save(new Product("Hat", new Price(569.72, "USD")));




        };
    }


}