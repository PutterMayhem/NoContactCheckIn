package objectClasses;

import java.util.Date;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
