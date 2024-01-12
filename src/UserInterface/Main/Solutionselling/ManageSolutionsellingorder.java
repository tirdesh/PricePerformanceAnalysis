/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.Main.Solutionselling;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.MarketModel.SolutionOfferCatalog;
import TheBusiness.MarketModel.SolutionOfferSummary;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.SolutionOrders.SolutionOrderItem;
import TheBusiness.SolutionOrders.SolutionOrderSummary;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abhisheksagar
 */
public class ManageSolutionsellingorder extends javax.swing.JPanel {

    /**
     * Creates new form ManageSolutionsellingorder
     */
    public ManageSolutionsellingorder() {
        initComponents();
    }
    JPanel CardSequencePanel;
    Business business;
//    Supplier selectedsupplier;
    MarketChannelAssignment selectedmarket;
//    Product selectedproduct;
    SolutionOffer selectedoffer;
    SolutionOrder currentOrder;
    //private HashMap<String, SolutionOffer> solutionOfferMap = new HashMap<>();

    
    public ManageSolutionsellingorder(Business bz, JPanel jp) {

        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        // salesPersonTextField.setText(salesperson.getPerson().toString());
        // customerTextField.setText(customer.getCustomerId());
        MasterSolutionOrderList mol = business.getMasterSolutionOrderList();
//        currentOrder =  mol.newSolutionOrder(mca); //no order was made yet
        initializeTable();

    }

    private void initializeTable() {

//clear supplier table
        cleanUpCombobox();
        cleanUpTable();
        cleanUpsolutionofferTable();

//load suppliers to the combobox
        ArrayList<MarketChannelAssignment> marketlist = business.getMarketChannelComboCatalog().getMcalist();

        if (marketlist.isEmpty()) {
            return;
        }

        for (MarketChannelAssignment m : marketlist) {
            
            MarketComboBox.addItem(m.toString());
            MarketComboBox.setSelectedIndex(0);
            String marketname = (String) MarketComboBox.getSelectedItem();
            Market marketString=business.getMarketCatalog().findMarket(marketname.split("_")[0]);
            Channel channelString=business.getChannelCatalog().findChannel(marketname.split("_")[1]);
            
            selectedmarket = business.getMarketChannelComboCatalog().finMarketChannelCombo(marketString,channelString);
            currentOrder= business.getMasterSolutionOrderList().newSolutionOrder(selectedmarket);

            ArrayList<SolutionOffer> pc = selectedmarket.getSolutionofferlist();
            for (SolutionOffer pt : pc) {

                Object[] row = new Object[5];
                row[0] = pt;
                row[1] = pt.getFloorPrice();
                row[2] = pt.getCeilingPrice();
                row[3] = pt.getTargetPrice();
                row[4]= pt.getSolutionPrice();

                ((DefaultTableModel) ProductCatalogTable.getModel()).addRow(row);
            }

        }
    }

    public void cleanUpCombobox() {
        //Clean the combobox for supplier choices

        MarketComboBox.removeAllItems();

    }

    public void cleanUpTable() {

        //Clean the product catalog table
        int rc = ProductCatalogTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) ProductCatalogTable.getModel()).removeRow(i);
        }
    }
    public void cleanUpsolutionofferTable() {

        //Clean the product catalog table
        int rc = SubproductsCatalogTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) SubproductsCatalogTable.getModel()).removeRow(i);
        }
    }
    public void cleanUpItemsTable() {

        //Clean the product catalog table
        int rc = OrderItemsTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) OrderItemsTable.getModel()).removeRow(i);
        }
    }

    public void refreshSupplierProductCatalogTable() {

//clear supplier table
        int rc = ProductCatalogTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) ProductCatalogTable.getModel()).removeRow(i);
        }

        String marketname = (String) MarketComboBox.getSelectedItem();
            Market marketString=business.getMarketCatalog().findMarket(marketname.split("_")[0]);
            Channel channelString=business.getChannelCatalog().findChannel(marketname.split("_")[1]);

            selectedmarket = business.getMarketChannelComboCatalog().finMarketChannelCombo(marketString,channelString);
            
