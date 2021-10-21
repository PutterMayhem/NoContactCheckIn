
package objectClasses;


import java.util.Date;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

/**
 * Functionality:
 * Create booking, creates user data if it doesn't exist
 * check confID for duplicates in the
 * check if room is currently booked or not; proper response
 * TODO:
 * booking a room makes it unavailable? checking in makes room unavailable?
 * Authentication of email conf ID
 * 
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

    public void setConfNum(int confNum) {
        this.confNum = confNum;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    //method to check if room is currently booked or not
    //returns true if booked
    public boolean checkRoom(int roomNum) throws SQLException {
    	String sqlQuery = "SELECT * FROM Room WHERE room_num = " + roomNum+";";
    	ResultSet sqlResults = connection().executeQuery(sqlQuery);
    	sqlResults.next();
    	int i = sqlResults.getInt("room_status");
    	if (i == 0) {
    		connection().close();
    		return false;
    	} else {
    		connection().close();
    		return true;
    	}
		
    }
    
    public boolean logIn(String email, String confID) throws SQLException {
    	String sqlQuery = "Select * from booking where cust_Email ='"+email+"' and conf_ID= "+confID+";";
    	ResultSet sqlResults = connection().executeQuery(sqlQuery);
    	if(sqlResults.next()) {
    		
    	}
    	return false;
    }
    
    
    public int getConfNum(String email) throws SQLException {
    	String sqlQuery = "SELECT conf_ID FROM Booking WHERE cust_email = '" + email + "';";
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

    
    /*
     * Checks if customer data exits, creates if not
     * check conf_ID for duplicates in table
     * checks if room is booked or not
     */
    public boolean createBooking() throws SQLException {
    	//Check if account exists. If not the account is created.
    	Account.checkAccount(customerFName, customerLName, phone, email);
    	
    	//ensure unique conf_ID is created
    	int confNum = Account.createConfID();
        
    	
    	//Checks if room is booked
        if(Room.isBooked(roomNumber)) {
        	System.out.println("Sorry, room is already booked. Please choose another room");
        	return false;
        }
        
    	String sqlQuery = "INSERT INTO Booking VALUES (" + confNum + ", '" + email + "', " + roomNumber + ", " + lengthStay + ", NULL, NULL)";
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
    	Booking test = new Booking("fname", "lname", "email", "6127779999", 2, 2);
    	try {
    		test.createBooking();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
