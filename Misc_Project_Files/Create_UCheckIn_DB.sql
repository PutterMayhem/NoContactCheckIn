-- UCHeckIn Database Creation Script
-- Super Awesome Dev Squad
-- Max Christiansen
-- Version: 1.0

-- Create the database and use it --
CREATE DATABASE uCheckIn;
USE uCheckIn;

-- Create the tables -- 
CREATE TABLE Customer(
	cust_ID int primary key,
    cust_Fname varchar(20),
    cust_Lname varchar(20),
    cust_Phone int,
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
    FOREIGN KEY(cust_email) REFERENCES Customer(cust_email) ON DELETE CASCADE,
    FOREIGN KEY(room_num) REFERENCES Room(room_num) ON DELETE CASCADE
);

CREATE Table Request(
    req_ID int primary key,
    req_Type varchar(15),
    req_DateTime date,
    req_FullfillDateTime date,
    fulfilled boolean,
    conf_ID int,
    FOREIGN KEY(conf_ID) REFERENCES Booking(conf_ID) ON DELETE CASCADE
);

create table Items(
    item_ID int primary key,
    item_Name varchar(20),
    item_price float
);

create table Employee(
    emp_ID int primary key,
    emp_Fname varchar(20),
    emp_Lname varchar(20),
    admin boolean
);