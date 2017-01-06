package myRetail.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Product {

    @Id
    public String id;
    public Integer sequence;
    public String name;
    public Price price;

    public Product() {}

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }
    public Product(Integer sequence, String name, Price price) {
        this.name = name;
        this.price = price;
        this.sequence = sequence;
    }
    @Override
    public String toString() {
        return String.format(
                "Product[sequence=%s id=%s, Name='%s', Price='%s']",
                sequence, id, name, price);
    }

}