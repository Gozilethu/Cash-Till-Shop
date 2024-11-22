/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author admin
 */
public interface bestBrightnes {
        
    public void generatePrimaryKey();
    
    /*  
        Store Menu
    */
    //Employee
    public void addEmployee();
    public void displayEmployee();
    public void deleteEmployee();
    public void updateEmployee();
    public void searchEmployeeWareHouse();
    public void deleteCustomer();
    
    
    //Financial Report Class
    public void threeMonthsFR();
    public void sixMonthsFR();
    public void twelveMonthsFR();
    public void printReport();
    
    /* 
        WareHouse Menu 
    */
    //product  WareHouse
    public void addProductWareHouse();
    public void updateProductWareHouse();
    public void generateSlipWareHouse();
    public void printSlipWareHouse();
    public void deleteProductWareHouse();
    public void saveChangesWareHouse();
    public void searchProductWareHouse();
    
    //Suppliers WareHouse
    public void addSupplWareHouse();
    public void displaySupplierWareHouse();
    public void deleteSupplierWareHouse();
    public void updateSupplierWareHouse();
    public void searchSupplWareHouse();
    
    //Stock WareHouse
    public void addStockWareHouse();
    public void updateStockWareHouse();
    public void deleteStockWareHouse();
    public void saveStockWareHouse();
    public void displayStockWareHouse();
    public void printSlipStockWareHouse();
    public void checkForRestock();
    public void notifyManager();
    public void requestedStock();
    
    //Order WareHouse
    public void searchOrderWareHouse();
    public void createOrderWareHouse();
    public void placeOrderWareHouse();
    public void updateOrderWareHouse();
    public void cancelOrderWareHouse();
    public void viewOrderWareHouse();
    
    /* 
        Sales Menu
    */
    //Quotetion Sales
    public void slipQuotation();
        
    //Order Sales
    public void updateOrderSale();
    public void cancelOrderSale();
    public void searchOrderSale();
    
    //Product Sales
    public void searchProductSale();
    public void soledProductSale();
    public void highSellingProductSale();
    public void lowSellingProductSale();
    public void damagedProductSale();
    
    //Discount Sales
    public void searchDiscountSales();
    public void issueDiscountSales();
    public void reverseDiscountSales();
    
    //Report Sales
    public void threeMonthsReportSales();
    public void sixMonthsReportSales();
    public void twelveMonthsReportSales();
    
    //Order Sales
    public void viewOrderSales();
    public void stockTakeOrderSales();
    public void notifyToRestockOrderSales();
    
    /*  
        Customer Menu
    */
    //Product Renge Customer Menu
    public void checkOutCustomerMenu();
    public void requstCustomerMenu();
    public void searchCustomerMenu();
    public void purchaseCustomerMenu();
    public void favorateCustomerMenu();
    
    //Shopping Curt Customer Menu
    public void updateOrderCustomerMenu();
    public void cancelOrderCustomerMenu();
    public void viewOrderCustomerMenu();
    public void saveOrderCustomerMenu();
    public void makePaymentCustomerMenu();
}
