-- UCHeckIn Database Creation Script
-- Super Awesome Dev Squad
-- Max Christiansen
-- Version: 1.0

-- Create the database and use it --
CREATE DATABASE uCheckIn;
USE uCheckIn;

-- Create the tables -- 
CREATE TABLE Customer(
	cust_ID int primary key auto_increment,
    cust_Fname varchar(20),
    cust_Lname varchar(20),
    cust_Phone varchar(11),
    cust_Email varchar(100)
);

ALTER TABLE Customer ADD INDEX (cust_Email);

CREATE TABLE RoomType(
	roomType_ID varchar(4) primary key,
    king int,
    queen int,
    full int,
    pull_Out int,
    suite boolean,
    rate float
);

CREATE TABLE RequestType(
	reqType_ID varchar(20) primary key,
    reqType_Description varchar(250)
);

CREATE TABLE Room(
	room_num int primary key,
    roomType_ID varchar(4),
    room_status boolean,
    room_active boolean,
    floor int,
    FOREIGN KEY(roomType_ID) REFERENCES RoomType(roomType_ID) ON DELETE CASCADE
);

CREATE TABLE Booking(
	conf_ID int primary key,
    cust_email varchar(100),
    room_num int,
    stay_length int,
    check_in date,
    check_out date,
	cctoken int,
    FOREIGN KEY(cust_email) REFERENCES Customer(cust_email) ON DELETE CASCADE,
    FOREIGN KEY(room_num) REFERENCES Room(room_num) ON DELETE CASCADE
);

create table Items(
    item_ID int auto_increment,
    item_Name varchar(20),
    item_price float,
    PRIMARY KEY(item_ID)
);

create table Employee(
    emp_ID int auto_increment,
    emp_Fname varchar(20),
    emp_Lname varchar(20),
    admin boolean,
    PRIMARY KEY(emp_ID)
);

CREATE Table Request(
    req_ID int primary key,
    req_Type varchar(15),
    req_DateTime date,
    req_FullfillDateTime date,
    fulfilled boolean,
    conf_ID int,
    item_ID int,
	FOREIGN KEY(conf_ID) REFERENCES Booking(conf_ID),
    FOREIGN KEY(item_ID) REFERENCES Items(item_ID)
);



INSERT INTO RoomType (roomType_ID, king, queen, full, pull_out, suite, rate)
VALUES ('SK', 1, 0, 0, 0, 0, 100),
	   ('DQ', 0, 2, 0, 0, 0, 110),
       ('SKS', 1, 0, 0, 1, 1, 175),
       ('DQS', 0, 2, 0, 1, 1, 200);
       
INSERT INTO Room (room_num, roomType_ID, room_status, room_active, floor)
VALUES (100, 'SK', 1, 1, 1),
	   (101, 'DQ', 1, 1, 1),
       (200, 'DQ', 0, 1, 2),
       (220, 'SKS', 0, 0, 2),
       (310, 'DQS', 1, 1, 3);
       
       
INSERT INTO Employee (emp_Fname, emp_Lname, admin)
VALUES ("Chuck", "Gustner", 1),
	   ("Mahad", "Hussein", 0);
       
INSERT INTO Items(item_Name, item_Price)
VALUES ("Chicken Kiev", 13.99),
		("Caesar Salad", 8.99),
        ("16oz New York Strip Steak", 16.99),
        ("Pork Ribs", 17.99),
        ("Talapia", 6.99),
        ("Shrimp Scampi", 9.99),
        ("Pepperoni Pizza", 12.99),
        ("Chocolate Cake", 6.99),
        ("New York Cheese Cake", 9.99),
        ("French Silk Pie", 8.99);