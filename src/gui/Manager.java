package gui;

import exceptions.FileException;
import exceptions.ProductsException;
import objects.Product;
import objects.Products;
import threads.Main;

/**
 * Window frame for the manager
 * @author Hariton Andrei Marius
 */
public class Manager extends javax.swing.JFrame {
    // TreeMap of Products
    private Products products;
    // Visualization of products limit
    private int maxSeenProducts;
    // Archive index
    private int archiveIndex;
    // Thread's name
    private String threadName;
    // (Reporting) Export or import's filename
    private String filename;
    
    /**
     * Creates new form Manager
     */
    public Manager() {
        initComponents();
    }
    
    /**
     * Setter for products
     * @param products The products
     */
    public void setProducts(Products products) {
        this.products = products;
    }

    /**
     * Setter for thread's name
     * @param name The thread's name
     */
    public void setThreadName(String name) {
        threadName = name;
    }
    
    /**
     * Setter for export's filename
     * @param name The export's filename
     */
    public void setFilename(String name) {
        filename = name;
    }
    
    /**
     * Getter for export's filename
     * @return Filename
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Cleans the Controls panel
     */
    public void cleanControls() {
        filterByNameTextField.setText("");
        filterByTypeTextField.setText("");
        filterByExpireDateTextField.setText("");
        searchByCodeTextField.setText("");
    }
    
    /**
     * Updates the list's TextArea
     */
    public void updateList() {
        // Time
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat df;
        df = new java.text.SimpleDateFormat("HH:mm:ss  dd-MM-yyyy");
        // Printing
        browser.setText(" ALL PRODUCTS ("+df.format(date)+"):\n\n");
        java.util.ArrayList<Product> productsList;
        try {
            int lastArchiveIndex=archiveIndex;
            productsList = products.getElements();
            while(archiveIndex<productsList.size()
                    && archiveIndex<maxSeenProducts+1)
            {
                browser.setText(browser.getText()
                    +(Product)productsList.get(archiveIndex));
                archiveIndex++;
            }
            if(archiveIndex>maxSeenProducts) {
                browser.setText(browser.getText()
                    +"\nTHERE ARE OTHER "
                    +(productsList.size()-archiveIndex)
                    +" PRODUCTS !!!");
            }
            archiveIndex=lastArchiveIndex;
        } catch(CloneNotSupportedException ex) {}
        browser.setText(browser.getText()+"\n LAST VIEW UPDATE: ");
        // Time printing
        browser.setText(browser.getText()+df.format(date));
    }
    
    /**
     * Updates the list's TextArea with not sold products
     */
    public void updateNotSoldList() {
        // Time
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat df;
        df = new java.text.SimpleDateFormat("HH:mm:ss  dd-MM-yyyy");
        // Printing
        browser.setText(" ALL PRODUCTS ("+df.format(date)+"):\n\n");
        java.util.ArrayList<Product> productsList;
        try {
            int lastArchiveIndex=archiveIndex;
            productsList = products.getNotSoldElements();
            while(archiveIndex<productsList.size()
                    && archiveIndex<maxSeenProducts+1)
            {
                browser.setText(browser.getText()
                    +(Product)productsList.get(archiveIndex));
                archiveIndex++;
            }
            if(archiveIndex>maxSeenProducts) {
                browser.setText(browser.getText()
                    +"\nTHERE ARE OTHER "
                    +(productsList.size()-archiveIndex)
                    +" PRODUCTS !!!");
            }
            archiveIndex=lastArchiveIndex;
        } catch(CloneNotSupportedException ex) {}
        browser.setText(browser.getText()+"\n LAST VIEW UPDATE: ");
        // Time printing
        browser.setText(browser.getText()+df.format(date));
    }
    
    /**
     * Updates the information Panel
     */
    public void updateInfo() {
        // Number of products
        productsNumberLabel.setText(String.valueOf(products.lenght()));
        // Archive index
        indexValueTextField.setText("0");
        // Earnings & Spending
        earningsLabel.setText("");
        spendingLabel.setText("");
        int earningsLenght = String.valueOf(products.getEarnings()).length();
        int spendingLenght = String.valueOf(products.getSpending()).length();
        for(int i=0; i<earningsLenght; i++) {
            if((String.valueOf(products.getEarnings()).charAt(i) == '.')
                    && earningsLenght > i+3) {
                earningsLenght = i+3;
            }
            earningsLabel.setText(earningsLabel.getText()+String.valueOf(
                    String.valueOf(products.getEarnings()).charAt(i)
            ));
        }
        for(int i=0; i<spendingLenght; i++) {
            if((String.valueOf(products.getSpending()).charAt(i) == '.')
                    && spendingLenght > i+3) {
                spendingLenght = i+3;
            }
            spendingLabel.setText(spendingLabel.getText()+String.valueOf(
                    String.valueOf(products.getSpending()).charAt(i)
            ));
        }
        // Date label
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat df;
        df = new java.text.SimpleDateFormat("HH:mm:ss  dd-MM-yyyy");
        dateLabel.setText(df.format(date));
    }
    
