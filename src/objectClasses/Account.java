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
	
	String email;
	int confNum;
	
	
	
	
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
    
    public static int createConfID() throws SQLException {
    	int confirmation = 0;
    	Random rnd = new Random();
        confirmation = rnd.nextInt(999999);
        while (checkConfNum(confirmation)) {
        	confirmation = rnd.nextInt();
        }
        return confirmation;
    }
    
    //method returns true if confID already exists in database
    public static boolean checkConfNum(int confID) throws SQLException {
    	String sqlQuery = "SELECT * FROM Booking WHERE conf_ID = " + confID;
    	ResultSet sqlResults = connection().executeQuery(sqlQuery);
    	//duplicate exists - return true
    	if(sqlResults.next()) {
			connection().close();
			return true;
		//duplicate does not exist - return false
		} else {
			connection().close();
			return false;
		}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
