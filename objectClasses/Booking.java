package objectClasses;


import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.time.LocalDateTime;    

/**
 * Objectives:
 * creates booking; create random confID, take customer details, length of stay, and room number
 * adjusts length of stay; takes confID, or customer email
 * check in, check out; takes confID or email
 */

public class Booking {
    //TODO: Validate all needed properties, setters, and getters
    String customerFName;
    String customerLName;
    String email;
    int confNum;
    int roomNumber;
    String phone;

    Date arrival;
    Date departure;
    int lengthStay; //in days for now
    
    public Booking(String custFName, String custLName, String email, String phone, int roomNum, int lengthStay) {
    	this.customerFName = custFName;
    	this.customerLName = custLName;
    	this.email = email;
    	this.roomNumber = roomNum;
    	this.lengthStay = lengthStay;
    	this.phone = phone;
    	
    }
   
    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }

    public int getConfNum(String email) throws SQLException {
    	String sqlQuery = "SELECT conf_ID FROM Booking WHERE cust_email = '" + email + "'";
    	ResultSet sqlResults = connection().executeQuery(sqlQuery);
    	if(sqlResults.next()) {
			confNum = sqlResults.getInt("conf_ID");
			System.out.println("Your Confirmation Number is: " + confNum);
			connection().close();
			return confNum;
		} else {
			System.out.println("Sorry, no confirmation number with that email was found");
			connection().close();
			return 0;
		}
    	
    }

    public void setConfNum(int confNum) {
        this.confNum = confNum;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    
    public boolean createBooking() throws SQLException {
    	//check if customer already exists
    	String sqlQuery = "SELECT cust_ID FROM Customer WHERE cust_Email = '" + email + "'";
    	ResultSet sqlResults = connection().executeQuery(sqlQuery);
    	if(sqlResults.next()) {
			System.out.println("Customer Data Exists");
		} else {
			System.out.println("Customer data does not exist");
			sqlQuery = "INSERT INTO Customer (cust_Fname, cust_Lname, cust_Phone, cust_Email)"
					+ " VALUES ('" + customerFName + "', '" + customerLName + "', '" + phone + "', '" + email + "');";
			int result = connection().executeUpdate(sqlQuery);
	    	if(result != 0) {
	    		System.out.println("Customer Created");
	    	} else {
	    		System.out.println("Could not create Customer");
	    	}
		}
    	Random rnd = new Random();
        this.confNum = rnd.nextInt(999999);
    	sqlQuery = "INSERT INTO Booking VALUES (" + confNum + ", '" + email + "', " + roomNumber + ", " + lengthStay + ", NULL, NULL)";
    	int result = connection().executeUpdate(sqlQuery);
    	
    	if(result != 0) {
    		System.out.println("Booking Created");
    		System.out.println("Your Confirmation Number is: " + confNum);
    		connection().close();
    		return true;
    	} else {
    		System.out.println("Could not create booking");
    		connection().close();
    		return false;
    	}
    }
    
    //Connection to database method
    private static Statement connection() {
		Statement statement = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uCheckIn", "root", "");
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
    
    
    
    
    public static void main(String[] args) {
    	Booking test = new Booking("fname", "lname", "email", "6127779999", 1, 2);
    	try {
    		test.createBooking();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
