package myRetail.model.DTO;

import myRetail.model.Price;
import myRetail.model.Product;

/**
 * A Data Transfer Object (DTO) class. This class is created so that there
 * we can easily limit the amount of information that is provided
 * through the API about a particular product, if we wish to add
 * more internal data to a project at a later date.
 */
public class ProductDTO {
    /* A ProductDTO considers it's 'id' to be what a Product instance
    * would consider it's 'sequence' field
    * */
    private int  id;
    private String name;

    private Price price;

    public ProductDTO(){}

    /**
     * A constructor - to be used for Products that already
     * exist in the system (since an ID (or Product.sequence))
     * has already been assigned.
     * @param id - A sequence number for an existing product.
     * @param name - The name of a product
     * @param price - The instance of a Price class that represents
     *              a Product's value and currency type.
     */
    public ProductDTO(int  id, String name, Price price){
        this. id =  id;
        this.name = name;
        this.price = price;
    }
    public ProductDTO(String name, Price price){
        this.name = name;
        this.price = price;
    }

    /**
     * @param product - A member of the Product class to be converted.
     * @return - A ProductDTO instance ready for API transmission
     */
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
