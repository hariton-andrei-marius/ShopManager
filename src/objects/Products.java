package objects;

import exceptions.FileException;
import exceptions.ProductsException;
import java.io.IOException;

/**
 * Class for the list of the products
 * @author Hariton Andrei Marius
 */
public class Products implements java.io.Serializable {
    private java.util.TreeMap<String,Product> products;
    private double earnings;
    private double spending;
    
    /**
     * Empty constructor for products
     */
    public Products() {
        products = new java.util.TreeMap<>();
        earnings = 0.0;
        spending = 0.0;
    }
    
    /**
     * Adds a product
     * @param p The product
     * @throws ProductsException Product's exception
     * @throws CloneNotSupportedException Clone not supported exception
     * @throws NullPointerException Null pointer exception
     */
    public void addProduct(Product p)
            throws ProductsException,
            CloneNotSupportedException, NullPointerException {
        if(!products.containsKey(p.getCode())) {
            products.put(p.getCode(), (Product) p.clone());
            spending += products.get(p.getCode()).getPrice();
        } else {
            throw new ProductsException("Already exists");
        }
    }
    
    /**
     * Removes a product
     * @param code The product's code
     * @throws ProductsException Product's exception
     */
    public void removeProduct(String code) throws ProductsException {
        if(products.containsKey(code)) {
            if(products.get(code).getType() != "[SOLD]") {
                earnings += products.get(code).getPrice();
            }
            products.remove(code);
        } else {
            throw new ProductsException("Not found");
        }
    }
    
    /**
     * Sell a product: put its type on "[SOLD]" and increase earnings
     * @param code The product's code
     * @throws ProductsException Product's exception
     */
    public void sellProduct(String code) throws ProductsException {
        if(products.containsKey(code)) {
            earnings += products.get(code).getPrice();
            products.get(code).setType("[SOLD]");
        } else {
            throw new ProductsException("Not found");
        }
    }
    
    /**
     * Searches a product by code
     * @param code The product's code
     * @return The searched product if it exists
     * @throws ProductsException Product's exception
     * @throws CloneNotSupportedException Clone not supported exception
     */
    public Product searchProduct(String code)
            throws ProductsException, CloneNotSupportedException {
        if(products.containsKey(code)) {
            return (Product) products.get(code);//deleted ".clone()"
        }
        throw new ProductsException("Not found");
    }
    
    /**
     * Filters products by name
     * @param name The product's name
     * @return An ArrayList of Products
     * @throws CloneNotSupportedException Clone not supported exception
     */
    public java.util.ArrayList<Product> filterByName(String name)
            throws CloneNotSupportedException {
        java.util.ArrayList<Product> productsByName;
        productsByName = new java.util.ArrayList<>(products.size());
        for(Product p : products.values()) {
            if(p.getName().equals(name)) productsByName.add((Product)p.clone());
        }
        productsByName.trimToSize();
        return productsByName;
    }
    
    /**
     * Filters products by type
     * @param type The product's type
     * @return An ArrayList of Products
     * @throws CloneNotSupportedException Clone not supported exception
     */
    public java.util.ArrayList<Product> filterByType(String type)
            throws CloneNotSupportedException {
        java.util.ArrayList<Product> productsByType;
        productsByType = new java.util.ArrayList<>(products.size());
        for(Product p : products.values()) {
            if(p.getType().equals(type)) productsByType.add((Product)p.clone());
        }
        productsByType.trimToSize();
        return productsByType;
    }
    
    /**
     * Filters products by type
     * @param expires The product's type
     * @return An ArrayList of Products
     * @throws CloneNotSupportedException Clone not supported exception
     */
    public java.util.ArrayList<Product> filterByExpireDate(String expires)
            throws CloneNotSupportedException {
        java.util.ArrayList<Product> productsByExpireDate;
        productsByExpireDate = new java.util.ArrayList<>(products.size());
        for(Product p : products.values()) {
            if(p.getExpireDate().equals(expires)) productsByExpireDate.add(
                    (Product)p.clone()
            );
        }
        productsByExpireDate.trimToSize();
        return productsByExpireDate;
    }
    
    /**
     * Getter for a list with all products
     * @return Objects list of products
     * @throws java.lang.CloneNotSupportedException Clone not supported
     */
    public java.util.ArrayList<Product> getElements()
            throws CloneNotSupportedException {
        java.util.ArrayList<Product> productsList;
        productsList = new java.util.ArrayList<>(products.size());
        for(Product p : products.values()) {
            productsList.add((Product)p.clone());
        }
        productsList.trimToSize();
        return productsList;
    }
    
