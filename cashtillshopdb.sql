-- Database creation
CREATE DATABASE IF NOT EXISTS bbphongolashopdb;
USE bbphongolashopdb;

-- Table: product_warehousetbl
CREATE TABLE product_warehousetbl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    units INT NOT NULL,
    expiry_date DATE NOT NULL,
    delivery_date DATE NOT NULL,
    quantity INT NOT NULL,
    isdamaged BOOLEAN DEFAULT FALSE
);

-- Table: StoreManager_Empolyeetbl
CREATE TABLE StoreManager_Empolyeetbl (
    idStoreManager_Empolyee INT AUTO_INCREMENT PRIMARY KEY,
    initialsStoreManager_Empolyee VARCHAR(50),
    surnameStoreManager_Empolyee VARCHAR(255),
    departmentStoreManager_Empolyee VARCHAR(100),
    workTimeStoreManager_Empolyee VARCHAR(50),
    cellNumberStoreManager_Empolyee BIGINT,
    emailStoreManager_Empolyee VARCHAR(255),
    usernameStoreManager_Empolyee VARCHAR(100) UNIQUE,
    passWordStoreManager_Empolyee VARCHAR(255)
);

-- Table: customerinfo_storetbl
CREATE TABLE customerinfo_storetbl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    initials VARCHAR(50),
    surname VARCHAR(255),
    cellphone BIGINT,
    email VARCHAR(255),
    address TEXT,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(255)
);

-- Table: order_cutomertbl
CREATE TABLE order_cutomertbl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    units INT,
    amount DOUBLE,
    quantity INT,
    expiry_date DATE
);

-- Table: carttbl
CREATE TABLE carttbl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    item VARCHAR(255),
    units VARCHAR(50),
    amount DOUBLE,
    expiry_date DATE,
    quantity INT,
    FOREIGN KEY (product_id) REFERENCES product_warehousetbl(id)
);
