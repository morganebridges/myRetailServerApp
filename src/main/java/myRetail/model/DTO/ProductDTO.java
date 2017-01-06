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
    /* A ProductDTO's id field mapps to the Product model's "sequence" field
    * */
    private Integer  id;
    private String name;
    private Price price;
    public ProductDTO(){}

    /**
     * A constructor - to be used for Products that already
     * exist in the system (since an ID (or Product.sequence))
     * has already been assigned.
     *
     * precondition : A product represented by this object has been saved in the database, with
     *  a sequence number assigned via our Product service.
     *
     * @param id - A sequence number for an existing product.
     * @param name - The name of a product
     * @param price - The instance of a Price class that represents
     *              a Product's value and currency type.
     */
    public ProductDTO(int  id, String name, Price price){
        if(id==0)
            throw new IllegalArgumentException("Invalid ID provided");
        this. id =  id;
        this.name = name;
        this.price = price;
    }
    /**
     * A constructor - to be used primarily for products that
     * do not yet have sequences
     * @param name
     * @param price
     */
    public ProductDTO(String name, Price price){
        this.id = 0;
        this.name = name;
        this.price = price;
    }
    public ProductDTO(Product product){
        if(product.sequence == 0)
            throw new IllegalArgumentException("Provided product had an invalid sequence value");
        this.id = product.sequence;
        this.name = product.name;
        this.price = product.price;
    }
    public int getId() {
        return  id;
    }

    public void setId(int id) {
        if(id == 0)
            throw new IllegalArgumentException("Provided product had an invalid sequence value");
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

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, Name='%s', Price='%s']",
                id, name, price);
    }
}
