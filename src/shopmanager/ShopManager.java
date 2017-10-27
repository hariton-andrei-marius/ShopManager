package shopmanager;

import threads.Main;

/**
 * Main class of the program
 * @author Hariton Andrei Marius
 */
public class ShopManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread t = new Thread(new Main());
        t.start();
        
    }
    
}
