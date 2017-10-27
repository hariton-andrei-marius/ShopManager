/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import exceptions.FileException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Product;
import objects.Products;

/**
 *
 * @author Hariton AndreiMarius
 */
public class ArchiveBrowser extends javax.swing.JFrame {
    // TreeMap of Products
    private Products products;
    // Visualization of products limit
    private int maxSeenProducts;
    // Archive index
    private int archiveIndex;
    
    /**
     * Creates new form ArchiveBrowser
     */
    public ArchiveBrowser() {
        initComponents();
    }

    /**
     * Updates the list's TextArea
     */
    public void updateBrowser() {
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        filenameLabel = new javax.swing.JLabel();
        filenameField = new javax.swing.JTextField();
        openButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        browser = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Archive browser");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        filenameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filenameLabel.setText("Filename:");

        filenameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filenameField.setText("data.sma");

        openButton.setBackground(new java.awt.Color(0, 102, 153));
        openButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        openButton.setForeground(new java.awt.Color(255, 255, 255));
        openButton.setText("Open");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        previousButton.setBackground(new java.awt.Color(0, 102, 153));
        previousButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        previousButton.setForeground(new java.awt.Color(255, 255, 255));
        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setBackground(new java.awt.Color(0, 102, 153));
        nextButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nextButton.setForeground(new java.awt.Color(255, 255, 255));
        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        browser.setEditable(false);
        browser.setColumns(20);
        browser.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        browser.setRows(5);
        browser.setText("\n\n\n\n\n\n\n\n\n\n\t\t\tHelp\n\t\t\t__________\n\n\t\t\t1) Write the name of the SMA (Shop Manager Archive) file,\n\t\t\t     located into the same folder of this app.\n\t\t\t2) Click \"Open\".\n\t\t\t3) Use \"Previous\" and \"Next\" to move into the SM file.");
        browser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(browser);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(filenameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(filenameField, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(openButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 423, Short.MAX_VALUE)
                        .addComponent(previousButton)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton)))
                .addContainerGap())
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filenameLabel)
                    .addComponent(filenameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openButton)
                    .addComponent(previousButton)
                    .addComponent(nextButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        try {
            products.restoreFromBinaryFile(filenameField.getText());
            updateBrowser();
        } catch (IOException | FileException ex) {
            Notify n = new Notify();
            n.setMessage("Not found: Maybe you typed the wrong filename.");
            n.setLocation(400,200);
            n.setVisible(true);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        products = new Products();
        archiveIndex=0;
        maxSeenProducts=archiveIndex+1000;
    }//GEN-LAST:event_formWindowOpened

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        if(archiveIndex!=0) {
            archiveIndex-=1000;
            maxSeenProducts=archiveIndex+1000;
            updateBrowser();
        } else {
            Notify n = new Notify();
            n.setMessage("Not required, maybe try the \"Next\" button.");
            n.setLocation(400,200);
            n.setVisible(true);
        }
    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        if(products.lenght()>archiveIndex) {
            archiveIndex+=1000;
            maxSeenProducts=archiveIndex+1000;
            updateBrowser();
        } else {
            Notify n = new Notify();
            n.setMessage("Not required, you have a few products.");
            n.setLocation(400,200);
            n.setVisible(true);
        }
    }//GEN-LAST:event_nextButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArchiveBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArchiveBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArchiveBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArchiveBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArchiveBrowser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea browser;
    private javax.swing.JPanel container;
    private javax.swing.JTextField filenameField;
    private javax.swing.JLabel filenameLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton previousButton;
    // End of variables declaration//GEN-END:variables
}
