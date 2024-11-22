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
public class storeMenu /*implements bestBrightness*/ {
    Statement st;
    ResultSet rs;
    Connection con;
    String jdbcUrl = "jdbc:mysql://localhost:3306/cashtillshopdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String username = "root";
    String password = ""; 
    WareHouse wh = new WareHouse();
    WareHouse.Product prod = wh.new Product();
    
    /*
        Employee Class
    */
    public class Employee {
        
        Employee() throws SQLException {
            
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public void autoConnectDB() {
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
            } catch (SQLException e) {
            }
        }

        private int id;
        private String initials;
        private String surname;
        private String department;
        private String workTime;
        private String email;
        private int cellphone;
        private String username;
        private String password;    


        public Employee(int id, String initials, String surname, String department, String workTime, int cellphone, String email, String username, String password) throws SQLException {
            this.id = id;
            this.initials = initials;
            this.surname = surname;
            this.department = department;
            this.workTime = workTime;
            this.email = email;
            this.cellphone = cellphone;
            this.username = username;
            this.password = password;
        }

        //Setters
        public void setId(int id) {
            this.id = id; 
        }
        public void setInitials(String initials) {
            this.initials = initials;
        }
        public void setSurname(String surname) {
            this.surname = surname;
        }
        public void setDepartment(String department) {
            this.department = department;
        }
        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setCellphone(int cellphone) {
            this.cellphone = cellphone;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public void setPassword(String password) {
            this.password = password;
        }

        //Getters
        public int getId() {
            return this.id;
        }
        public String getInitials() {
            return this.initials;
        }
        public String getSurname() {
            return this.surname;
        }
        public String getDepartment() {
            return this.department;
        }
        public String getWorkTime() {
            return this.workTime;
        }
        public String getEmail() {
            return this.email;
        }
        public int getCellphone() {
            return this.cellphone;
        }
        public String getUsername() {
            return this.username;
        }
        public String getPassword() {
            return this.password;
        }

       public void addEmployee(int id, String initials, String surname, String department, String workTime, int cellphone, String email, String username, String password) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbcUrl, username, password");
                st = con.createStatement();
                String query = "INSERT INTO StoreManager_Empolyeetbl values(?,?,?,?,?,?,?,?,?)";
                rs = st.executeQuery(query);
                
                DefaultTableModel model = new DefaultTableModel();

                st.executeUpdate(query);
                String row[] = {String.valueOf(id), initials, surname, department, workTime, String.valueOf(cellphone), email, username, password};
                model.addRow(row);

                JOptionPane.showMessageDialog(null, "Employee added successfully ");
                con.close();

           } catch (SQLException e) {
           }
       }

