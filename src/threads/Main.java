package threads;

import gui.Manager;
import objects.Products;

/**
 * Main thread class
 * @author Hariton Andrei Marius
 */
public class Main implements Runnable {
    Products products;
    Manager manager;
    
    /**
     * The constructor of the main thread
     */
    public Main() {
        products = new Products();
        manager = new Manager();
    }
    
    /**
     * The run method of the thread
     */
    @Override
    public void run() {
        manager.setProducts(products);
        manager.setThreadName(Thread.currentThread().toString());
        manager.setLocation(5, 5);
        manager.setVisible(true);
    }
}
