
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class SalesPerson extends storeMenu {
    String jdbcUrl = "jdbc:mysql://localhost:3306/cashtillshopdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String username = "root";
    String password = "";
    
    
    PreparedStatement ps;
    private String usernameSalesPerson;
    private String passwordSalesPerson;
    
    public SalesPerson(String usernameSalesPerson, String passwordSalesPerson) {
        this.usernameSalesPerson = usernameSalesPerson;
        this.passwordSalesPerson = passwordSalesPerson;
    }

    SalesPerson() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void setUsernameSalesPerson(String usernameSalesPerson) {
        this.usernameSalesPerson = usernameSalesPerson;
    }
    public void setPasswordSalesPerson(String passwordSalesPerson) {
        this.passwordSalesPerson = passwordSalesPerson;
    }
        
    public String getUsernameSalesPerson() {
        return this.usernameSalesPerson;
    }
    public String getPasswordSalesPerson() {
        return this.passwordSalesPerson;
    }
    
    public void login(String emlpUsername, String emlpPassword) {
        try {
            con = DriverManager.getConnection(jdbcUrl, username, password);
            st = con.createStatement();
            ps = con.prepareStatement("SELECT * FROM employee_storetbl WHERE username=? AND password=?");
            ps.setString(6, emlpUsername);
            ps.setString(7, emlpPassword);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }

                con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getUserInfo(){
        try {
            storeMenu storeMn = new storeMenu();
            storeMenu.Employee empl = storeMn.new Employee();
            usernameSalesPerson = empl.getUsername();
            passwordSalesPerson = empl.getPassword();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AcceptQoutation() {
    String sourceJdbcUrl = "jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String targetJdbcUrl = "jdbc:mysql://localhost:3306/bbphongolashopdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    try (Connection sourceConnection = DriverManager.getConnection(sourceJdbcUrl, username, password);
         Connection targetConnection = DriverManager.getConnection(targetJdbcUrl, username, password)) {

        String selectSql = "SELECT * FROM quotationtbl";
        try (PreparedStatement selectStatement = sourceConnection.prepareStatement(selectSql);
             ResultSet resultSet = selectStatement.executeQuery()) {

        String checkSql = "SELECT COUNT(*) AS count FROM acceptedquotationtbl WHERE id=?";
        String insertSql = "INSERT INTO acceptedquotationtbl (id, item, units, price, quantity, expiry_date, accepted) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement checkStatement = targetConnection.prepareStatement(checkSql);
             PreparedStatement insertStatement = targetConnection.prepareStatement(insertSql)) {
            while (resultSet.next()) {
                int acceptedquotationId = resultSet.getInt("id");
                String name = resultSet.getString("item");
                String units = resultSet.getString("units");
                String amountString = resultSet.getString("price");
                double amount;
                try {
                    amount = Double.parseDouble(amountString.replace(",", "."));
                } catch (NumberFormatException e) {
                    amount = 0.0; 
                }
                int quantity = resultSet.getInt("qauntity");
                Date expiry_date = resultSet.getDate("expiry_date");

                checkStatement.setInt(1, acceptedquotationId);
                ResultSet checkResult = checkStatement.executeQuery();
                checkResult.next();
                int count = checkResult.getInt("count");

                if (count == 0) {
                    insertStatement.setInt(1, acceptedquotationId);
                    insertStatement.setString(2, name);
                    insertStatement.setString(3, units);
                    insertStatement.setDouble(4, amount);
                    insertStatement.setInt(5, quantity);
                    insertStatement.setDate(6, (java.sql.Date) expiry_date);

                    insertStatement.executeUpdate();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Quotation accepted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "There was an error in accepting quotation, Try again later.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public void viewQuotation() {
    
    }
    
    public class orderSalesPerson extends Stock {
        
        
        
        public void updateOrderSale(int id, String name, String stockDate, double amount, String invoice) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");

                String query = "UPDATE stock_warehousetbl SET name=?, date=?, units=?, amount=? WHERE stock_warehousetbl";
                ps = con.prepareStatement(query);
                ps.setInt(0, id);
                ps.setString(1, name);
                ps.setString(2, stockDate);
                ps.setDouble(3, amount);
                ps.setString(4, invoice);
                
                DefaultTableModel model = new DefaultTableModel();
                String row[] = {String.valueOf(id), name, stockDate, String.valueOf(amount), invoice};
                model.addRow(row);
                
                ps.executeUpdate();
            } catch (SQLException e) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SalesPerson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void cancelOrderSale(int id) {
            try {
                String query = "UPDATE stock_warehousetbl SET amount = 0 WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(0, id);
                
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }
        
        public void searchOrderSale(String searchOrder) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                st = con.createStatement();

                String query = "SELECT * FROM stock_warehousetbl WHERE name LIKE '%" + searchOrder + "%'";
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
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SalesPerson.class.getName()).log(Level.SEVERE, null, ex);
            }
            prod.notifystoremanWareHouse();
        
        }
    }
    
    public class productSalesPerson extends WareHouse {
        WareHouse wh = new WareHouse();
        WareHouse.Product prod = wh.new Product();
        
        
        public void searchProductSale(String searchProduct) throws ClassNotFoundException {
            prod.searchProductWareHouse(searchProduct);
        }
        
        public void soledProductSale() {
            try{
                String query = "UPDATE warehouse_producttbl SET is_sold = ? WHERE product_id = ?";
                ps = con.prepareStatement(query);
                ps.setBoolean(5, true); 
                ps.setInt(0, id);
                ps.executeUpdate();
               
            } catch (SQLException e) {
            }
        }
        
        public void highSellingProductSale() {
            
            ArrayList<String> highSellingProducts = new ArrayList<>();
            int minSalesCount = 50;
            try{
                
                String query = "SELECT name FROM product_warehousetbl WHERE quantity >= 50";
                ps = con.prepareStatement(query);
                ps.setInt(1, minSalesCount);
                rs = ps.executeQuery();

                while (rs.next()) {
                    String productName = rs.getString("name");
                    highSellingProducts.add(productName);
                }
            } catch (SQLException e) {
            }
            JOptionPane.showMessageDialog(null, "High selling product is" + highSellingProducts);
            
        }
        
        public void lowSellingProductSale() {
            ArrayList<String> lowSellingProducts = new ArrayList<>();
            int maxSalesCount = 50;
            
            try{
                
                String query = "SELECT name FROM product_warehousetbl WHERE quantity <= 50";
                ps = con.prepareStatement(query);
                ps.setInt(1, maxSalesCount);
                rs = ps.executeQuery();

                while (rs.next()) {
                    String productName = rs.getString("name");
                    lowSellingProducts.add(productName);
                }
            } catch (SQLException e) {
            }
            JOptionPane.showMessageDialog(null, "Low selling product is" + lowSellingProducts);
           
        }
    
        public void damagedProductSale() {
            try {
                String query = "UPDATE product_warehousetbl SET isdamaged = ? WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setBoolean(6, true); 
                ps.setInt(1, id);
                ps.executeUpdate();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error DataBase Connection!!!!");
            }
        }
        
        public double calculateDamagedProductValue() {
            double totalValue = 0.0;
                try {
                    String query = "SELECT SUM(amount) FROM product_warehousetbl WHERE isdamaged = ?";
                    ps = con.prepareStatement(query);
                    ps.setBoolean(1, true);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        totalValue = rs.getDouble(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
                return totalValue;
        }
        
        public void refreshSalesProductJTable() throws SQLException {
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);          
            try (Connection con = DriverManager.getConnection(jdbcUrl)) {
                String query = "INSERT INTO product_warehousetbl (id, item, amount, units, expiry_date, delivery_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int idProduct = rs.getInt("id");
                        String itemProduct = rs.getString("item");
                        int amount = rs.getInt("units");
                        int units = rs.getInt("amount");
                        String expiryDateProduct = rs.getString("expiry_date");
                        String deliveryDateProduct = rs.getString("delivery_date");
                        int quantityProduct = rs.getInt("quantity");
                        String isdamaged = rs.getString("isdamaged");
                                              
                        String[] row = {String.valueOf(idProduct), itemProduct, String.valueOf(amount), String.valueOf(units), expiryDateProduct, deliveryDateProduct, String.valueOf(quantityProduct), isdamaged};
                        model.addRow(row);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error connecting to the database or executing the query.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    
    public class discountSales {
    
    }
    
    public class reportSales {
    
    }
    
    public class orderSales {
    
    }
    
}