       public void displayEmployee() throws ClassNotFoundException {
           try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection("jdbcUrl, username, password");
               String query = "SELECT * FROM StoreManager_Empolyeetbl";
               PreparedStatement pps = con.prepareStatement(query);
               rs = pps.executeQuery();

               DefaultTableModel model = new DefaultTableModel();
               while(rs.next()) {
                   id = rs.getInt("idStoreManager_Empolyee");
                   initials = rs.getString("initialsStoreManager_Empolyee");
                   surname = rs.getString("surnameStoreManager_Empolyee");
                   department = rs.getString("departmentStoreManager_Empolyee");
                   workTime = rs.getString("workTimeStoreManager_Empolyee");
                   cellphone = rs.getInt("cellNumberStoreManager_Empolyee");
                   email = rs.getString("emailStoreManager_Empolyee");
                   username = rs.getString("usernameStoreManager_Empolyee");
                   password = rs.getString("passWordStoreManager_Empolyee");

                   String row[] = {String.valueOf(id), initials, surname, department, workTime, String.valueOf(cellphone), email, username, password};
                   model.addRow(row);                   
               }
               con.close();
           } catch(SQLException e) {
           }
       }

        public void deleteEmployee(int id) throws ClassNotFoundException {
            try{
                Class.forName("jdbcUrl, username, password");
                String query = "DELETE FROM StoreManager_Empolyeetbl WHERE idStoreManager_Empolyee=?";
                PreparedStatement pps = con.prepareStatement(query);
                pps.setInt(1, id);

                int row = pps.executeUpdate();
                if(row > 0) {
                    JOptionPane.showMessageDialog(null, "Employee Successfully Deleted");
                } else {
                    JOptionPane.showMessageDialog(null, "Employee Not found");
                }

                con.close();

            } catch(SQLException e) {
            }
        }

        public void updateEmployee(int id, String initials, String surname, String department, String workTime, int cellphone, String email, String username, String password) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String query = "UPDATE StoreManager_Empolyeetbl SET initialsStoreManager_Empolyee=?, surnameStoreManager_Empolyee=?, departmentStoreManager_Empolyee=?, workTimeStoreManager_Empolyee=?, cellNumberStoreManager_Empolyee=?, emailStoreManager_Empolyee=?, addressStoreManager_Empolyee=? WHERE StoreManager_Empolyeetbl";
                PreparedStatement pps = con.prepareStatement(query);
                DefaultTableModel model = new DefaultTableModel();

                String row[] = {String.valueOf(id), initials, surname, department, workTime, String.valueOf(cellphone), email, username, password};
                model.addRow(row);

            } catch(SQLException e) {
            }       
        }
        
        public void searchEmployeeMn(String searchEmployee) throws ClassNotFoundException {    
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbcUrl, username, password");
                st = con.createStatement();

                String query = "SELECT * FROM StoreManager_Empolyeetbl WHERE idStoreManager_Empolyee LIKE '%" + searchEmployee + "%'";
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
    /*
        Customer Class
    */
    public class Customer {
        
        public void viewCustomer() throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                String query = "SELECT * FROM StoreManager_Customertbl";
                PreparedStatement pps = con.prepareStatement(query);
                rs = pps.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                while(rs.next()) {
                   int idCustomer = rs.getInt("idStoreManager_Customer");
                   String initialsCustomer = rs.getString("initialsStoreManager_Customer");
                   String surnameCustomer = rs.getString("surnameStoreManager_Customer");
                   int cellphoneCustomer = rs.getInt("cellphoneStoreManager_Customer");
                   String emailCustomer = rs.getString("emailStoreManager_Customer");
                   String addressCustomer = rs.getString("addressStoreManager_Customer");
                   String totalorderCustomer = rs.getString("totalOrderStoreManager_Customer");
                   String usernameCustomer = rs.getString("usernameStoreManager_Customer");
                   String passwordCustomer = rs.getString("passWordStoreManager_Customer");

                   String row[] = {String.valueOf(idCustomer), initialsCustomer, surnameCustomer, String.valueOf(cellphoneCustomer), emailCustomer, addressCustomer, totalorderCustomer, usernameCustomer, passwordCustomer};
                   model.addRow(row);                   
               }
               con.close();
           } catch(SQLException e) {
           }
        }
        
        ///Delete Customer
        public void deleteCustomer(int idCustomer) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String query = "DELETE FROM StoreManager_Customertbl WHERE idStoreManager_Customer=?";
                PreparedStatement pps = con.prepareStatement(query);
                pps.setInt(0, idCustomer);

                int row = pps.executeUpdate();
                if(row > 0) {
                    JOptionPane.showMessageDialog(null, "Customer Successfully Deleted");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer Not found");
                }

                con.close();

            } catch(SQLException e) {
            }
        }
    }
    
    
    /*
        Financial Report Class
    */
    class financialReport {
        
        public void printReport() {
            // iyangihlula madoda ifinancial Report 
            
            
        }
    }
    
    /*
        Stock Class
    */
    class Stock {
        private int id;
        private String name;
        private String supplier;
        private String stockDate;
        private double amount;
        private String invoice;


        public Stock(int id, String name, String supplier, String stockDate, double amount, String invoice) throws SQLException {
            this.id = id;
            this.name = name;
            this.supplier = supplier;
            this.stockDate = stockDate;
            this.amount = amount;
            this.invoice = invoice;
        }

        Stock() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        //Setters
        public void setId(int id) {
            this.id = id; 
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }
        public void setStockDate(String stockDate) {
            this.stockDate = stockDate;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        //Getters
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getSupplier() {
            return supplier;
        }
        public String getStockDate() {
            return stockDate;
        }
        public double getAmount() {
            return amount;
        }
        public String getInvoice() {
            return invoice;
        }
        
        public void addStock(int id, String name, String supplier, String stockDate, double amount, String invoice) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                st = con.createStatement();
                rs = st.executeQuery("");
                String query = "INSERT INTO stocktbl values(?,?,?,?)";

                DefaultTableModel model = new DefaultTableModel();

                st.executeUpdate(query);
                String row[] = {String.valueOf(id), name, supplier, stockDate, String.valueOf(amount), invoice};
                model.addRow(row);

                JOptionPane.showMessageDialog(null, "Product added successfully ");
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        
        public void displayStock() throws ClassNotFoundException {
           try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                String query = "SELECT * FROM stocktbl";
                PreparedStatement pps = con.prepareStatement(query);
                rs = pps.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                while(rs.next()) {
                    id = rs.getInt("stock_id");
                    name = rs.getString("stock_name");
                    supplier = rs.getString("stock_supplier");
                    stockDate = rs.getString("stock_stockDate");
                    amount = rs.getDouble("stock_amount");
                    invoice = rs.getString("stock_invoice");

                   String row[] = {String.valueOf(id), name, supplier, stockDate, String.valueOf(amount), invoice};
                   model.addRow(row);                   
               }
               con.close();
           } catch(SQLException e) {
           }
           
           prod.notifystoremanWareHouse();

        }
      
        public void updateStock(int id, String name, String supplier, String stockDate, double amount, String invoice) throws ClassNotFoundException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                String query = "UPDATE stocktbl SET stock_name=?, stock_supplier=?, stock_stockDate=?, stock_amount=?, stock_invoice=? WHERE stocktbl";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(0, id);
                ps.setString(1, name);
                ps.setString(2, supplier);
                ps.setString(3, stockDate);
                ps.setDouble(4, amount);
                ps.setString(4, invoice);
                
                DefaultTableModel model = new DefaultTableModel();
                String row[] = {String.valueOf(id), name, supplier, stockDate, String.valueOf(amount), invoice};
                model.addRow(row);
                
            } catch(SQLException e) {
           }          
        }
        
        public void searchStockMn(String searchStock) throws ClassNotFoundException {    
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                st = con.createStatement();

                String query = "SELECT * FROM stocktbl WHERE stock_name LIKE '%" + searchStock + "%'";
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
            prod.notifystoremanWareHouse();
        }
        
        public void printRecieptStock() throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                st = con.createStatement();
                
                String query = "SELECT * FROM stocktbl WHERE stock_id = 1"; 
                rs = st.executeQuery(query);
                StringBuilder reciept = new StringBuilder();
                
                while (rs.next()) {     
                    id = rs.getInt("stock_id");
                    name = rs.getString("stock_name");
                    supplier = rs.getString("stock_supplier");
                    stockDate = rs.getString("stock_stockDate");
                    amount = rs.getDouble("stock_amount");
                    invoice = rs.getString("stock_invoice");

                    // Format the receipt information
                    reciept.append(String.format("Receipt ID: %d\n", id));
                    reciept.append(String.format("Item: %s\n", name));
                    reciept.append(String.format("Date: %s\n", stockDate));
                    reciept.append(String.format("Price: R%.2f\\n", amount));
                    reciept.append(String.format("Invoice: %s\n", invoice));
                    reciept.append("\n");
                }
                
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
            }    
             
        }
        
        public void checkForRestock() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                                
                st = con.createStatement();

                String sql = "SELECT FROM stocktbl item_id, stock, max_stock ";
                rs = st.executeQuery(sql);
                
                String tableName = "stocktbl"; 
                String sql1 = "SELECT COUNT(*) FROM " + tableName;
                rs = st.executeQuery(sql1);
                int rowCount;  
                            
                while (rs.next()) {
                    name = rs.getString("stock_name");
                    rowCount = rs.getInt(1);
                    int maxStock = 30;
                    
                    double threshold = 0.5; 

                    if ((double)rowCount / maxStock < threshold) {
                        JOptionPane.showMessageDialog(null, "Item " + name + " needs restocking.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Item " + name + " is in good stock.");
                    }  
                }

            } catch (SQLException sql) {
            } catch (Exception e) {
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (st != null) st.close();
                } catch (SQLException e) {
                }
                try {
                    if (con != null) con.close();
                } catch (SQLException se) {
                }
            }
        }
       
    }
    
    /*
        Privileges Class
    */
    class privileges {
        
        public void autoConnectDB() {
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
           
        } catch (SQLException e) {
            }
        }
        
        
        
        public void searchEmployeeMn(String searchPrivileges) throws ClassNotFoundException {    
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                st = con.createStatement();

                String query = "SELECT * FROM employeetbl WHERE employee_id LIKE '%" + searchPrivileges + "%'";
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
    
        public void assignPrivileges(String privileg) throws ClassNotFoundException {
            try {  
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestbrightnessdb?zeroDateTimeBehavior=CONVERT_TO_NULL");
                String query = "UPDATE StoreManager_Empolyeetbl SET departmentStoreManager_Empolyee=? WHERE StoreManager_Empolyeetbl";
                             
                st = con.createStatement();
                rs = st.executeQuery(query);
                
                int id = rs.getInt("idStoreManager_Empolyee");
                String initails = rs.getString("initialsStoreManager_Empolyee");
                String surname = rs.getString("surnameStoreManager_Empolyee");
                String cellphne = rs.getString("cellNumberStoreManager_Empolyee");
                String password = rs.getString("employee_amount");
                
                DefaultTableModel model = new DefaultTableModel();
                String row[] = {String.valueOf(id), initails, surname, cellphne, password, privileg}; 
                model.addRow(row);
            } catch (SQLException ex) {
                Logger.getLogger(storeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
   
    
}
