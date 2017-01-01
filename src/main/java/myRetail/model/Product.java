package myRetail.model;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    public String id;

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
                id, name, price);
    }

}