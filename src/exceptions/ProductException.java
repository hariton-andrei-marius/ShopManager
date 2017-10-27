package exceptions;

import gui.Notify;

/**
 * Product's exception class
 * @author Hariton Andrei Marius
 */
public class ProductException extends Exception {

    /**
     * Constructor with generic message
     */
    public ProductException() {
        super("Generic product exception");
        
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
    public ProductException(String ex) {
        super(ex);
        
        // Graphical
        Notify n = new Notify();
        n.setMessage(this.toString());
        n.setLocation(400,200);
        n.setVisible(true);
    }
}
