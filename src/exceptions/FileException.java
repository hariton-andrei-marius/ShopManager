package exceptions;

import gui.Notify;

/**
 * File's exception class
 * @author Hariton Andrei Marius
 */
public class FileException extends Exception {

    /**
     * Constructor with generic message
     */
    public FileException() {
        super("Generic file exception");
        
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
    public FileException(String ex) {
        super(ex);
        
        // Graphical
        Notify n = new Notify();
        n.setMessage(this.toString());
        n.setLocation(400,200);
        n.setVisible(true);
    }
}
