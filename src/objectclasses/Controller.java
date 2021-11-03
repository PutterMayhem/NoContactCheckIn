package objectclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

	private Room room;
	private int numGuests;
	private Date checkIn;
	private Date checkOut;

	/*
	 * Returns true if customer entered valid data. False if not. Use if statement
	 * to determine actions taken.
	 */
	public boolean logIn(String email, String confID) throws SQLException {
		String sqlQuery1 = "Select * from Customer where cust_Email ='" + email + "';";
		String sqlQuery2 = "Select * from Booking where conf_ID = '" + confID + "';";

		ResultSet sqlResults1 = connection().executeQuery(sqlQuery1);
		ResultSet sqlResults2 = connection().executeQuery(sqlQuery2);
		if (sqlResults1.next() && sqlResults2.next()) {
			return true;
		}
		return false;
	}

	// Connection to database method
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

	public boolean bookingProcess(Date checkIn, Date checkOut, int guests) {
		ArrayList<Room> available = room.getAllAvailable();

	}

}