    /**
     * Updates all saved data on files
     */
    public void updateFiles() {
        try {
            products.saveToBinaryFile("data.sma");
        } catch(java.io.IOException ex) {
        }
    }
    
    /**
     * Deletes all products
     */
    public void deleteAllProducts() {
        products = new Products();
    }
    
    /**
     * Sets a notify message
     * @param message The message
     */
    public void setNotify(String message) {
        notifyLabel.setText(message);
    }
    
    /*********** REPORT ACTIONS ***********/
    
    /**
     * Exports data to a Text file
     */
    public void exportToText() {
        try {
            products.exportToTextFile(filename);
            setNotify("Text file export done.");
        } catch (CloneNotSupportedException ex) {
            setNotify("Clone not supported exception.");
        } catch (java.io.IOException ex) {
            setNotify("Input-output exception.");
        } catch (ProductsException ex) {
            setNotify("Products exception.");
        }
    }
    
    /**
     * Exports data to a JSON file
     */
    public void exportToJson() {
        try {
            products.exportToJsonFile(filename);
            setNotify("JSON file export done.");
        } catch (CloneNotSupportedException ex) {
            setNotify("Clone not supported exception.");
        } catch (java.io.IOException ex) {
            setNotify("Input-output exception.");
        } catch (ProductsException ex) {
            setNotify("Products exception.");
        }
    }
    
    /**
     * Exports data to a HTML file
     */
    public void exportToHtml() {
        try {
            products.exportToHtmlFile(filename);
            setNotify("HTML file export done.");
        } catch (CloneNotSupportedException ex) {
            setNotify("Clone not supported exception.");
        } catch (java.io.IOException ex) {
            setNotify("Input-output exception.");
        } catch (ProductsException ex) {
            setNotify("Products exception.");
        }
    }
    
    /**
     * Imports data from a JSON file
     */
    public void importFromJson() {
        try {
            products.importFromJsonFile(filename);
            updateList();
            updateInfo();
            setNotify("JSON file import done.");
        } catch (CloneNotSupportedException ex) {
            setNotify("Clone not supported exception.");
        } catch (java.io.IOException ex) {
            setNotify("Input-output exception.");
            // Window notify
            Notify n = new Notify();
            n.setMessage("JSON file not found. "
                    + "Maybe you typed the wrong filename.");
            n.setLocation(400,200);
            n.setVisible(true);
        } catch (ProductsException ex) {
            setNotify("Product exception.");
        } catch (NullPointerException ex) {
            setNotify("Null pointer exception.");
        }
    }
    
    /**
     * Makes a backup from another JSON file
     */
    public void backupOfJson() {
        try {
            products.backupFromJsonFile(filename);
            setNotify("JSON file's backup done.");
        } catch (CloneNotSupportedException ex) {
            setNotify("Clone not supported exception.");
        } catch (java.io.IOException ex) {
            setNotify("Input-output exception.");
            // Window notify
            Notify n = new Notify();
            n.setMessage("JSON file not found. "
                    + "Maybe you typed the wrong filename.");
            n.setLocation(400,200);
            n.setVisible(true);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        centerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        browser = new javax.swing.JTextArea();
        leftPanel = new javax.swing.JPanel();
        filterByNameLabel = new javax.swing.JLabel();
        filterByNameTextField = new javax.swing.JTextField();
        filterByNameButton = new javax.swing.JButton();
        pasteFilterByNameButton = new javax.swing.JButton();
        searchByCodeLabel = new javax.swing.JLabel();
        searchByCodeTextField = new javax.swing.JTextField();
        searchByCodeButton = new javax.swing.JButton();
        pasteSearchByCodeButton = new javax.swing.JButton();
        filterByTypeLabel = new javax.swing.JLabel();
        filterByTypeTextField = new javax.swing.JTextField();
        filterByTypeButton = new javax.swing.JButton();
        pasteFilterByTypeButton = new javax.swing.JButton();
        filterByExpireDateLabel = new javax.swing.JLabel();
        filterByExpireDateTextField = new javax.swing.JTextField();
        filterByExpireDateButton = new javax.swing.JButton();
        pasteFilterByExpireDateButton = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        productsLabel = new javax.swing.JLabel();
        productsNumberLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        indexValueLabel = new javax.swing.JLabel();
        indexValueTextField = new javax.swing.JTextField();
        indexValueConfirmButton = new javax.swing.JButton();
        earningsDetailLabel = new javax.swing.JLabel();
        earningsLabel = new javax.swing.JLabel();
        spendingDetailLabel = new javax.swing.JLabel();
        spendingLabel = new javax.swing.JLabel();
        reportActionsButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        exportLabel = new javax.swing.JLabel();
        actionsOnProductsLabel = new javax.swing.JLabel();
        productsBrowserLabel = new javax.swing.JLabel();
        copySelectedTextButton = new javax.swing.JButton();
        seeAllProductsButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        seeSoldProducts = new javax.swing.JToggleButton();
        bottomBar = new javax.swing.JToolBar();
        technicalInfoLabel = new javax.swing.JLabel();
        notifyLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openSmaFileMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        restoreMenuItem = new javax.swing.JMenuItem();
        importMenu = new javax.swing.JMenu();
        jsonImportMenuItem = new javax.swing.JMenuItem();
        exportMenu = new javax.swing.JMenu();
        textExportMenuItem = new javax.swing.JMenuItem();
        jsonExportMenuItem = new javax.swing.JMenuItem();
        htmlExportMenuItem = new javax.swing.JMenuItem();
        backupMenu = new javax.swing.JMenu();
        jsonBackupMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        settingsMenuItem = new javax.swing.JMenuItem();
        deleteAllMenuItem = new javax.swing.JMenuItem();
        newWindowMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ShopManager");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        centerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Products browser"));

