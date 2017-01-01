package myRetail.model;

import org.springframework.data.annotation.Id;


public class Product {

    @Id
    public String id;

    public String name;
    public double price;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, Name='%s', Price='%s']",
                id, name, price);
    }

}