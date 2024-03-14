CREATE TABLE Brand (
  BrandID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  BrandName VARCHAR(255) NOT NULL,
  DelStatus BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO Brand (BrandName, DelStatus) VALUES ('Dell', True);
INSERT INTO Brand (BrandName, DelStatus) VALUES ('Apple', True);
INSERT INTO Brand (BrandName, DelStatus) VALUES ('HP', True);
-- Create the table `Category`
CREATE TABLE Category (
  CategoryID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  CategoryName VARCHAR(255) NOT NULL,
  DelStatus BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO Category (CategoryName, DelStatus) VALUES ('Laptops', TRUE);
INSERT INTO Category (CategoryName, DelStatus) VALUES ('Desktops', TRUE);
INSERT INTO Category (CategoryName, DelStatus) VALUES ('Accessories', TRUE);

CREATE TABLE Product (
  ProductID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ProductName VARCHAR(255) NOT NULL,
  ProductDetails VARCHAR(255) DEFAULT NULL,
  Stock INT(50) NOT NULL,
  Warranty INTEGER DEFAULT NULL,
  Price DECIMAL(10,2) NOT NULL,
  BrandID INT(50) NOT NULL,
  CategoryID INT(50) NOT NULL,
  INDEX product_name_index (ProductName),
  FOREIGN KEY (BrandID) REFERENCES brand(BrandID),
  FOREIGN KEY (CategoryID) REFERENCES category(CategoryID)
);
-- Assuming BrandID and CategoryID already have data (refer to previous examples)
INSERT INTO Product (ProductName, ProductDetails, Stock, Warranty, Price, BrandID, CategoryID)
VALUES ('Dell XPS 13 Laptop', 'Latest 12th Gen Intel Core i7 processor, 16GB RAM, 512GB SSD', 10, 12, 1499.99, 1, 1),  -- BrandID 1, CategoryID 1 (Computer)
       ('Apple MacBook Air', 'M2 chip, 8GB RAM, 256GB SSD', 15, 24, 1249.00, 2, 1),  -- BrandID 2, CategoryID 1 (Computer)
       ('Samsung Galaxy S23 Ultra', '1TB storage, 120Hz display, Quad camera system', 20, 12, 1188.88, 3, 2); -- BrandID 3, CategoryID 2 (Other - assuming Brand 3 and Category 2 exist)

CREATE TABLE Discount (
  DiscountID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  Code VARCHAR(10) NOT NULL,
  Type VARCHAR(50) NOT NULL,
  StartDate DATE NOT NULL,
  EndDate DATE,
  Percent INTEGER(2)
);

CREATE TABLE User (
    UserID INT(50) PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255),
    Password VARCHAR(255),
    Email VARCHAR(45),
    FullName VARCHAR(255),
    Phone VARCHAR(45),
    Address VARCHAR(125),
    Role BOOLEAN DEFAULT false,
    Newsletter SMALLINT,
    Avatar VARBINARY(25)
);
-- This is for illustration purposes ONLY. DO NOT use this approach in practice.
INSERT INTO User (UserID, Username, Password, Email, FullName)
VALUES ('1', 'user1', 'password123', 'user1@example.com', 'John Doe'),
       ('2','user2', 'password456', 'user2@example.com', 'Jane Smith');
CREATE TABLE Orders (
  OrderID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  UserID INTEGER(50) NOT NULL,
  PaymentStatus SMALLINT NOT NULL,
  Created_Date DATE NOT NULL,
  PaymentID INTEGER(50),
  FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create the table `OrderDetail`
CREATE TABLE OrderDetail (
  ProductID INTEGER(50),
  OrderID INTEGER(50),
  Quantity INTEGER(10) NOT NULL,
  DiscountID INTEGER(50),
  SubTotal DECIMAL(10,2) NOT NULL,
  Total DECIMAL(10,2) NOT NULL,
   PRIMARY KEY (ProductID, OrderID),
  FOREIGN KEY (ProductID) REFERENCES ProductInfo(ProductID),
  FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
  FOREIGN KEY (DiscountID) REFERENCES Discount(DiscountID)
);

-- Create the table `ProductInfo`
CREATE TABLE ProductInfo (
  ProductInfoID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Content VARCHAR(255) NOT NULL,  
  ProductID INTEGER(50),
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create the table `ProductImage`
CREATE TABLE ProductImage (
  ProductimageID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  ProductID INTEGER(50) NOT NULL,
  Image VARCHAR(255) NOT NULL,
  Main BOOLEAN DEFAULT false,
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create the table `Review`
CREATE TABLE Review (
  ReviewID INTEGER(50) PRIMARY KEY AUTO_INCREMENT,
  UserID INTEGER(50) NOT NULL,
  ProductID INTEGER(50) NOT NULL,
  ReviewContent VARCHAR(255) NOT NULL,
  Reply VARCHAR(255),
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

