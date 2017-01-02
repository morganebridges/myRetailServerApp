package myRetail.repository;

import java.math.BigInteger;
import java.util.List;

import myRetail.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByName(String firstName);
    public Product findBySequence(Integer firstName);
    public List<Product> findByPrice(String lastName);
    public Product findById(BigInteger id);
}
