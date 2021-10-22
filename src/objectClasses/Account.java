package objectClasses;

import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

/*
 * TODO:
 * Manipulates booking data, check-in, check-out with date
 * adjusts length of stay
 * authenticates user with conf_ID and email
 */
public class Account {
	
	private String email;
	private int confNum;
	
	public Account(String email) {
		setEmail(email);
		confNum = createConfID();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
    
    public static int createConfID() {
    	int confirmation = 0;
    	Random rnd = new Random();
        confirmation = rnd.nextInt(999999);
        while (checkConfNum(confirmation)) {
        	confirmation = rnd.nextInt();
        }
        return confirmation;
    }
    
    //method returns true if confID already exists in database
    public static boolean checkConfNum(int confID) {
    	String sqlQuery = "SELECT * FROM Booking WHERE conf_ID = " + confID;
    	ResultSet sqlResults;
		try {
			sqlResults = connection().executeQuery(sqlQuery);
	    	//duplicate exists - return true
	    	if(sqlResults.next()) {
				connection().close();
				return true;
			//duplicate does not exist - return false
			} else {
				connection().close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    /*
     * Checks account status and returns true if exists. 
     * If account does not exist the account is created and returns true. 
     * If account can't be created returns false.
     */
    public static boolean checkAccount(String customerFName, String customerLName, String phone, String email) {
    	//check if customer already exists
    	String sqlQuery = "SELECT cust_ID FROM Customer WHERE cust_Email = '" + email + "';";
    	ResultSet sqlResults;
		try {
			sqlResults = connection().executeQuery(sqlQuery);

	    	if(sqlResults.next()) {
				System.out.println("Customer Data Exists");
				return true;
			} else {
				System.out.println("Customer data does not exist");
				sqlQuery = "INSERT INTO Customer (cust_Fname, cust_Lname, cust_Phone, cust_Email)"
						+ " VALUES ('" + customerFName + "', '" + customerLName + "', '" + phone + "', '" + email + "');";
				int result = connection().executeUpdate(sqlQuery);
		    	if(result != 0) {
		    		System.out.println("Customer Created");
		    		return true;
		    	} else {
		    		System.out.println("Could not create Customer");
		    		return false;
		    	}
		    	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
