package myRetail.model;

/**
 * Created by fjorgeDevelopers on 1/2/17.
 */
public class ProductDTO {
    private int  id;
    private String name;

    private Price price;
    public ProductDTO(){}

    public ProductDTO(int  id, String name, Price price){
        this. id =  id;
        this.name = name;
        this.price = price;
    }
    public ProductDTO(String name, Price price){
        this.name = name;
        this.price = price;
    }
    public static ProductDTO dtoFromProduct(Product product){
        return new ProductDTO(product.sequence, product.name, product.price);
    }

    public int getId() {
        return  id;
    }

    public void setId(int  id) {
        this. id =  id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

}