    /**
     * Getter for a list with not sold products
     * @return Objects list of products
     * @throws java.lang.CloneNotSupportedException Clone not supported
     */
    public java.util.ArrayList<Product> getNotSoldElements()
            throws CloneNotSupportedException {
        java.util.ArrayList<Product> productsList;
        productsList = new java.util.ArrayList<>(products.size());
        for(Product p : products.values()) {
            if(p.getType()!="[SOLD]") {
                productsList.add((Product)p.clone());
            }
        }
        productsList.trimToSize();
        return productsList;
    }

    /**
     * Getter for earnings
     * @return Earnings
     */
    public double getEarnings() {
        return earnings;
    }
    
    /**
     * Getter for spending
     * @return Spending
     */
    public double getSpending() {
        return spending;
    }

    /**
     * Setter for earnings
     * @param earnings Earnings
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
    
    /**
     * Setter for spending
     * @param spending Spending
     */
    public void setSpending(double spending) {
        this.spending = spending;
    }
    
    /**
     * To get the lenght
     * @return The number of products stored
     */
    public int lenght() {
        return products.size();
    }

    /**
     *  Save the current object to a binary file
     * @param name Filename
     * @throws IOException Input output exception
     */
    public void saveToBinaryFile(String name) throws java.io.IOException {
        java.io.ObjectOutputStream stream;
        stream = new java.io.ObjectOutputStream(
                    new java.io.FileOutputStream(name)
        );
        stream.writeObject(products);
        stream.writeObject(earnings);
        stream.writeObject(spending);
        stream.close();
    }

    /**
     * Restores the current object from a binary file
     * @param name Filename
     * @throws IOException Input output exception
     * @throws FileException File exception
     */
    public void restoreFromBinaryFile(String name)
            throws java.io.IOException, FileException {
        java.io.ObjectInputStream stream;
        stream = new java.io.ObjectInputStream(
                    new java.io.FileInputStream(name)
        );
        try {
            products = (java.util.TreeMap<String,Product>) stream.readObject();
            earnings = (double) stream.readObject();
            spending = (double) stream.readObject();
        } catch(ClassNotFoundException ex) {}
        stream.close();
    }
    
    /**
     * Saves to text file
     * @param filename Filename
     * @throws CloneNotSupportedException Clone not supported exception
     * @throws java.io.IOException Input output exception
     * @throws exceptions.ProductsException Products exception
     */
    public void exportToTextFile(String filename)
            throws CloneNotSupportedException, IOException, ProductsException {
        if(lenght() > 4000) throw new ProductsException("Too many products.");
        java.io.FileWriter file = null;
        java.io.PrintWriter stream = null;
        String stringOfProducts = "";
        for(Product p : products.values()) {
            stringOfProducts += p.toStringWithDescription();
        }
        file = new java.io.FileWriter(filename+".txt");
        stream = new java.io.PrintWriter(file);
        stream.print(stringOfProducts);
        stream.flush();
        file.close();
    }
    
    /**
     * Saves to JSON file
     * @param filename Filename
     * @throws CloneNotSupportedException Clone not supported exception
     * @throws java.io.IOException Input output exception
     * @throws exceptions.ProductsException Products exception
     */
    public void exportToJsonFile(String filename)
            throws CloneNotSupportedException, IOException, ProductsException {
        if(lenght() > 4000) throw new ProductsException("Too many products.");
        java.io.FileWriter file = null;
        java.io.PrintWriter stream = null;
        String stringOfProducts = "[";
        for(Product p : products.values()) {
            stringOfProducts += "\n\t{";
            stringOfProducts += "name:"+"\""+p.getName()+"\""+", ";
            stringOfProducts += "type:"+"\""+p.getType()+"\""+", ";
            stringOfProducts += "price:"+"\""+p.getPrice()+"\""+", ";
            stringOfProducts += "expires:"+"\""+p.getExpireDate()+"\""+", ";
            stringOfProducts += "description:"+"\""+p.getDescription()+"\""+", ";
            stringOfProducts += "id:"+"\""+p.getCode()+"\"";
            stringOfProducts += "}";
            if(!products.lastKey().equals(p.getCode())) {
                stringOfProducts += ",";
            }
        }
        stringOfProducts += "\n]";
        file = new java.io.FileWriter(filename+".json");
        stream = new java.io.PrintWriter(file);
        stream.print(stringOfProducts);
        stream.flush();
        file.close();
    }
    
