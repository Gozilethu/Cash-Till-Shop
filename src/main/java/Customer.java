
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Customer { 
    /*
    
    */
    String jdbcUrl = "jdbc:mysql://localhost:3306/cashtillshopdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String username = "root";
    String password = ""; 
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;

    private int idCustomer;
    private String initialsCustomer;
    private String surnameCustomer;
    private int cellphoneCustomer;
    private String emailCustomer;
    private String addressCustomer;
    private double totalCustomer;
    private String usernameCustomer;
    private String passwordCustomer;
    
    public Customer(int idCustomer, String initialsCustomer, String surnameCustomer, int cellphoneCustomer, String emailCustomer, String addressCustomer, double totalCustomer, String usernameCustomer, String passwordCustomer) {
        this.idCustomer = idCustomer;
        this.initialsCustomer = initialsCustomer;
        this.surnameCustomer = surnameCustomer;
        this.cellphoneCustomer = cellphoneCustomer;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
        this.totalCustomer = totalCustomer;
        this.usernameCustomer = usernameCustomer;
        this.passwordCustomer = passwordCustomer;
    }

    Customer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
    public void setInitialsCustomer(String initialsCustomer) {
        this.initialsCustomer = initialsCustomer;
    }
    public void setSurnameCustomer(String surnameCustomer) {
        this.surnameCustomer = surnameCustomer;
    }
    public void setCellphoneCustomer(int cellphoneCustomer) {
        this.cellphoneCustomer = cellphoneCustomer;
    }
    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }
    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }
    public void setTotalCustomer(double totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
    public void setUsernameCustomer(String usernameCustomer) {
        this.usernameCustomer = usernameCustomer;
    }
    public void setPasswordCustomer(String passwordCustomer) {
        this.passwordCustomer = passwordCustomer;
    }
        
    public int getIdCustomer() {
        return this.idCustomer;
    }
    public String getInitialsCustomer() {
        return this.initialsCustomer;
    }
    public String getSurnameCustomer() {
        return this.surnameCustomer;
    }
    public int getCellphoneCustomer() {
        return this.cellphoneCustomer;
    }
    public String getEmailCustomer() {
        return this.emailCustomer;
    }
    public String getAddressCustomer() {
        return this.addressCustomer;
    }
    public double getTotalCustomer() {
        return this.totalCustomer;
    }
    public String getUsernameCustomer() {
        return this.usernameCustomer;
    }
    public String getPasswordCustomer() {
        return this.passwordCustomer;
    }
    
    public void login(String usernameCustomer, String passwordCustomer) {
        try {
            con = DriverManager.getConnection(jdbcUrl, username, password);
            st = con.createStatement();
            ps = con.prepareStatement("SELECT * FROM customerinfo_storetbl WHERE username=? AND password=?");
            ps.setString(6, usernameCustomer);
            ps.setString(7, passwordCustomer);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }

                con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createAccount( int idCustomer,String initialsCustomer, String surnameCustomer, int cellphoneCustomer, String emailCustomer, String addressCustomer, String usernameCustomer, String Conpassword, String passwordCustomer) {
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                rs = st.executeQuery("");            
                String query = "INSERT INTO customerinfo_storetbl values(?,?,?,?,?,?,?,?)";
                ps = (PreparedStatement) st.executeQuery(query);
                ps.setInt(0, idCustomer);
                ps.setString(1, initialsCustomer);
                ps.setString(2, surnameCustomer);
                ps.setDouble(3, cellphoneCustomer);
                ps.setString(4, emailCustomer);
                ps.setString(5, addressCustomer); 
                ps.setString(6, passwordCustomer);
                ps.setString(7, usernameCustomer);
                
                
                DefaultTableModel model = new DefaultTableModel();  
                st.executeUpdate(query);
                
                if(Conpassword.equals(passwordCustomer)) {
                    String row[] = {String.valueOf(idCustomer), initialsCustomer, surnameCustomer, String.valueOf(cellphoneCustomer), emailCustomer, addressCustomer, usernameCustomer, passwordCustomer};
                    model.addRow(row);
                    
                    JOptionPane.showMessageDialog(null, "Account Created Successfully :)");
                }else if(!Conpassword.equals(passwordCustomer)) {
                    JOptionPane.showMessageDialog(null, "PASSWORD ARE NOT THE SAME");
                }
                
                con.close();

            } catch (SQLException e) {
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public class productRenge {
        
        private int idProduct;
        private String itemProduct;
        private int unitProduct;
        private double amountProduct;
        private String expiryDateProduct;
        private String deliveryDateProduct;
        private int quantityProduct;
        
        public productRenge(int idProduct, String itemProduct, double amountProduct, String expiryDateProduct, String deliveryDateProduct, int unitProduct, int quantityProduct) {
            this.idProduct = idProduct;
            this.itemProduct = itemProduct;
            this.unitProduct = unitProduct;
            this.amountProduct = amountProduct;
            this.expiryDateProduct = expiryDateProduct;
            this.deliveryDateProduct = deliveryDateProduct;
            this.quantityProduct = quantityProduct;
            
        }

        productRenge() {
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
        
        
        public void checkOutCustomerMenu() throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                String query = "INSERT INTO carttbl values(?,?,?,?,?,?)";
                rs = st.executeQuery(query);
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        public void searchCustomerMenu(String searchProduct) throws ClassNotFoundException {    
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();

                String query = "SELECT * FROM storemanager_stocktbl WHERE nameStoreManager_Stock LIKE '%" + searchProduct + "%'";
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
                ex.printStackTrace();
            }
        }
        
        public void addToCart(int idProduct, String itemProduct, String unitProduct, double amountProduct, String expiryDateProduct, int quantityProduct) {
            try {
                con = DriverManager.getConnection(jdbcUrl, username, password);
                st = con.createStatement();
                String query = "INSERT INTO carttbl values(?,?,?,?,?,?)";
                ps = (PreparedStatement) st.executeQuery(query);
                ps.setInt(0, idProduct);
                ps.setString(1, itemProduct);
                ps.setString(2, unitProduct);
                ps.setDouble(3, amountProduct);
                ps.setString(4, expiryDateProduct);
                ps.setInt(5, quantityProduct);
                
                JOptionPane.showMessageDialog(null, "Added to Cart");
                
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void viewOrds() {
            try {
                con = DriverManager.getConnection(jdbcUrl, username, password);
                String query = "SELECT * FROM order_cutomertbl";
                PreparedStatement pps = con.prepareStatement(query);
                rs = pps.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                while(rs.next()) {
                   idProduct = rs.getInt("id");
                   itemProduct = rs.getString("name");
                   unitProduct = rs.getInt("units");
                   amountProduct = rs.getInt("price");
                   quantityProduct = rs.getInt("quantity");
                   expiryDateProduct = rs.getString("expiry_date");
                  
                   String row[] = {String.valueOf(idProduct), itemProduct, String.valueOf(unitProduct), String.valueOf(amountProduct), String.valueOf(quantityProduct), String.valueOf(expiryDateProduct)};
                   model.addRow(row);                   
               }
               con.close();
            } catch(SQLException e) {
           }
        }
    }
    
}
