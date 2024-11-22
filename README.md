# Cash-Till-Shop Software

## Overview
**Cash-Till-Shop** is a comprehensive shopping software designed to streamline operations across multiple domains such as employee management, warehouse management, financial reporting, sales, and customer interactions. It is a Java-based system that integrates functionalities to automate and optimize tasks, ensuring efficiency and accuracy.

---

## Features

### 1. **Employee Management**
   - **Add Employee:** Create employee records.
   - **Display Employee:** View all employee records.
   - **Delete Employee:** Remove an employee record.
   - **Update Employee:** Modify employee details.
   - **Search Employee (Warehouse):** Search for employee records in the warehouse.
   - **Delete Customer:** Remove customer records associated with employees.

---

### 2. **Financial Reporting**
   - Generate financial reports for:
     - Last 3 months.
     - Last 6 months.
     - Last 12 months.
   - **Print Reports:** Export financial reports for record-keeping.

---

### 3. **Warehouse Management**
#### Product Management
   - **Add Products:** Register new products in the warehouse.
   - **Update Products:** Modify product details.
   - **Generate Slips:** Create slips for warehouse products.
   - **Print Slips:** Print generated slips.
   - **Delete Products:** Remove products from the warehouse.
   - **Save Changes:** Persist product updates to the database.
   - **Search Products:** Look up products in the warehouse inventory.

#### Supplier Management
   - **Add Suppliers:** Add new suppliers to the system.
   - **Display Suppliers:** View all supplier records.
   - **Delete Suppliers:** Remove supplier records.
   - **Update Suppliers:** Modify supplier details.
   - **Search Suppliers:** Find specific supplier records.

#### Stock Management
   - **Add Stock:** Register new stock.
   - **Update Stock:** Modify existing stock records.
   - **Delete Stock:** Remove stock records.
   - **Save Stock:** Save stock changes.
   - **Display Stock:** View current stock inventory.
   - **Print Stock Slips:** Print inventory slips for stock.
   - **Restock Notifications:** Notify managers when stock levels are low.
   - **Requested Stock:** Manage stock requests.

#### Order Management
   - **Search Orders:** Find warehouse orders.
   - **Create Orders:** Generate new orders for the warehouse.
   - **Place Orders:** Finalize and place orders.
   - **Update Orders:** Modify existing orders.
   - **Cancel Orders:** Cancel orders that are no longer needed.
   - **View Orders:** View warehouse order history.

---

### 4. **Sales Management**
#### Quotation Management
   - **Generate Quotations:** Create and print quotation slips for customers.

#### Order Management
   - **Update Orders:** Update sales orders.
   - **Cancel Orders:** Cancel sales orders.
   - **Search Orders:** Look up sales orders.

#### Product Management
   - **Search Products:** Find products for sale.
   - **Mark Products as Sold:** Track sold items.
   - **Analyze Sales:** Identify high-selling and low-selling products.
   - **Manage Damaged Products:** Record damaged products and calculate their value.

#### Discount Management
   - **Search Discounts:** Look up discounts available for products.
   - **Issue Discounts:** Apply discounts to products.
   - **Reverse Discounts:** Revoke previously applied discounts.

#### Sales Reporting
   - Generate sales reports for:
     - Last 3 months.
     - Last 6 months.
     - Last 12 months.

#### Stock Notifications
   - **View Orders:** Review sales orders.
   - **Stock Take:** Audit current stock levels for sales.
   - **Restock Notifications:** Notify the warehouse when stock levels are low.

---

### 5. **Customer Management**
#### Product Range Management
   - **Checkout:** Manage customer checkouts.
   - **Requests:** Handle customer product requests.
   - **Search Products:** Search products available for customers.
   - **Purchase Products:** Process customer purchases.
   - **Favorites:** Track favorite products for customers.

#### Shopping Cart Management
   - **Update Orders:** Modify orders in the shopping cart.
   - **Cancel Orders:** Cancel cart orders.
   - **View Orders:** View order details in the cart.
   - **Save Orders:** Save orders for later processing.
   - **Make Payments:** Process payments for orders.

---

## Technical Details
- **Language:** Java
- **Database:** MySQL
- **Build Tool:** Maven
- **Frameworks/Libraries:**
  - `mysql-connector-java` for database connectivity.
  - Custom layouts and Java Swing for the GUI.

---

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/Cash-Till-Shop.git
   cd Cash-Till-Shop
   ```

2. **Configure the Database**
   - Ensure MySQL is installed and running.
   - Import the provided SQL script to set up the database.

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.mycompany.isp.ISP"
   ```

---

## Contributing
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Submit a pull request.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
