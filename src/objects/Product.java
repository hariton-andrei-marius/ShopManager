package objects;

import exceptions.ProductsException;

/**
 * Single product class
 * @author Hariton Andrei Marius
 */
public class Product implements java.io.Serializable, Cloneable {
    private String name;
    private String type;
    private double price;
    private String expires;
    private String id;
    private String description;
    
    /**
     * Constructor with all parameters
     * @param name The product's name
     * @param type The product's type
     * @param price The product's price
     * @param expires The product's expire date
     * @param description The product's description
     */
    public Product(String name, String type,
            double price, String expires, String description) {
        try {
            setName(name);
            setType(type);
            setPrice(price);
            setExpireDate(expires);
            setDescription(description);
            id=name.toUpperCase().replaceAll("\\s+","")
            +String.valueOf(
                    new java.util.Random().nextInt(1999999999-10+1)+10
            )+String.valueOf(
                    new java.util.Random().nextInt(1999999999-10+1)+10
            )+String.valueOf(
                    new java.util.Random().nextInt(1999999999-10+1)+10
            );
        } catch(ProductsException ex) {}
    }
    
    public String getCode() {
        return id;
    }
    
    /**
     * Getter for the product's name
     * @return The name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the product's type
     * @return The type of the product
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the product's price
     * @return The price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for the product's expire date
     * @return The expire date of the product
     */
    public String getExpireDate() {
        return expires;
    }

    /**
     * Getter for the product's description
     * @return The description of the product
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Setter for the product's name
     * @param name The name of the product
     * @throws exceptions.ProductsException Product's exception
     */
    public void setName(String name) throws ProductsException {
        if(name.isEmpty())      throw new ProductsException("Empty name");
        if(name.length() < 3)   throw new ProductsException("Name too short");
        if(name.length() > 20)  throw new ProductsException("Name too long");
        this.name = name;
    }

    /**
     * Setter for the product's type
     * @param type The type of the product
     * @throws exceptions.ProductsException Product's exception
     */
    public void setType(String type) throws ProductsException {
        if(type.isEmpty())      throw new ProductsException("Empty type");
        if(type.length() > 15)  throw new ProductsException("Type too long");
        this.type = type;
    }

    /**
     * Setter for the product's type
     * @param price The price of the product
     * @throws exceptions.ProductsException Product's exception
     */
    public void setPrice(double price) throws ProductsException {
        if(price <= 0) throw new ProductsException("Impossible price");
        this.price = price;
    }

    /**
     * Setter for the product's expire date
     * @param expires The expire date of the product
     * @throws exceptions.ProductsException Product's exception
     */
    public void setExpireDate(String expires) throws ProductsException {
        if(expires.length() > 20) throw new ProductsException("Wrong exp. date");
        this.expires = expires;
    }
    
    /**
     * Setter for the product's description
     * @param description The description of the product
     * @throws exceptions.ProductsException Product's exception
     */
    public void setDescription(String description) throws ProductsException {
        if(description.length() > 600){
            throw new ProductsException(
                    "Description too long("
                    +description.length()
                    +")"
            );
        }
        this.description = description;
    }
    
    /**
     * To string override
     * @return String without description
     */
    @Override
    public String toString() {
        return " NAME: "+name+"\t"
            +"TYPE: "+type+"\t"
            +"PRICE: "+String.valueOf(price)+"\t"
            +"EXPIRE DATE: "+expires+"\t"
            +"ID: "+this.getCode()+"\n";
    }
    
    /**
     * To string with description
     * @return String with description
     */
    public String toStringWithDescription() {
        return " NAME: "+name+"\t"
            +"TYPE: "+type+"\t"
            +"PRICE: "+String.valueOf(price)+"\t"
            +"EXPIRE DATE: "+expires+"\t"
            +"ID: "+this.getCode()+"\n"
            +"\t\tDESCRIPTION: "+this.getDescription()+"\n";
    }
    
    /**
     * Makes the "Product" objects clone-able
     * @return A clone of the current "Product" object
     * @throws CloneNotSupportedException Clone not supported exception
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Product p = (Product) super.clone();
        return p;
    }
}
