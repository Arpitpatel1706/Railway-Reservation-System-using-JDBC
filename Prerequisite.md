# Software Prerequisite:

1. MySQL
2. IDE(e.g., IntelliJ IDEA,Visual Studio Code)

## Databases Setup:

Step 1: Create Database name `Railway`
```bash
-- Create a new database called 'Railway'
CREATE DATABASE railway;

-- Switch to the newly created database
USE Railway;
```
Step 2: Create Tables: `user`, `train` and `chart`
```bash

-- Create the 'user' table
CREATE TABLE user (
    uname VARCHAR(255),
    pass VARCHAR(255),
    age INT,
    g VARCHAR(255),
    timestamp TIMESTAMP,
    sno INT AUTO_INCREMENT,
    PRIMARY KEY (sno)
);

-- Create the 'train' table
CREATE TABLE train (
    tnum INT,
    tname VARCHAR(255),
    seats INT,
    bp VARCHAR(255),
    dp VARCHAR(255),
    fAC INT,
    sAC INT,
    tAC INT,
    sc INT,
    doj DATE,
    dtime VARCHAR(255),
    atime VARCHAR(255),
    sno INT AUTO_INCREMENT,
    PRIMARY KEY (sno)
);

-- Create the 'chart' table
CREATE TABLE chart (
    pnr INT,
    name VARCHAR(255),
    age INT,
    gender VARCHAR(255),
    seatno INT,
    coach VARCHAR(255),
    status VARCHAR(255),
    timestamp TIMESTAMP,
    dot DATE,
    sno INT AUTO_INCREMENT,
    tnum INT,
    PRIMARY KEY (sno),
    FOREIGN KEY (tnum) REFERENCES train(sno)
);

```

## JDBC configuration
### Step 1: Include JDBC Driver for MySQL
```bash
// register jdbc Driver 

String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";

Class.forName(mysqlJDBCDriver);
```
### Step 2: Create Connection Class using MySQL username and password
```bash
// Create Connection

String url = "jdbc:mysql://localhost:3306/databaseName";

String user = "root";

String pass = "123";

con = DriverManager.getConnection(url, user, pass);
```
