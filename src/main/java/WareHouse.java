
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class WareHouse  {
    Statement st;
    ResultSet rs;
    Connection con;
    PreparedStatement ps;
    
    String jdbcUrl = "jdbc:mysql://localhost:3306/bbphongolashopdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String username = "root";
    String password = "";/*M#T7638phe@dataBase*/
    
    int falseId = 10000;
    String idName = "Prd";
    int id = falseId;
    
   public void generatePrimaryKey() {      
        if(id <= falseId) {
            falseId = falseId + 1;
            id = falseId;         
        }
        String idString = String.valueOf(falseId);
        idString = idName + idString; 
        
        System.out.println(idString);     
    }
    
    public class Product {
               
        private int idProduct;
        private String itemProduct;
        private int unitProduct;
        private double amountProduct;
        private String expiryDateProduct;
        private String deliveryDateProduct;
        private int quantityProduct;
        
        public Product(int idProduct, String itemProduct, double amountProduct, String expiryDateProduct, String deliveryDateProduct, int unitProduct, int quantityProduct) {
            this.idProduct = idProduct;
            this.itemProduct = itemProduct;
            this.unitProduct = unitProduct;
            this.amountProduct = amountProduct;
            this.expiryDateProduct = expiryDateProduct;
            this.deliveryDateProduct = deliveryDateProduct;
            this.quantityProduct = quantityProduct;
            
        }

        Product() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
       
        public void setIdProduct(int idProduct) {
            this.idProduct = idProduct;
        }
        public void setItemProduct(String itemProduct){
            this.itemProduct = itemProduct;
        }
        public void setUnitProduct(int unitProduct) {
            this.unitProduct = unitProduct;
        }
        public void setAmountProduct(double amountProduct) {
            this.amountProduct = amountProduct;
        }
        public void setExpiryDateProduct(String expiryDateProduct) {
            this.expiryDateProduct = expiryDateProduct;
        }
        public void setDeliveryDateProduct(String deliveryDateProduct) {
            this.deliveryDateProduct = deliveryDateProduct;
        }
        public void setQuantityProduct(int quantityProduct) {
            this.quantityProduct = quantityProduct;
        }
        
        
        public int getIdProduct() {
            return idProduct;
        }
        public String getItemProduct() {
            return itemProduct;
        }
        public int getUnitProduct() {
            return unitProduct;
        }
        public double getAmountProduct() {
            return amountProduct;
        }
        public String getExpiryDateProduct() {
            return expiryDateProduct;
        }    
        public String getDeliveryDateProduct() {
            return deliveryDateProduct;
        } 
        public int getQuantityProduct() {
            return this.quantityProduct;
        }
        
        public void login(String usernameCustomer, String passwordCustomer) {
            try {
                try (Connection con = DriverManager.getConnection("jdbcUrl", "username", "password")) {
                    try (PreparedStatement ps = con.prepareStatement("SELECT * FROM employee_storetbl WHERE username=? AND password=?")) {    
                        st = con.createStatement();
                        ps.setString(6, usernameCustomer);
                        ps.setString(7, passwordCustomer);
                        rs = ps.executeQuery();

                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                    JOptionPane.showMessageDialog(null, "Login successful!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                                }
                        }
                            con.close();
                        }
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void refreshJTable() throws SQLException {
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);          
            try (Connection con = DriverManager.getConnection(jdbcUrl)) {
                String query = "INSERT INTO product_warehousetbl (id, item, amount, units, expiry_date, delivery_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        idProduct = rs.getInt("id");
                        itemProduct = rs.getString("item");
                        unitProduct = rs.getInt("units");
                        amountProduct = rs.getInt("amount");
                        expiryDateProduct = rs.getString("expiry_date");
                        deliveryDateProduct = rs.getString("delivery_date");
                        quantityProduct = rs.getInt("quantity");                       
                                              
                        String[] row = {String.valueOf(idProduct), itemProduct, String.valueOf(amountProduct), expiryDateProduct, deliveryDateProduct, String.valueOf(unitProduct), String.valueOf(quantityProduct)};
                        model.addRow(row);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error connecting to the database or executing the query.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        public void addProductWareHouse(int idProduct, String itemProduct, double amountProduct, String expiryDateProduct, String deliveryDateProduct, int unitProduct, int quantityProduct) throws ClassNotFoundException {
            try{
                con = DriverManager.getConnection(jdbcUrl);
                st = con.createStatement();
                String query = "INSERT INTO product_warehousetbl (id, item, amount, units, expiry_date, delivery_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
                rs = st.executeQuery(query);
                
                ps.setInt(1, idProduct);
                ps.setString(2, itemProduct);
                ps.setDouble(3, amountProduct);
                ps.setInt(4, unitProduct);
                ps.setString(5, expiryDateProduct);
                ps.setString(6, deliveryDateProduct);
                ps.setInt(7, quantityProduct);
                
                int rowsInserted = ps.executeUpdate();
                
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Product added successfully");
                while(true) {refreshJTable();} 
                }
                con.close();
                while(true) {refreshJTable();} 
            } catch (SQLException e) {
                }
        }
        
        public void updateProductWareHouse(int idProduct, String itemProduct, double amountProduct, String expiryDateProduct, String deliveryDateProduct, int unitProduct, int quantityProduct) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                //INSERT INTO product_warehousetbl (id, item, amount, units, expiry_date, delivery_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)
                String query = "UPDATE product_warehousetbl SET id=?, item=?, amount=?, units=?, expiry_date=?, delivery_date=? quantity=?  WHERE warehouse_productbl";
                PreparedStatement ps = con.prepareStatement(query);
                DefaultTableModel model = new DefaultTableModel();

                String[] row = {String.valueOf(idProduct), itemProduct, String.valueOf(amountProduct), expiryDateProduct, deliveryDateProduct, String.valueOf(unitProduct), String.valueOf(quantityProduct)};
                model.addRow(row);
                refreshJTable();
            } catch(SQLException e) {
           }          
        }
        
        public String generateSlip(int id, String item, double amount, int units, String expiryDate, String deliveryDate, int qty) {
            StringBuilder slip = new StringBuilder();

            slip.append("Product ID: ").append(id).append("\n");
            slip.append("Item: ").append(item).append("\n");

            slip.append("Amount: ").append(amount).append("\n");
            slip.append("Units: ").append(units).append("\n");


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            slip.append("Expiry Date: ").append(dateFormat.format(expiryDate)).append("\n");
            slip.append("Delivery Date: ").append(dateFormat.format(deliveryDate)).append("\n");
            slip.append("Quantity: ").append(qty).append("\n");

            slip.append("-----------------\n");

            return slip.toString();
        }
        
        public void generateSlipWareHouse() {
            try {
                con = DriverManager.getConnection(jdbcUrl, username, password);
                String query = "SELECT * FROM product_warehousetbl";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    idProduct = rs.getInt("id");
                    itemProduct = rs.getString("item");
                    amountProduct = rs.getDouble("amount");
                    unitProduct = rs.getInt("units");
                    expiryDateProduct = rs.getString("expiry_date"); 
                    deliveryDateProduct = rs.getString("delivery_date"); 
                    quantityProduct = rs.getInt("quantity");
                }
                String slip = generateSlip(idProduct, itemProduct, amountProduct, unitProduct, expiryDateProduct, deliveryDateProduct, quantityProduct);
                System.out.println(slip);
                /*String[] row = {String.valueOf(idProduct), itemProduct, String.valueOf(amountProduct), String.valueOf(unitProduct), expiryDateProduct, deliveryDateProduct, String.valueOf(quantityProduct)};
                StringBuilder  slip = new StringBuilder();
                slip.append(row);
                
                for (String element : row) {
                    slip.append(element).append(" ");
                }*/
                
            } catch (SQLException ex) {
                Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
                Product prod = new Product();
                prod.generateSlip(id, idName, amountProduct, id, expiryDateProduct, deliveryDateProduct, id);
            }
        }
      
        public void printSlipWareHouse() throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                
                String query = "INSERT INTO product_warehousetbl (id, item, amount, units, expiry_date, delivery_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)"; 
                rs = st.executeQuery(query);
                StringBuilder reciept = new StringBuilder();
                
                while (rs.next()) {     
                    idProduct = rs.getInt("id");
                    itemProduct = rs.getString("item");
                    expiryDateProduct = rs.getString("expiry_date");
                    deliveryDateProduct = rs.getString("delivery_date");
                    amountProduct = rs.getDouble("amount");
                    unitProduct = rs.getInt("units");
                    quantityProduct = rs.getInt("quantity");

                    reciept.append(String.format("Receipt ID: %d\n", idProduct));
                    reciept.append(String.format("Item: %s\n", itemProduct));
                    reciept.append(String.format("Unit: %s\n", unitProduct));
                    reciept.append(String.format("Expiry Date: %s\n", expiryDateProduct));
                    reciept.append(String.format("Delivery Date: %s\n", deliveryDateProduct));
                    reciept.append(String.format("Price: R%.2f\\n", amountProduct));
                    reciept.append(String.format("Quantity: %s\n", quantityProduct));
                    reciept.append("\n");
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
            }    
        
        }
        
        public void saveChangesWareHouse() throws SQLException, ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String sql = "SELECT * FROM product_warehousetbl";
                con = DriverManager.getConnection(jdbcUrl, username, password);
                DefaultTableModel model = new DefaultTableModel();
                model.setRowCount(0);
                
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    idProduct = rs.getInt("id");
                    itemProduct = rs.getString("item");
                    amountProduct = rs.getDouble("amount");
                    unitProduct = rs.getInt("units");
                    expiryDateProduct = rs.getString("expiry_date");
                    deliveryDateProduct = rs.getString("delivery_date");
                    quantityProduct = rs.getInt("quantity");
                }
                JOptionPane.showMessageDialog(null, "Product saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshJTable();
            } catch (SQLException e) {
            }
            updateProductWareHouse(idProduct, itemProduct, amountProduct, expiryDateProduct, deliveryDateProduct, unitProduct, quantityProduct);
        }
        
        public void deleteProductWareHouse() throws ClassNotFoundException {
            try {
              
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
        
                String query = "DELETE FROM warehouse_productbl WHERE idWareHouse_Product=?";
                PreparedStatement pps = con.prepareStatement(query);

                pps.setInt(1, idProduct);

                int rowsAffected = pps.executeUpdate();
                if (rowsAffected > 0)
                {
                    JOptionPane.showMessageDialog(null, "PRODUCT WAS SUCCESSFULLY DELETED");

                } else {
                    JOptionPane.showMessageDialog(null, "No product found for the given product ID.");
                }
              
                DefaultTableModel model = new DefaultTableModel();
                String[] row = {String.valueOf(idProduct), itemProduct, String.valueOf(amountProduct), expiryDateProduct, deliveryDateProduct, String.valueOf(unitProduct)};
                model.addRow(row);

                con.close();
                refreshJTable();
            } catch (SQLException e) {
            }
            
        }
        
        public void searchProductWareHouse(String searchProduct) throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();

                String query = "SELECT * FROM warehouse_productbl WHERE idWareHouse_Product LIKE '%" + searchProduct + "%'";
                rs = st.executeQuery(query);

                DefaultTableModel model = new DefaultTableModel();
                
                ResultSetMetaData mData = rs.getMetaData();
                int columnCount = mData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(mData.getColumnName(i));
                }

                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }

                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
            } 
        }
        
        public void notifystoremanWareHouse() {
            try {
                String query = "SELECT idWareHouse_Product, nameWareHouse_Product, unitsWareHouse_Product FROM warehouse_productbl WHERE unitsWareHouse_Product <= 10";
                con = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement pps = con.prepareStatement(query);
                rs = pps.executeQuery();
                
                while (rs.next()) {
                    idProduct = rs.getInt("id");
                    itemProduct = rs.getString("nameWareHouse_Product");
                    int originalQuantity = 10;
                    unitProduct = rs.getInt("unitsWareHouse_Product");
                    
                    JOptionPane.showMessageDialog(null, "Product " + itemProduct + " (ID: " + idProduct + ") needs restocking.\nOriginal Quantity: " + originalQuantity+"\nCurrent Quantity: " + unitProduct+"");
                    refreshJTable();
                }
            } catch (SQLException ex) {
                Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class Suppliers {
                
        private int idSupplier;
        private String nameSupplier;
        private int contactSupplier;
        private String addressSupplier;
        private String genderSupplier;
        private String productSupplier;
        private String countrySupplier;
        
        public Suppliers(int idSupplier, String nameSupplier, int contactSupplier, String addressSupplier, String genderSupplier, String productSupplier, String countrySupplier) {
            this.idSupplier = idSupplier;
            this.nameSupplier = nameSupplier;
            this.contactSupplier = contactSupplier;
            this.addressSupplier = addressSupplier;
            this.genderSupplier = genderSupplier;
            this.productSupplier = productSupplier;
            this.countrySupplier = countrySupplier;
        }

        Suppliers() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        //Setters
        public void setIdSupplier(int idSupplier) {
            this.idSupplier = idSupplier;
        }
        public void setNameSupplier(String nameSupplier) {
            this.nameSupplier = nameSupplier;
        }
        public void setContactSupplier(int contactSupplier) {
            this.contactSupplier = contactSupplier;
        }
        public void setAddressSupplier(String addressSupplier) {
            this.addressSupplier = addressSupplier;
        }
        public void setGenderSupplier(String genderSupplier) {
            this.genderSupplier = genderSupplier;
        }
        public void setProductSupplier(String productSupplier) {
            this.productSupplier = productSupplier;
        }
        public void setCountrySupplier(String countrySupplier) {
            this.countrySupplier = countrySupplier;
        }
        
        //Getters
        public int getIdSupplier() {
            return this.idSupplier;
        }
        public String getNameSupplier() {
            return this.nameSupplier;
        }
        public int getContactSupplier() {
            return this.contactSupplier;
        }
        public String getAddressSupplier() {
            return this.addressSupplier;
        }
        public String getGenderSupplier() {
            return this.genderSupplier;
        }
        public String getProductSupplier() {
            return this.productSupplier;
        }
        public String getCountrySupplier() {
            return this.countrySupplier;
        }
        
        private void refreshJTable() throws SQLException {
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);          
            try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
                String query = "SELECT * FROM suppliers_warehousetbl";
                try (PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        idSupplier = rs.getInt("id");
                        nameSupplier = rs.getString("name");
                        contactSupplier = rs.getInt("contact");
                        addressSupplier = rs.getString("address");
                        productSupplier = rs.getString("product");
                        countrySupplier = rs.getString("country");
                        genderSupplier = rs.getString("gender");

                        String row[] = {String.valueOf(idSupplier), nameSupplier, String.valueOf(contactSupplier), addressSupplier, genderSupplier, productSupplier, countrySupplier};
                        model.addRow(row);
                    }
                }
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error connecting to the database or executing the query.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            
        }
        
        public void addSupplWareHouse(int idSupplier, String nameSupplier, int contactSupplier, String addressSupplier, String genderSupplier, String productSupplier, String countrySupplier) throws ClassNotFoundException, SQLException {
            try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO suppliers_warehousetbl (id, name, contact, address, product, country, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idSupplier); 
                    ps.setString(2, nameSupplier);
                    ps.setInt(3, contactSupplier); 
                    ps.setString(4, addressSupplier);
                    ps.setString(5, productSupplier);
                    ps.setString(6, countrySupplier);
                    ps.setString(7, genderSupplier);

                int rowsInserted = ps.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Supplier added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null, "Failed to add supplier.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            }
            refreshJTable();
        }
        
        public void displaySupplierWareHouse() throws ClassNotFoundException {
            try {
                con = DriverManager.getConnection(jdbcUrl, username, password);
                String query = "SELECT * FROM suppliers_warehousetbl";
                PreparedStatement pps = con.prepareStatement(query);
                rs = pps.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                while(rs.next()) {
                   idSupplier = rs.getInt("id");
                   nameSupplier = rs.getString("name");
                   contactSupplier = rs.getInt("contact");
                   addressSupplier= rs.getString("address");
                   genderSupplier = rs.getString("gender");
                   productSupplier = rs.getString("product");
                   countrySupplier = rs.getString("country");

                   String row[] = {String.valueOf(idSupplier), nameSupplier, String.valueOf(contactSupplier), addressSupplier, genderSupplier, productSupplier, countrySupplier};
                   model.addRow(row);                   
               }
               con.close();
               refreshJTable();
           } catch(SQLException e) {
           }
           
        }
        
        public void deleteSupplierWareHouse(int idSupplier) throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
        
                String query = "SELECT * FROM suppliers_warehousetbl";
                ps = con.prepareStatement(query);

                ps.setInt(1, idSupplier);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0)
                {
                    JOptionPane.showMessageDialog(null, "SUPPLIER WAS SUCCESSFULLY DELETED");

                } else {
                    JOptionPane.showMessageDialog(null, "No supplier found for the given ID.");
                }
              
                String[] row = {String.valueOf(idSupplier), nameSupplier, String.valueOf(contactSupplier), addressSupplier, genderSupplier, productSupplier, countrySupplier};
                DefaultTableModel model = new DefaultTableModel();
                model.addRow(row);

                con.close();
                
            } catch (SQLException e) {
            }
        }
        
        public void updateSupplierWareHouse(String nameSupplier, int contactSupplier, String addressSupplier, String genderSupplier, String productSupplier, String countrySupplier) throws ClassNotFoundException, SQLException {
            try(Connection con = DriverManager.getConnection(jdbcUrl, username, password)){                
                Class.forName("com.mysql.cj.jdbc.Driver");
                String query = "UPDATE suppliers_warehousetbl SET name=?, contact=?, address=?, product=?, country=?, gender=? WHERE id=?";
                DefaultTableModel model = new DefaultTableModel();
                try (PreparedStatement ps = con.prepareStatement(query)) {
                
                    ps.setInt(0, idSupplier);
                    ps.setString(1, nameSupplier);
                    ps.setInt(2, contactSupplier);
                    ps.setString(3, addressSupplier);
                    ps.setString(4, genderSupplier);
                    ps.setString(5, productSupplier);
                    ps.setString(6, countrySupplier);
                    } catch (SQLException ex) {                           
                    Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
                }
                String row[] = {String.valueOf(idSupplier), nameSupplier, String.valueOf(contactSupplier), addressSupplier, genderSupplier, productSupplier, countrySupplier};
                model.addRow(row);
                
                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Supplier updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null, "Failed to update supplier.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch(SQLException e) {
            }
        }
        
        public void searchSupplWareHouse(String searchSupplier) throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();

                String query = "SELECT * FROM suppliers_warehousetbl WHERE id LIKE '%" + searchSupplier + "%'";
                rs = st.executeQuery(query);

                DefaultTableModel model = new DefaultTableModel();
                
                ResultSetMetaData mData = rs.getMetaData();
                int columnCount = mData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(mData.getColumnName(i));
                }

                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }

                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
            } 
        }
        
    }
    
    public class Stock {
        Date date = new Date(System.currentTimeMillis());
                
        private int idStock;
        private String nameStock;
        private double amountStock;
        private int unitsStock;
        private double totalStock;
        private String stockDate = date.toString();
        private String text;
        
        public Stock(int idStock, String nameStock, double amountStock, int unitsStock, double totalStock, String stockDate, String text) {
            this.idStock = idStock;
            this.nameStock = nameStock;
            this.amountStock = amountStock;
            this.unitsStock = unitsStock;
            this.totalStock = totalStock;
            this.stockDate = stockDate;
            this.text = text;
        }

        Stock() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        public void setIdStock(int idStock) {
            this.idStock = idStock;
        }
        public void setNameStock(String nameStock) {
            this.nameStock = nameStock;
        }
        public void setAmountStock(double amountStock) {
            this.amountStock = amountStock;
        }
        public void setUnitsStock(int unitsStock) {
            this.unitsStock = unitsStock;
        }
        public void setTotalStock(double totalStock) {
            this.totalStock = totalStock;
        }
        public void setStockDate(String stockDate) {
            this.stockDate = stockDate;
        }
        public void setText(String text) {
            this.text = text;
        }
                
        public int getIdStock() {
            return this.idStock;
        }
        public String getNameStock() {
            return this.nameStock;
        }
        public double getAmountStock() {
            return this.amountStock;
        }
        public int getUnitsStock() {
            return this.unitsStock;
        }
        public double getTotalStock() {
            return this.totalStock;
        }
        public String getStockDate() {
            return this.stockDate;
        }
        public String getText() {
            return this.text;
        }
        
        private void refreshJTable() throws SQLException {
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);          
            try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
                String query = "SELECT * FROM supplies";
                try (PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        idStock = rs.getInt("supplierId");
                        nameStock = rs.getString("name");
                        amountStock = rs.getInt("contact");
                        unitsStock = rs.getInt("address");
                        stockDate = rs.getString("product");
                        

                        String row[] = {String.valueOf(idStock), nameStock, String.valueOf(amountStock), String.valueOf(unitsStock), stockDate};
                        model.addRow(row);
                    }
                }
            } catch (SQLException e) {
                // Handle database connection or SQL exceptions
                JOptionPane.showMessageDialog(null, "Error connecting to the database or executing the query.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        public void addStockWareHouse(int idStock, String nameStock, double amountStock, int unitsStock, String stockDate) throws ClassNotFoundException{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                rs = st.executeQuery("");
                String query = "INSERT INTO stock (stockId, name, amount, units, stock_date) VALUES (?, ?, ?, ?, ?)";
                totalStock = amountStock * unitsStock;
                DefaultTableModel model = new DefaultTableModel();
                            
                st.executeUpdate(query);
                String row[] = {String.valueOf(idStock), nameStock, String.valueOf(amountStock), String.valueOf(unitsStock), stockDate};
                model.addRow(row);
                
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Stock added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add stock.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                
                con.close();
                refreshJTable();
            } catch (SQLException e) {
            }
        }
        
        public void updateStockWareHouse(int idStock, String nameStock, double amountStock, int unitsStock, String stockDate) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String query = "UPDATE stock SET name=?, amount=?, units=?, stock_date=? WHERE stockId=?";
                ps = con.prepareStatement(query);
                DefaultTableModel model = new DefaultTableModel();
                
                ps.setString(1, nameStock);
                ps.setDouble(2, amountStock);
                ps.setDouble(3, unitsStock);
                ps.setString(4, stockDate);
                ps.setInt(5, idStock);
               
                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Stock data updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update stock data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                String row[] = {String.valueOf(idStock), nameStock, String.valueOf(amountStock), String.valueOf(unitsStock), stockDate};
                model.addRow(row);
                refreshJTable();
            } catch(SQLException e) {
            }
        }
        
        public void deleteStockWareHouse(int idStock) throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
        
                String query = "DELETE FROM stock WHERE stockId=?";
                PreparedStatement pps = con.prepareStatement(query);

                pps.setInt(1, idStock);

                int rowsAffected = pps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "STOCK WAS SUCCESSFULLY DELETED");

                } else {
                    JOptionPane.showMessageDialog(null, "No stock found for the given ID.");
                }
              
                String[] row = {String.valueOf(idStock), nameStock, String.valueOf(amountStock), String.valueOf(unitsStock), String.valueOf(totalStock), stockDate};
                DefaultTableModel model = new DefaultTableModel();
                model.addRow(row);

                con.close();
                refreshJTable();
            } catch (SQLException e) {
            }
        }
        
        public void saveStockWareHouse() throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String sql = "SELECT * FROM stock";
                con = DriverManager.getConnection(jdbcUrl, username, password);
                ps = con.prepareStatement(sql);
                DefaultTableModel model = new DefaultTableModel();
                
                while (rs.next()) {
                    idStock = rs.getInt("stockId");
                    nameStock = rs.getString("name");
                    amountStock = rs.getDouble("amount");
                    unitsStock = rs.getInt("units");
                    stockDate = rs.getString("stock_date");

                    model.addRow(new Object[]{idStock, nameStock, nameStock, amountStock, stockDate});
                }
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Error, Failed to save stock.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Stock saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }  
            } catch (SQLException e) {
            }
            updateStockWareHouse(idStock, nameStock, amountStock, unitsStock, stockDate);
        }
        
        public void notifyManager(){
            try {
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                String query = "SELECT * FROM stock_warehousetbl WHERE id= 1"; 
                rs = st.executeQuery(query);
                
                int lowStockThreshold = 50;
                StringBuilder lowStockProducts = new StringBuilder();

                while (rs.next()) {
                    nameStock = rs.getString("name");
                    amountStock = rs.getInt("amount");
                    unitsStock = rs.getInt("units");

                    if (unitsStock < (lowStockThreshold / 100.0) * 100) {
                        lowStockProducts.append(nameStock).append(" ").append(String.valueOf(unitsStock)).append("\n");
                    }
                }
                
                
                
                if (lowStockProducts.length() > 0) {
                     text = "Stock required for these products:\n\n" + lowStockProducts.toString();
                } else {
                     text = "No products require restocking at the moment.";
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        public void Generate() {
        
        }
        
        public void printSlipStockWareHouse() throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                
                String query = "SELECT * FROM stock"; 
                rs = st.executeQuery(query);
                StringBuilder reciept = new StringBuilder();
                
                while (rs.next()) {     
                    idStock = rs.getInt("stockId");
                    nameStock = rs.getString("name");
                    amountStock = rs.getDouble("amount");
                    unitsStock = rs.getInt("units");
                    stockDate = rs.getString("stock_date");

                    // Format the receipt information
                    reciept.append(String.format("Receipt ID: %d\n", idStock));
                    reciept.append(String.format("Item: %s\n", nameStock));
                    reciept.append(String.format("Date: %s\n", stockDate));
                    reciept.append(String.format("Price: R%.2f\\n", amountStock));
                    reciept.append(String.format("Units: %s\n", unitsStock));
                    reciept.append(String.format("Units: %s\n", totalStock));
                    reciept.append("\n");
                }
                
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
            }
        }
        
        public void requestedStock() {
            try {
                String[] columnNames = {"id", "product", "quantity", "Stock Date"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                
                con = DriverManager.getConnection(jdbcUrl, username, password);
                String sql = "SELECT id, product, quantity, stock_date FROM requeststocktbl ";
                ps  = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    int requestStockId = rs.getInt("id");
                    String product = rs.getString("product");
                    int quantity = rs.getInt("quantity");
                    java.sql.Date stockDate = rs.getDate("stock_date");
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(stockDate);
                    
                    Object[] rowData = {requestStockId, product, quantity, formattedDate};
                    
                    tableModel.addRow(rowData);
                }
            } catch (SQLException ex) {
                Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error connecting to the database or executing the query.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        
        }
        
    }
    
    public class Orders {
        WareHouse wh = new WareHouse();
        WareHouse.Stock st = wh.new Stock();
        WareHouse.Suppliers suppl = wh.new Suppliers();
        Date date = new Date(System.currentTimeMillis());
        String dt = date.toString();
                
        private int idOrder ;
        private String itemOrder = st.getNameStock() ;
        private double amountOrder;
        private int unitsOrder;
        private String purchaseDateOrder = dt;
        private String deliveryDateOrder;
        private String supplierOrder = suppl.getNameSupplier();
        private double totalOrder;
        private int quantityProduct;
        private String requestOrder;
        private String purchase_date;
        
        public Orders (int idOrder, String itemOrder, double amountOrder, int unitsOrder, String supplierOrder, String purchaseDateOrder, String deliveryDateOrder, double totalOrder, String requestOrder, String purchase_date, int quantityProduct) {
            this.idOrder = idOrder;
            this.itemOrder = itemOrder;
            this.amountOrder = amountOrder;
            this.unitsOrder = unitsOrder;
            this.supplierOrder = supplierOrder;
            this.purchaseDateOrder = purchaseDateOrder;
            this.deliveryDateOrder = deliveryDateOrder;
            this.totalOrder = totalOrder;
            this.requestOrder = requestOrder;
            this.purchase_date = purchase_date;
            this.quantityProduct = quantityProduct;
        }

        Orders() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        public void setIdOrder(int idOrder) {
            this.idOrder = idOrder;
        }
        public void setItemOrder(String itemOrder) {
            this.itemOrder = itemOrder;
        }
        public void setAmountOrder(double amountOrder) {
            this.amountOrder = amountOrder;
        }
        public void setUnitsOrder(int unitsOrder) {
            this.unitsOrder = unitsOrder;
        }
        public void setSupplierOrder(String supplierOrder) {
            this.supplierOrder = supplierOrder;
        }
        public void setPurchaseDateOrder(String purchaseDateOrder) {
            this.purchaseDateOrder = purchaseDateOrder;
        }
        public void setDeliveryDateOrder(String deliveryDateOrder) {
            this.deliveryDateOrder = deliveryDateOrder;
        }
        public void setTotalOrder(double totalOrder) {
            this.totalOrder = totalOrder;
        }
        public void setRequestOrder(String requestOrder) {
            this.requestOrder = requestOrder;
        }
        public void setPurchase_date(String purchase_date) {
            this.purchase_date = purchase_date;
        }
        public void setQuantityProduct(int quantityProduct) {
            this.quantityProduct = quantityProduct;
        }
        
        
        public int getIdOrder() {
            return this.idOrder;
        }
        public String getItemOrder() {
            return this.itemOrder;
        }
        public double getAmountOrder() {
            return this.amountOrder;
        }
        public double getUnitsOrder() {
            return this.unitsOrder;
        }
        public String getSupplierOrder() {
            return this.supplierOrder;
        } 
        public String getPurchaseDateOrder() {
            return this.purchaseDateOrder;
        }
        public String getDeliveryDateOrder() {
            return this.deliveryDateOrder;
        }
        public double getTotalOrder() {
            return this.totalOrder;
        }
        public String getRequestOrder() {
            return this.requestOrder;
        }
        public String getPurchase_date() {
            return this.purchase_date;
        }
        public int getQuantityProduct() {
            return this.quantityProduct;
        }
        
        
        public void searchOrderWareHouse(int searchOrderWareH) throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                Statement st = con.createStatement();

                String query = "SELECT * FROM orders_warehousetbl WHERE id LIKE '%" + searchOrderWareH + "%'";
                rs = st.executeQuery(query);

                DefaultTableModel model = new DefaultTableModel();
                
                ResultSetMetaData mData = rs.getMetaData();
                int columnCount = mData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(mData.getColumnName(i));
                }

                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }

                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
            }
        }
        
        public void createOrderWareHouse(int idOrder, String itemOrder, double amountOrder, int unitsOrder, String purchaseDateOrder, String deliveryDateOrder, int quantityProduct) throws ClassNotFoundException {

            try {
                con  = DriverManager.getConnection(jdbcUrl, username, password);
                String sql = "INSERT INTO orders_warehousetbl (id, item, amount, units, Qty, Exp_date, Purchase_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql); 
                ps.setInt(1, idOrder);
                ps.setString(2, itemOrder);
                ps.setDouble(3, amountOrder);
                ps.setInt(4, unitsOrder);
                ps.setInt(5, quantityProduct);
                ps.setString(6, deliveryDateOrder);
                ps.setString(7, purchaseDateOrder);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Order created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        public void placeOrderWareHouse() throws ClassNotFoundException {

            String sourceJdbcUrl = "jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String targetJdbcUrl = "jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL";

            try (Connection sourceConnection = DriverManager.getConnection(sourceJdbcUrl, username, password);
                 Connection targetConnection = DriverManager.getConnection(targetJdbcUrl, username, password)) {

                String selectSql = "SELECT * FROM orders_warehousetbl";
                try (PreparedStatement selectStatement = sourceConnection.prepareStatement(selectSql);
                     ResultSet resultSet = selectStatement.executeQuery()) {

                    String checkSql = "SELECT COUNT(*) AS count FROM orders_warehousetbl WHERE id=?";
                    String insertSql = "INSERT INTO placeorder_warehoousetbl (id, item, amount, units, Qty, expiry_date, purchase_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement checkStatement = targetConnection.prepareStatement(checkSql);
                         PreparedStatement insertStatement = targetConnection.prepareStatement(insertSql)) {
                        while (resultSet.next()) {
                            int orderId = resultSet.getInt("id");
                            String item = resultSet.getString("item");
                            double amount = resultSet.getDouble("amount");
                            String units = resultSet.getString("units");
                            int Qty = resultSet.getInt("Qty");
                            java.util.Date expiry_date = resultSet.getDate("expiry_date");
                            java.util.Date purchase_date = resultSet.getDate("purchase_date");

                            checkStatement.setInt(1, orderId);
                            ResultSet checkResult = checkStatement.executeQuery();
                            checkResult.next();
                            int count = checkResult.getInt("count");

                            if (count == 0) {
                                insertStatement.setInt(1, orderId);
                                insertStatement.setString(2, item);
                                insertStatement.setDouble(3, amount);
                                insertStatement.setString(4, units);
                                insertStatement.setInt(5, Qty);
                                insertStatement.setDate(6, (java.sql.Date) expiry_date);
                                insertStatement.setDate(7, (java.sql.Date) purchase_date);

                                insertStatement.executeUpdate();
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                } catch (SQLException e) {

                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error placing order.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
        }
        
        public void updateOrderWareHouse(int idOrder, String itemOrder, double amountOrder, int unitsOrder, String purchaseDateOrder, String deliveryDateOrder, int quantityProduct) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String query = "UPDATE orders_warehousetbl SET id=?, item=?, amount=?, Qty=?, Exp_date=?, Purchase_date=? WHERE orders_warehousetbl";
                con = DriverManager.getConnection(jdbcUrl, username, password);
                DefaultTableModel model = new DefaultTableModel();
                ps = con.prepareStatement(query);
                    ps.setInt(0, idOrder);
                    ps.setString(1, itemOrder);
                    ps.setDouble(2, amountOrder);
                    ps.setInt(3, unitsOrder);
                    ps.setString(4, purchaseDateOrder);
                    ps.setString(5, deliveryDateOrder);
                    ps.setInt(6, quantityProduct);

                String[] row = {String.valueOf(idOrder), itemOrder, String.valueOf(amountOrder), String.valueOf(unitsOrder), purchaseDateOrder, deliveryDateOrder, String.valueOf(quantityProduct)};
                model.addRow(row);

            } catch(SQLException e) {
            }
        }
        
        public void viewOrderWareHouse() {
            try {
                con = DriverManager.getConnection(jdbcUrl, username, password);
                String sql = "SELECT * FROM orders_warehousetbl";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
               
                while(rs.next())
                {
                    idOrder = rs.getInt("id");
                    itemOrder = rs.getString("item");
                    amountOrder = rs.getDouble("amount");
                    unitsOrder = rs.getInt("units");
                    quantityProduct = rs.getInt("Qty");
                    deliveryDateOrder = rs.getString("Exp_date");
                    purchaseDateOrder = rs.getString("Purchase_date");

                    String [] row = {String.valueOf(idOrder), itemOrder, String.valueOf(amountOrder), String.valueOf(unitsOrder), purchaseDateOrder, deliveryDateOrder, String.valueOf(quantityProduct)};
                    DefaultTableModel model = new DefaultTableModel();
                    model.addRow(row);
                }
                con.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(WareHouse.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
    } 
}