        browser.setEditable(false);
        browser.setColumns(20);
        browser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        browser.setRows(5);
        browser.setText(" TIPs\n\n 1. In order to see the updated products list, you have to click on \"Update prod. view\" on the bottom of the window, into the right\n     panel.\n\n 2. In this text area you can see a maximum of 1000 products (for speed reasons), and if you have more than 1000, you have to \"move\"\n     the index from \"0\" to another positive value, and then you have to click on \"OK\" and \"Update prod. view\" again. For example if you\n     have 3000 products, and you want to see the products from 2000 to 3000, you have to set the index value to 2000, click \"OK\" and\n     \"Update prod. view\".\n\n 3. When you add a product, its price increases the \"Spending\" value on the right, otherwise if you remove a product, its price\n     increases the \"Earnings\" value.\n\n 4. If you want to delete all data, there is an option on the \"Edit\" menu on the top of the window. After you deleted all data, you can\n     restore it yet, or you can save.\n\n 5. If you can't see your products, try to update the value of the index (0) on the panel on the right.\n\n 6. When you export the products list to a text/JSON file, it's saved into the folder from which you had run this program, and when you\n     import some products from a JSON file, they're automatically saved and on the next time you start this program you will see them.\n     Remember that when you import products from a JSON file, their ID will be different from the ID into the JSON file.\n\n 7. For speed reasons the export (to text file, JSON file, or HTML file) of the products is limited only for a maximum number of 4000\n     products.\n\n 8. You may open multiple windows of this manager window (from the \"Edit\" menu), but when you close one of them, you close the\n     whole program.\n\n\n\t\t\t\t\t\t© 2016 ShopManager by Hariton Andrei Marius.");
        browser.setBorder(null);
        browser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(browser);

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                .addContainerGap())
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));
        leftPanel.setMaximumSize(new java.awt.Dimension(162, 32767));
        leftPanel.setMinimumSize(new java.awt.Dimension(162, 100));

        filterByNameLabel.setBackground(new java.awt.Color(204, 204, 255));
        filterByNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filterByNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterByNameLabel.setText("Filter by name");

        filterByNameTextField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        filterByNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        filterByNameTextField.setMaximumSize(new java.awt.Dimension(130, 19));
        filterByNameTextField.setMinimumSize(new java.awt.Dimension(130, 19));
        filterByNameTextField.setPreferredSize(new java.awt.Dimension(130, 19));

        filterByNameButton.setBackground(new java.awt.Color(0, 102, 153));
        filterByNameButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filterByNameButton.setForeground(new java.awt.Color(255, 255, 255));
        filterByNameButton.setText("Filter");
        filterByNameButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        filterByNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByNameButtonActionPerformed(evt);
            }
        });

        pasteFilterByNameButton.setBackground(new java.awt.Color(0, 102, 153));
        pasteFilterByNameButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pasteFilterByNameButton.setForeground(new java.awt.Color(255, 255, 255));
        pasteFilterByNameButton.setText("Paste text");
        pasteFilterByNameButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pasteFilterByNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteFilterByNameButtonActionPerformed(evt);
            }
        });

        searchByCodeLabel.setBackground(new java.awt.Color(204, 204, 255));
        searchByCodeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchByCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchByCodeLabel.setText("Search by ID");

        searchByCodeTextField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        searchByCodeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchByCodeTextField.setMaximumSize(new java.awt.Dimension(130, 19));
        searchByCodeTextField.setMinimumSize(new java.awt.Dimension(130, 19));
        searchByCodeTextField.setPreferredSize(new java.awt.Dimension(130, 19));

        searchByCodeButton.setBackground(new java.awt.Color(0, 102, 153));
        searchByCodeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchByCodeButton.setForeground(new java.awt.Color(255, 255, 255));
        searchByCodeButton.setText("Search");
        searchByCodeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        searchByCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByCodeButtonActionPerformed(evt);
            }
        });

        pasteSearchByCodeButton.setBackground(new java.awt.Color(0, 102, 153));
        pasteSearchByCodeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pasteSearchByCodeButton.setForeground(new java.awt.Color(255, 255, 255));
        pasteSearchByCodeButton.setText("Paste text");
        pasteSearchByCodeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pasteSearchByCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteSearchByCodeButtonActionPerformed(evt);
            }
        });

        filterByTypeLabel.setBackground(new java.awt.Color(204, 204, 255));
        filterByTypeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filterByTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterByTypeLabel.setText("Filter by type");

        filterByTypeTextField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        filterByTypeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        filterByTypeTextField.setMaximumSize(new java.awt.Dimension(130, 19));
        filterByTypeTextField.setMinimumSize(new java.awt.Dimension(130, 19));
        filterByTypeTextField.setPreferredSize(new java.awt.Dimension(130, 19));

        filterByTypeButton.setBackground(new java.awt.Color(0, 102, 153));
        filterByTypeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filterByTypeButton.setForeground(new java.awt.Color(255, 255, 255));
        filterByTypeButton.setText("Filter");
        filterByTypeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        filterByTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByTypeButtonActionPerformed(evt);
            }
        });

        pasteFilterByTypeButton.setBackground(new java.awt.Color(0, 102, 153));
        pasteFilterByTypeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pasteFilterByTypeButton.setForeground(new java.awt.Color(255, 255, 255));
        pasteFilterByTypeButton.setText("Paste text");
        pasteFilterByTypeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pasteFilterByTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteFilterByTypeButtonActionPerformed(evt);
            }
        });

        filterByExpireDateLabel.setBackground(new java.awt.Color(204, 204, 255));
        filterByExpireDateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filterByExpireDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterByExpireDateLabel.setText("Filter by expire date");

        filterByExpireDateTextField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        filterByExpireDateTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        filterByExpireDateTextField.setMaximumSize(new java.awt.Dimension(130, 19));
        filterByExpireDateTextField.setMinimumSize(new java.awt.Dimension(130, 19));
        filterByExpireDateTextField.setPreferredSize(new java.awt.Dimension(130, 19));

        filterByExpireDateButton.setBackground(new java.awt.Color(0, 102, 153));
        filterByExpireDateButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filterByExpireDateButton.setForeground(new java.awt.Color(255, 255, 255));
        filterByExpireDateButton.setText("Filter");
        filterByExpireDateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        filterByExpireDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByExpireDateButtonActionPerformed(evt);
            }
        });

        pasteFilterByExpireDateButton.setBackground(new java.awt.Color(0, 102, 153));
        pasteFilterByExpireDateButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pasteFilterByExpireDateButton.setForeground(new java.awt.Color(255, 255, 255));
        pasteFilterByExpireDateButton.setText("Paste text");
        pasteFilterByExpireDateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pasteFilterByExpireDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteFilterByExpireDateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filterByNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pasteSearchByCodeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchByCodeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchByCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pasteFilterByExpireDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByExpireDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pasteFilterByTypeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByTypeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pasteFilterByNameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByNameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByExpireDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByTypeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterByExpireDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchByCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchByCodeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchByCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchByCodeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pasteSearchByCodeButton)
                .addGap(18, 18, 18)
                .addComponent(filterByNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterByNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterByNameButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pasteFilterByNameButton)
                .addGap(18, 18, 18)
                .addComponent(filterByTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterByTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterByTypeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pasteFilterByTypeButton)
                .addGap(18, 18, 18)
                .addComponent(filterByExpireDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterByExpireDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterByExpireDateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pasteFilterByExpireDateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations"));
        rightPanel.setMaximumSize(new java.awt.Dimension(194, 32767));
        rightPanel.setMinimumSize(new java.awt.Dimension(194, 100));

        productsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productsLabel.setText("Products:");

        productsNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productsNumberLabel.setText("0");

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText("--:--:--  --/--/----");

        indexValueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        indexValueLabel.setText("Index:");

        indexValueTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        indexValueTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        indexValueTextField.setText("0");

        indexValueConfirmButton.setBackground(new java.awt.Color(0, 102, 153));
        indexValueConfirmButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        indexValueConfirmButton.setForeground(new java.awt.Color(255, 255, 255));
        indexValueConfirmButton.setText("OK");
        indexValueConfirmButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        indexValueConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexValueConfirmButtonActionPerformed(evt);
            }
        });

        earningsDetailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        earningsDetailLabel.setText("Earnings:");

        earningsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        earningsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        earningsLabel.setText("0.0");

        spendingDetailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        spendingDetailLabel.setText("Spending:");

        spendingLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        spendingLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        spendingLabel.setText("0.0");

        reportActionsButton.setBackground(new java.awt.Color(0, 102, 153));
        reportActionsButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reportActionsButton.setForeground(new java.awt.Color(255, 255, 255));
        reportActionsButton.setText("Report actions");
        reportActionsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        reportActionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportActionsButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(0, 102, 153));
        addButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add products");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setBackground(new java.awt.Color(0, 102, 153));
        removeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        removeButton.setForeground(new java.awt.Color(255, 255, 255));
        removeButton.setText("Remove a product");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(0, 102, 153));
        editButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Edit a product");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        exportLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exportLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exportLabel.setText("To text, HTML, JSON file");

        actionsOnProductsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        actionsOnProductsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actionsOnProductsLabel.setText("Actions on products");

        productsBrowserLabel.setBackground(new java.awt.Color(204, 204, 255));
        productsBrowserLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        productsBrowserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsBrowserLabel.setText("Products browser");

        copySelectedTextButton.setBackground(new java.awt.Color(0, 102, 153));
        copySelectedTextButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        copySelectedTextButton.setForeground(new java.awt.Color(255, 255, 255));
        copySelectedTextButton.setText("Copy selected text");
        copySelectedTextButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        copySelectedTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copySelectedTextButtonActionPerformed(evt);
            }
        });

        seeAllProductsButton.setBackground(new java.awt.Color(0, 102, 153));
        seeAllProductsButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        seeAllProductsButton.setForeground(new java.awt.Color(255, 255, 255));
        seeAllProductsButton.setText("Update prod. view");
        seeAllProductsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        seeAllProductsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeAllProductsButtonActionPerformed(evt);
            }
        });

        sellButton.setBackground(new java.awt.Color(0, 102, 153));
        sellButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sellButton.setForeground(new java.awt.Color(255, 255, 255));
        sellButton.setText("Sell a product");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        seeSoldProducts.setBackground(new java.awt.Color(0, 102, 153));
        seeSoldProducts.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        seeSoldProducts.setForeground(new java.awt.Color(255, 255, 255));
        seeSoldProducts.setText("See sold p. ON/OFF");

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(indexValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(indexValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(indexValueConfirmButton, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(earningsDetailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spendingDetailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spendingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(earningsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productsNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actionsOnProductsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportActionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sellButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productsBrowserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(copySelectedTextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seeAllProductsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seeSoldProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indexValueLabel)
                    .addComponent(indexValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indexValueConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productsLabel)
                    .addComponent(productsNumberLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(earningsDetailLabel)
                    .addComponent(earningsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spendingDetailLabel)
                    .addComponent(spendingLabel))
                .addGap(18, 18, 18)
                .addComponent(actionsOnProductsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addGap(18, 18, 18)
                .addComponent(exportLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportActionsButton)
                .addGap(18, 18, 18)
                .addComponent(productsBrowserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copySelectedTextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeAllProductsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeSoldProducts)
                .addGap(18, 18, 18)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bottomBar.setBackground(new java.awt.Color(255, 255, 255));
        bottomBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bottomBar.setFloatable(false);
        bottomBar.setRollover(true);

        technicalInfoLabel.setText("-");
        technicalInfoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bottomBar.add(technicalInfoLabel);

        notifyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        notifyLabel.setText("-");
        notifyLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        notifyLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bottomBar.add(notifyLabel);

        menuBar.setBackground(new java.awt.Color(255, 255, 255));
        menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fileMenu.setText("File");
        fileMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        openSmaFileMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        openSmaFileMenuItem.setText("Open SMA file");
        openSmaFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSmaFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openSmaFileMenuItem);

        saveMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        saveMenuItem.setText("Save");
        saveMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        restoreMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        restoreMenuItem.setText("Restore");
        restoreMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        restoreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(restoreMenuItem);

        importMenu.setBackground(new java.awt.Color(255, 255, 255));
        importMenu.setText("Import from");
        importMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jsonImportMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        jsonImportMenuItem.setText("JSON file");
        jsonImportMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jsonImportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsonImportMenuItemActionPerformed(evt);
            }
        });
        importMenu.add(jsonImportMenuItem);

        fileMenu.add(importMenu);

        exportMenu.setBackground(new java.awt.Color(255, 255, 255));
        exportMenu.setText("Export as");
        exportMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        textExportMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        textExportMenuItem.setText("Text file");
        textExportMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textExportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textExportMenuItemActionPerformed(evt);
            }
        });
        exportMenu.add(textExportMenuItem);

        jsonExportMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        jsonExportMenuItem.setText("JSON file");
        jsonExportMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jsonExportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsonExportMenuItemActionPerformed(evt);
            }
        });
        exportMenu.add(jsonExportMenuItem);

        htmlExportMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        htmlExportMenuItem.setText("HTML file");
        htmlExportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                htmlExportMenuItemActionPerformed(evt);
            }
        });
        exportMenu.add(htmlExportMenuItem);

        fileMenu.add(exportMenu);

        backupMenu.setBackground(new java.awt.Color(255, 255, 255));
        backupMenu.setText("Backup of");
        backupMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jsonBackupMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        jsonBackupMenuItem.setText("JSON file");
        jsonBackupMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jsonBackupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsonBackupMenuItemActionPerformed(evt);
            }
        });
        backupMenu.add(jsonBackupMenuItem);

        fileMenu.add(backupMenu);

        menuBar.add(fileMenu);

        editMenu.setBackground(new java.awt.Color(255, 255, 255));
        editMenu.setText("Edit");
        editMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        settingsMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        settingsMenuItem.setText("Settings");
        settingsMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(settingsMenuItem);

        deleteAllMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        deleteAllMenuItem.setText("Delete all");
        deleteAllMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deleteAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(deleteAllMenuItem);

        newWindowMenuItem.setText("New window");
        newWindowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newWindowMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(newWindowMenuItem);

        menuBar.add(editMenu);

        aboutMenu.setText("About");
        aboutMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        aboutMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        aboutMenuItem.setText("About");
        aboutMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(aboutMenuItem);

        helpMenuItem.setBackground(new java.awt.Color(255, 255, 255));
        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(helpMenuItem);

        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(bottomBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void settingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsMenuItemActionPerformed
        Settings settings = new Settings();
        settings.setProducts(products);
        settings.setManager(this);
        settings.setLocation(400, 200);
        settings.setVisible(true);
    }//GEN-LAST:event_settingsMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        About about = new About();
        about.setProducts(products);
        about.setManager(this);
        about.setLocation(400, 200);
        about.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void filterByNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterByNameButtonActionPerformed
        if(!filterByNameTextField.getText().isEmpty()) {
            browser.setText(" FOUNDED PRODUCTS WITH NAME \""+
                    filterByNameTextField.getText()+"\":\n\n");
            java.util.ArrayList<Product> searchedProducts;
            try {
                int lastArchiveIndex=archiveIndex;
                searchedProducts = products.filterByName(
                        filterByNameTextField.getText()
                );
                while(archiveIndex<searchedProducts.size()
                        && archiveIndex<maxSeenProducts+1)
                {
                    browser.setText(browser.getText()
                        +(Product)searchedProducts.get(archiveIndex));
                    archiveIndex++;
                }
                if(archiveIndex>maxSeenProducts) {
                    browser.setText(browser.getText()
                        +"\n THERE ARE OTHER "
                        +(searchedProducts.size()-archiveIndex)
                        +" PRODUCTS !!!");
                }
                archiveIndex=lastArchiveIndex;
                setNotify("Search done.");
            } catch(CloneNotSupportedException ex) {
                setNotify("Clone not supported exception.");
            }
        }
    }//GEN-LAST:event_filterByNameButtonActionPerformed

    private void seeAllProductsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeAllProductsButtonActionPerformed
        if(seeSoldProducts.isSelected()) {
            updateNotSoldList();
        } else {
            updateList();
        }
    }//GEN-LAST:event_seeAllProductsButtonActionPerformed

    private void copySelectedTextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copySelectedTextButtonActionPerformed
        browser.copy();
        setNotify("Text copied.");
    }//GEN-LAST:event_copySelectedTextButtonActionPerformed

    private void pasteFilterByNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteFilterByNameButtonActionPerformed
        filterByNameTextField.setText("");
        filterByNameTextField.paste();
        setNotify("Text pasted.");
    }//GEN-LAST:event_pasteFilterByNameButtonActionPerformed

    private void searchByCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByCodeButtonActionPerformed
        if(!searchByCodeTextField.getText().isEmpty()) {
            browser.setText(" FOUNDED PRODUCT BY CODE:\n\n");
            Product searchedProduct;
            try {
                searchedProduct = products.searchProduct(
                        searchByCodeTextField.getText()
                );
                browser.setText(browser.getText()
                            +searchedProduct.toStringWithDescription());
                setNotify("Search done.");
            } catch(ProductsException | CloneNotSupportedException ex) {
                setNotify("Product/Clone-not-supported exception.");
            }
        }
    }//GEN-LAST:event_searchByCodeButtonActionPerformed

    private void pasteSearchByCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteSearchByCodeButtonActionPerformed
        searchByCodeTextField.setText("");
        searchByCodeTextField.paste();
        setNotify("Text pasted.");
    }//GEN-LAST:event_pasteSearchByCodeButtonActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        updateFiles();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void restoreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreMenuItemActionPerformed
        try {
            products.restoreFromBinaryFile("data.sma");
            updateList();
            updateInfo();
            setNotify("All data has been restored.");
        } catch(java.io.IOException | FileException ex) {
            setNotify("Input-output/file exception.");
        }
    }//GEN-LAST:event_restoreMenuItemActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        archiveIndex=0;
        maxSeenProducts=archiveIndex+1000;
        technicalInfoLabel.setText(threadName);
        filename="report";
        try {
            products.restoreFromBinaryFile("data.sma");
            updateInfo();
            setNotify("Ready.");
        } catch(java.io.IOException | FileException ex) {
            setNotify("Input-output/file exception.");
        }
    }//GEN-LAST:event_formWindowOpened

    private void deleteAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllMenuItemActionPerformed
        deleteAllProducts();
        updateList();
        updateInfo();
        cleanControls();
    }//GEN-LAST:event_deleteAllMenuItemActionPerformed

    private void filterByTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterByTypeButtonActionPerformed
        if(!filterByTypeTextField.getText().isEmpty()) {
            browser.setText(" FOUNDED PRODUCTS WITH TYPE \""+
                    filterByTypeTextField.getText()+"\":\n\n");
            java.util.ArrayList<Product> searchedProducts;
            try {
                int lastArchiveIndex=archiveIndex;
                searchedProducts = products.filterByType(
                        filterByTypeTextField.getText()
                );
                while(archiveIndex<searchedProducts.size()
                        && archiveIndex<maxSeenProducts+1)
                {
                    browser.setText(browser.getText()
                        +(Product)searchedProducts.get(archiveIndex));
                    archiveIndex++;
                }
                if(archiveIndex>maxSeenProducts) {
                    browser.setText(browser.getText()
                        +"\n THERE ARE OTHER "
                        +(searchedProducts.size()-archiveIndex)
                        +" PRODUCTS !!!");
                }
                archiveIndex=lastArchiveIndex;
                setNotify("Search done.");
            } catch(CloneNotSupportedException ex) {
                setNotify("Clone not supported exception.");
            }
        }
    }//GEN-LAST:event_filterByTypeButtonActionPerformed

    private void pasteFilterByTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteFilterByTypeButtonActionPerformed
        filterByTypeTextField.setText("");
        filterByTypeTextField.paste();
        setNotify("Text pasted.");
    }//GEN-LAST:event_pasteFilterByTypeButtonActionPerformed

    private void filterByExpireDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterByExpireDateButtonActionPerformed
        if(!filterByExpireDateTextField.getText().isEmpty()) {
            browser.setText(" FOUNDED PRODUCTS WITH EXPIRE DATE \""+
                    filterByExpireDateTextField.getText()+"\":\n\n");
            java.util.ArrayList<Product> searchedProducts;
            try {
                int lastArchiveIndex=archiveIndex;
                searchedProducts = products.filterByExpireDate(
                        filterByExpireDateTextField.getText()
                );
                while(archiveIndex<searchedProducts.size()
                        && archiveIndex<maxSeenProducts+1)
                {
                    browser.setText(browser.getText()
                        +(Product)searchedProducts.get(archiveIndex));
                    archiveIndex++;
                }
                if(archiveIndex>maxSeenProducts) {
                    browser.setText(browser.getText()
                        +"\n THERE ARE OTHER "
                        +(searchedProducts.size()-archiveIndex)
                        +" PRODUCTS !!!");
                }
                archiveIndex=lastArchiveIndex;
                setNotify("Search done.");
            } catch(CloneNotSupportedException ex) {
                setNotify("Clone not supported exception.");
            }
        }
    }//GEN-LAST:event_filterByExpireDateButtonActionPerformed

    private void pasteFilterByExpireDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteFilterByExpireDateButtonActionPerformed
        filterByExpireDateTextField.setText("");
        filterByExpireDateTextField.paste();
        setNotify("Text pasted.");
    }//GEN-LAST:event_pasteFilterByExpireDateButtonActionPerformed

    private void indexValueConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexValueConfirmButtonActionPerformed
        archiveIndex = Integer.parseInt(indexValueTextField.getText());
        maxSeenProducts=archiveIndex+1000;
        setNotify("Index value updated!");
    }//GEN-LAST:event_indexValueConfirmButtonActionPerformed

    private void jsonExportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsonExportMenuItemActionPerformed
        Report r = new Report();
        r.setLocation(400,200);
        r.setManager(this);
        r.setAction("ExportJSON");
        r.setVisible(true);
    }//GEN-LAST:event_jsonExportMenuItemActionPerformed

    private void textExportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textExportMenuItemActionPerformed
        Report r = new Report();
        r.setLocation(400,200);
        r.setManager(this);
        r.setAction("ExportTXT");
        r.setVisible(true);
    }//GEN-LAST:event_textExportMenuItemActionPerformed

    private void jsonImportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsonImportMenuItemActionPerformed
        Report r = new Report();
        r.setLocation(400,200);
        r.setManager(this);
        r.setAction("ImportJSON");
        r.setVisible(true);
    }//GEN-LAST:event_jsonImportMenuItemActionPerformed

    private void jsonBackupMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsonBackupMenuItemActionPerformed
        Report r = new Report();
        r.setLocation(400,200);
        r.setManager(this);
        r.setAction("BackupJSON");
        r.setVisible(true);
    }//GEN-LAST:event_jsonBackupMenuItemActionPerformed

    private void htmlExportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_htmlExportMenuItemActionPerformed
        Report r = new Report();
        r.setLocation(400,200);
        r.setManager(this);
        r.setAction("ExportHTML");
        r.setVisible(true);
    }//GEN-LAST:event_htmlExportMenuItemActionPerformed

    private void newWindowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newWindowMenuItemActionPerformed
        Thread t = new Thread(new Main());
        t.start();
    }//GEN-LAST:event_newWindowMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        browser.setText(