    /**
     * Imports all products from a JSON file
     * @param filename Filename
     * @throws CloneNotSupportedException Clone not supported exception
     * @throws java.io.IOException Input output exception
     * @throws exceptions.ProductsException Product exception
     */
    public void importFromJsonFile(String filename)
            throws CloneNotSupportedException, IOException,
            ProductsException, NullPointerException {
        // READING FROM FILE
        java.io.FileReader file = null;
        java.io.BufferedReader stream = null;
        String content = new String("");
        String temporary, name, type, expires, description;
        Double price;
        // FILE
        file = new java.io.FileReader(filename+".json");
        stream = new java.io.BufferedReader(file);
        temporary = stream.readLine();
        while(temporary != null){
            content += temporary;
            temporary = stream.readLine();
            if(temporary != null) content += "\n";
        }
        file.close();
        // FROM STRING TO TREEMAP
        java.util.StringTokenizer st = new java.util.StringTokenizer(content,"\"");
        while(st.nextToken() != null && st.hasMoreElements() == true) {
            name = st.nextToken();
            st.nextToken();
            type = st.nextToken();
            st.nextToken();
            price = Double.valueOf(st.nextToken());
            st.nextToken();
            expires = st.nextToken();
            st.nextToken();
            description = st.nextToken();
            st.nextToken();
            st.nextToken();
            this.addProduct(new Product(name,type,price,expires,description));
        }
    }
    
    /**
     * Makes a backup of the JSON file with products
     * @param filename Filename
     * @throws CloneNotSupportedException Clone not supported exception
     * @throws java.io.IOException Input output exception
     */
    public void backupFromJsonFile(String filename)
            throws CloneNotSupportedException, IOException {
        // Reading
        java.io.FileReader file = null;
        java.io.BufferedReader stream = null;
        String s="";
        String temporary;
        // FILE
        file = new java.io.FileReader(filename+".json");
        stream = new java.io.BufferedReader(file);
        temporary = stream.readLine();
        while(temporary != null){
            s += temporary;
            temporary = stream.readLine();
            if(temporary != null) s += "\n";
        }
        file.close();
        // Saving
        java.io.FileWriter f = null;
        java.io.PrintWriter out = null;
        // FILE
        f = new java.io.FileWriter(filename+".backup.json");
        out = new java.io.PrintWriter(f);
        out.print(s);
        out.flush();
        f.close();
    }
    
    /**
     * Saves to HTML file
     * @param filename Filename
     * @throws CloneNotSupportedException Clone not supported exception
     * @throws java.io.IOException Input output exception
     * @throws exceptions.ProductsException Products exception
     */
    public void exportToHtmlFile(String filename)
            throws CloneNotSupportedException, IOException, ProductsException {
        if(lenght() > 4000) throw new ProductsException("Too many products.");
        java.io.FileWriter file = null;
        java.io.PrintWriter stream = null;
        String htmlString = "<!DOCTYPE html><!--Made by ShopManager--><html>"
                + "<head><style>table{margin: 0 auto;border-top:1px solid black;"
                + "border-left:1px solid black;}td{border-bottom:1px solid black;"
                + "border-right:1px solid black;text-align:center;}</style>"
                + "</head><body><table border=\"0\" cellspacing=\"0\" "
                + "cellpadding=\"0\"><colgroup><col width=\"124\"/>"
                + "<col width=\"124\"/><col width=\"124\"/><col width=\"124\"/>"
                + "<col width=\"124\"/><col width=\"124\"/></colgroup><tr><td>"
                + "<b>Name</b></td><td><b>Type</b></td><td><b>Price</b></td><td>"
                + "<b>Expires</b></td><td><b>Description</b></td><td><b>ID</b>"
                + "</td></tr>";
        for(Product p : products.values()) {
            htmlString += "<tr>";
            htmlString += "<td>"+p.getName()+"</td>";
            htmlString += "<td>"+p.getType()+"</td>";
            htmlString += "<td>"+p.getPrice()+"</td>";
            htmlString += "<td>"+p.getExpireDate()+"</td>";
            htmlString += "<td>"+p.getDescription()+"</td>";
            htmlString += "<td>"+p.getCode()+"</td>";
            htmlString += "</tr>";
        }
        htmlString += "</table></body></html>";
        file = new java.io.FileWriter(filename+".html");
        stream = new java.io.PrintWriter(file);
        stream.print(htmlString);
        stream.flush();
        file.close();
    }
}
