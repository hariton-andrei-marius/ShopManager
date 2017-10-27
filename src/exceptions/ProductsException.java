package exceptions;

import gui.Notify;

/**
 * Products exception class
 * @author Hariton Andrei Marius
 */
public class ProductsException extends Exception {

    /**
     * Constructor with generic message
     */
    public ProductsException() {
        super("Generic products exception");
        
        // Graphical
        Notify n = new Notify();
        n.setMessage(this.toString());
        n.setLocation(400,200);
        n.setVisible(true);
    }

    /**
     * Constructor with custom message
     * @param ex The exception's message
     */
    public ProductsException(String ex) {
        super(ex);
        
        // Graphical
        Notify n = new Notify();
        n.setMessage(this.toString());
        n.setLocation(400,200);
        n.setVisible(true);
    }
}
