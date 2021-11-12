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
		String sqlQuery1 = "Select * from Customer where cust_email ='" + email + "';";
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

	public boolean bookingProcess(Date checkIn, Date checkOut, String type) {
		ArrayList<Room> available = room.getAllAvailableType(type);

		// TODO Add code to retrieve data from GUI. Then delete test data
		Room test = Room.getRoomFromDB(101);
		String ccNum = "4785414525354747";
		String tempDate = "1023";
		int csc = 345;

		VirtualCCProcessor ccp = new VirtualCCProcessor(ccNum, tempDate, csc);
		int token = ccp.hashCode();
		// TODO insert token into booking table
		return false;
	}

	public boolean checkRequests() {
		// TODO add code to check requests
	}

	public boolean checkIn() {
		// TODO add code to check in
	}

	public boolean checkOut() {
		// TODO add code to check out
	}

	public boolean createRequest() {
		// TODO add code to add a special request
	}

	public boolean createCustomer(String fName, String lName, String email, String phone) {
		Account newAccount = new Account(fName, lName, phone, email);
		try {
			boolean isCreated = newAccount.createCustomer();
			return isCreated;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean createEmployee(String fName, String lName, String email, String phone, boolean admin) {
		Employee newEmp = new Employee(fName, lName, phone, email, admin);
		try {
			boolean isCreated = newEmp.createEmployee();
			return isCreated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