" TIPs\n" +
"\n" +
" 1. In order to see the updated products list, you have to click on \"Update prod. view\" on the bottom of the window, into the right\n" +
"     panel.\n" +
"\n" +
" 2. In this text area you can see a maximum of 1000 products (for speed reasons), and if you have more than 1000, you have to \"move\"\n" +
"     the index from \"0\" to another positive value, and then you have to click on \"OK\" and \"Update prod. view\" again. For example if you\n" +
"     have 3000 products, and you want to see the products from 2000 to 3000, you have to set the index value to 2000, click \"OK\" and\n" +
"     \"Update prod. view\".\n" +
"\n" +
" 3. When you add a product, its price increases the \"Spending\" value on the right, otherwise if you remove a product, its price\n" +
"     increases the \"Earnings\" value.\n" +
"\n" +
" 4. If you want to delete all data, there is an option on the \"Edit\" menu on the top of the window. After you deleted all data, you can\n" +
"     restore it yet, or you can save.\n" +
"\n" +
" 5. If you can't see your products, try to update the value of the index (0) on the panel on the right.\n" +
"\n" +
" 6. When you export the products list to a text/JSON file, it's saved into the folder from which you had run this program, and when you\n" +
"     import some products from a JSON file, they're automatically saved and on the next time you start this program you will see them.\n" +
"     Remember that when you import products from a JSON file, their ID will be different from the ID into the JSON file.\n" +
"\n" +
" 7. For speed reasons the export (to text file, JSON file, or HTML file) of the products is limited only for a maximum number of 4000\n" +
"     products.\n" +
"\n" +
" 8. You may open multiple windows of this manager window (from the \"Edit\" menu), but when you close one of them, you close the\n" +
"     whole program.\n" +
"\n" +
"\n" +
"						© 2016 ShopManager by Hariton Andrei Marius."
);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void reportActionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportActionsButtonActionPerformed
        Report r = new Report();
        r.setLocation(400,200);
        r.setManager(this);
        r.setAction("DefaultHTML");
        r.setVisible(true);
    }//GEN-LAST:event_reportActionsButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        Add add = new Add();
        add.setProducts(products);
        add.setManager(this);
        add.setLocation(400, 200);
        add.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        RemoveByCode remove = new RemoveByCode();
        remove.setProducts(products);
        remove.setManager(this);
        remove.setLocation(400, 200);
        remove.setVisible(true);
    }//GEN-LAST:event_removeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        Edit edit = new Edit();
        edit.setProducts(products);
        edit.setManager(this);
        edit.setLocation(400, 200);
        edit.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        SellByCode sell = new SellByCode();
        sell.setProducts(products);
        sell.setManager(this);
        sell.setLocation(400, 200);
        sell.setVisible(true);
    }//GEN-LAST:event_sellButtonActionPerformed

    private void openSmaFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSmaFileMenuItemActionPerformed
        ArchiveBrowser ab = new ArchiveBrowser();
        ab.setLocation(50, 50);
        ab.setVisible(true);
    }//GEN-LAST:event_openSmaFileMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Manager().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel actionsOnProductsLabel;
    private javax.swing.JButton addButton;
    private javax.swing.JMenu backupMenu;
    private javax.swing.JToolBar bottomBar;
    private javax.swing.JTextArea browser;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton copySelectedTextButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JMenuItem deleteAllMenuItem;
    private javax.swing.JLabel earningsDetailLabel;
    private javax.swing.JLabel earningsLabel;
    private javax.swing.JButton editButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JLabel exportLabel;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton filterByExpireDateButton;
    private javax.swing.JLabel filterByExpireDateLabel;
    private javax.swing.JTextField filterByExpireDateTextField;
    private javax.swing.JButton filterByNameButton;
    private javax.swing.JLabel filterByNameLabel;
    private javax.swing.JTextField filterByNameTextField;
    private javax.swing.JButton filterByTypeButton;
    private javax.swing.JLabel filterByTypeLabel;
    private javax.swing.JTextField filterByTypeTextField;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem htmlExportMenuItem;
    private javax.swing.JMenu importMenu;
    private javax.swing.JButton indexValueConfirmButton;
    private javax.swing.JLabel indexValueLabel;
    private javax.swing.JTextField indexValueTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jsonBackupMenuItem;
    private javax.swing.JMenuItem jsonExportMenuItem;
    private javax.swing.JMenuItem jsonImportMenuItem;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newWindowMenuItem;
    private javax.swing.JLabel notifyLabel;
    private javax.swing.JMenuItem openSmaFileMenuItem;
    private javax.swing.JButton pasteFilterByExpireDateButton;
    private javax.swing.JButton pasteFilterByNameButton;
    private javax.swing.JButton pasteFilterByTypeButton;
    private javax.swing.JButton pasteSearchByCodeButton;
    private javax.swing.JLabel productsBrowserLabel;
    private javax.swing.JLabel productsLabel;
    private javax.swing.JLabel productsNumberLabel;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton reportActionsButton;
    private javax.swing.JMenuItem restoreMenuItem;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton searchByCodeButton;
    private javax.swing.JLabel searchByCodeLabel;
    private javax.swing.JTextField searchByCodeTextField;
    private javax.swing.JButton seeAllProductsButton;
    private javax.swing.JToggleButton seeSoldProducts;
    private javax.swing.JButton sellButton;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JLabel spendingDetailLabel;
    private javax.swing.JLabel spendingLabel;
    private javax.swing.JLabel technicalInfoLabel;
    private javax.swing.JMenuItem textExportMenuItem;
    // End of variables declaration//GEN-END:variables
}