//        selectedmarket = business.getMarketCatalog().findMarket(suppliername[0]);
        if (selectedmarket == null) {
            return;
        }
        ArrayList<SolutionOffer> pc = selectedmarket.getSolutionofferlist();
        for (SolutionOffer pt : pc) {

            Object[] row = new Object[5];
            row[0] = pt;
            // row[0] = "solutionoffer" + (l + 1);  // Setting the name
            row[1] = pt.getFloorPrice();
            row[2] = pt.getCeilingPrice();
            row[3] = pt.getTargetPrice();
            row[4] = pt.getSolutionPrice();
            // solutionOfferMap.put("solutionoffer" + (l + 1), pt);

            ((DefaultTableModel) ProductCatalogTable.getModel()).addRow(row);
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

        Back = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductCatalogTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        OrderItemsTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        MarketComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        customerTextField = new javax.swing.JTextField();
        QuantityTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        productFrequencyBelowTargetTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        productFrequencyAboveTargetTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        productPricePerformanceTextField = new javax.swing.JTextField();
        productRevenueTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        SubproductsCatalogTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        salesPersonTextField1 = new javax.swing.JTextField();
        PriceTextField = new javax.swing.JTextField();
        TotalTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back.setText("X Cancel");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 760, 90, -1));

        Next.setText("Submit");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        add(Next, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 720, 80, -1));

        jLabel1.setText("Market Channel Assignment");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 180, -1));

        ProductCatalogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Floor", "Ceiling", "Target", "Selling/Solution"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProductCatalogTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProductCatalogTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProductCatalogTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(ProductCatalogTable);
        if (ProductCatalogTable.getColumnModel().getColumnCount() > 0) {
            ProductCatalogTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 600, 110));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Prepare Solution Order");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 550, -1));

        jLabel8.setText("Solution Offer");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, 20));

        OrderItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Actual price", "Quanity", "Item total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OrderItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrderItemsTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrderItemsTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(OrderItemsTable);

        jScrollPane3.setViewportView(jScrollPane4);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 600, 100));

        jLabel9.setText("Order Items");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, -1, 20));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1AddProductItemActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 90, 30));

        MarketComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MarketComboBoxMousePressed(evt);
            }
        });
        MarketComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarketComboBoxActionPerformed(evt);
            }
        });
        add(MarketComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 180, -1));

        jLabel10.setText("Price:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 60, -1));
        add(customerTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 160, -1));
        add(QuantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, 160, -1));

        jLabel11.setText("Sales person");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 150, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Business-wide Product Intelligence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Frequency Below Target");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 150, -1));
        jPanel1.add(productFrequencyBelowTargetTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 150, -1));

        jLabel4.setText("Frequency Above Target");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 150, -1));
        jPanel1.add(productFrequencyAboveTargetTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, -1));

        jLabel7.setText("Marign around target");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 150, -1));

        productPricePerformanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPricePerformanceTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(productPricePerformanceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 150, -1));

        productRevenueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productRevenueTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(productRevenueTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, -1));

        jLabel5.setText("Sales Revenues");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, -1));

        jLabel3.setText("Product name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, -1));

        productNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(productNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 150, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 600, 170));

        SubproductsCatalogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Floor", "Ceiling", "Target"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SubproductsCatalogTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SubproductsCatalogTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SubproductsCatalogTableMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(SubproductsCatalogTable);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 596, 114));

        jLabel12.setText("Solution offer product Items");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, 20));

        jLabel13.setText("Customer");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 150, -1));

        jLabel14.setText("Quantity:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 60, -1));
        add(salesPersonTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 160, -1));
        add(PriceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 160, -1));

        TotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalTextFieldActionPerformed(evt);
            }
        });
        add(TotalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 160, -1));

        jLabel15.setText("Total:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 590, 60, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
          currentOrder.cancelSolutionOrder();
                 CardSequencePanel.removeAll();

        ManageSolutionsellingorder ms = new ManageSolutionsellingorder(business, CardSequencePanel);

        CardSequencePanel.add("ManageSolutionsellingorder", ms);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_BackActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
//        currentOrder.Submit();
//        CardSequencePanel.remove(this);
//        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    JOptionPane.showMessageDialog(this, "Cart Submitted successfully");
    BackActionPerformed(evt);

    }//GEN-LAST:event_NextActionPerformed

    private void ProductCatalogTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductCatalogTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductCatalogTableMouseEntered

    private void ProductCatalogTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductCatalogTableMousePressed
        // TODO add your handling code here:
        cleanUpsolutionofferTable();
        int solutionoffertablesize = ProductCatalogTable.getRowCount();
        int selectedrow = ProductCatalogTable.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > solutionoffertablesize - 1) {
            return;
        }
        // String selectedOfferName = (String) ProductCatalogTable.getValueAt(selectedrow, 0);
        // SolutionOffer selectedOffer = solutionOfferMap.get(selectedOfferName);
        SolutionOffer selectedoffer = (SolutionOffer) ProductCatalogTable.getValueAt(selectedrow, 0);
                
        PriceTextField.setText(String.valueOf(selectedoffer.getSolutionPrice()));
        QuantityTextField.setText("1");
        if (selectedoffer == null) {
            return;
        }
        for (Product pt : selectedoffer.getProducts()) {

                Object[] row = new Object[5];
                row[0] = pt;
                row[1] = pt.getFloorPrice();
                row[2] = pt.getCeilingPrice();
                row[3] = pt.getTargetPrice();
//                row[4]= pt.getSolutionPrice();

                ((DefaultTableModel) SubproductsCatalogTable.getModel()).addRow(row);
            }
        SolutionOfferSummary solutionOfferSummary = new SolutionOfferSummary(selectedoffer);

        // Assuming these are your text fields for displaying the summary
        productNameTextField.setText(selectedoffer.toString());
        String revenues = String.valueOf(solutionOfferSummary.getSalesRevenues());
        productRevenueTextField.setText(revenues);
        productFrequencyAboveTargetTextField.setText(String.valueOf(solutionOfferSummary.getNumberAboveTarget()));
        productFrequencyBelowTargetTextField.setText(String.valueOf(solutionOfferSummary.getNumberBelowTarget()));
        productPricePerformanceTextField.setText(String.valueOf(solutionOfferSummary.getSolutionOfferPricePerformance()));
    }//GEN-LAST:event_ProductCatalogTableMousePressed

    private void OrderItemsTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderItemsTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderItemsTableMouseEntered

    private void OrderItemsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderItemsTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderItemsTableMousePressed

    private void jButton1AddProductItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1AddProductItemActionPerformed
        // TODO add your handling code here:

        int suppliertablesize = ProductCatalogTable.getRowCount();
        int selectedrow = ProductCatalogTable.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > suppliertablesize - 1) {
            return;
        }
        // String selectedOfferName = (String) ProductCatalogTable.getValueAt(selectedrow, 0);
        // SolutionOffer selectedOffer = solutionOfferMap.get(selectedOfferName);
        SolutionOffer selectedoffer = (SolutionOffer) ProductCatalogTable.getValueAt(selectedrow, 0);
        if (selectedoffer == null) return;

        
        String paidPrice = PriceTextField.getText();
        String quantity = QuantityTextField.getText();
        if(paidPrice==null || quantity==null){
            JOptionPane.showMessageDialog(this, "Modify paid price or quantity");
        }
        SolutionOrderItem item = currentOrder.newSolutionOrderItem(selectedoffer, Integer.parseInt(paidPrice), Integer.parseInt(quantity));
        Object[] row = new Object[5];

        row[0] = String.valueOf(selectedoffer);
        row[1] = String.valueOf(item.getActualPrice());
        row[2] = String.valueOf(item.getQuantity());
        row[3] = String.valueOf(item.getSolutionOrderItemTotal());

        ((DefaultTableModel) OrderItemsTable.getModel()).addRow(row);
        TotalTextField.setText(String.valueOf(currentOrder.getSolutionOrderTotal()));

    }//GEN-LAST:event_jButton1AddProductItemActionPerformed

    private void productPricePerformanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPricePerformanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPricePerformanceTextFieldActionPerformed

    private void productRevenueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productRevenueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productRevenueTextFieldActionPerformed

    private void productNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameTextFieldActionPerformed

    private void MarketComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarketComboBoxActionPerformed
        // TODO add your handling code here:
        refreshSupplierProductCatalogTable();
    }//GEN-LAST:event_MarketComboBoxActionPerformed

    private void SubproductsCatalogTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubproductsCatalogTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SubproductsCatalogTableMouseEntered

    private void SubproductsCatalogTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubproductsCatalogTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubproductsCatalogTableMousePressed

    private void MarketComboBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarketComboBoxMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarketComboBoxMousePressed

    private void TotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JComboBox<String> MarketComboBox;
    private javax.swing.JButton Next;
    private javax.swing.JTable OrderItemsTable;
    private javax.swing.JTextField PriceTextField;
    private javax.swing.JTable ProductCatalogTable;
    private javax.swing.JTextField QuantityTextField;
    private javax.swing.JTable SubproductsCatalogTable;
    private javax.swing.JTextField TotalTextField;
    private javax.swing.JTextField customerTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField productFrequencyAboveTargetTextField;
    private javax.swing.JTextField productFrequencyBelowTargetTextField;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JTextField productPricePerformanceTextField;
    private javax.swing.JTextField productRevenueTextField;
    private javax.swing.JTextField salesPersonTextField1;
    // End of variables declaration//GEN-END:variables
}
