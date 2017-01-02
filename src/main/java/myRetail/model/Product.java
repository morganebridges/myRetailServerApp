package myRetail.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Product {

    public int id;
    @Id
    public Integer sequence;
    public String name;
    public Price price;

    public Product() {}

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, Name='%s', Price='%s']",
                sequence, name, price);
    }

}