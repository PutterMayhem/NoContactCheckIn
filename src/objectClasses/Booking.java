
package objectClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

/**
 * Functionality: Create booking, creates user data if it doesn't exist check
 * confID for duplicates in the check if room is currently booked or not; proper
 * response TODO: booking a room makes it unavailable? checking in makes room
 * unavailable? Authentication of email conf ID
 *
 */

public class Booking {
	// TODO: Validate all needed properties, setters, and getters

	Account customer;
	int confNum;
	Room room;
	Date arrival;
	Date departure;
	int lengthStay; // in days for now

	public Booking(Account customer, Room room, int lengthStay) {
		this.customer = customer;
		this.room = room;
		this.lengthStay = lengthStay;

	}

	public Account getCustomer() {
		return customer;
	}

	public void setAccount(Account customer) {
		this.customer = customer;
	}

	public void setConfNum(int confNum) {
		this.confNum = confNum;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	// method to check if room is currently booked or not
	// returns true if booked
	public boolean checkRoom(int roomNum) throws SQLException {
		String sqlQuery = "SELECT * FROM Room WHERE room_num = " + roomNum + ";";
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

	public int getConfNum(String email) throws SQLException {
		String sqlQuery = "SELECT conf_ID FROM Booking WHERE cust_email = '" + email + "';";
		ResultSet sqlResults = connection().executeQuery(sqlQuery);
		if (sqlResults.next()) {
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

	// method returns true if confID already exists in database
	public boolean checkConfNum(int confID) {
		String sqlQuery = "SELECT * FROM Booking WHERE conf_ID = " + confID;
		ResultSet sqlResults;
		try {
			sqlResults = connection().executeQuery(sqlQuery);
			if (sqlResults.next()) {
				// duplicate exists - return true
				connection().close();
				return true;
				// duplicate does not exist - return false
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

	public int createConfID() {
		int confirmation = 0;
		Random rnd = new Random();
		confirmation = rnd.nextInt(999999);
		while (checkConfNum(confirmation)) {
			confirmation = rnd.nextInt();
		}
		System.out.println(confirmation);
		return confirmation;
	}

	/*
	 * Checks if customer data exits, creates if not check conf_ID for duplicates in
	 * table checks if room is booked or not
	 */
	public boolean createBooking() throws SQLException {
		// Check if account exists. If not the account is created.
		// Account.checkAccount(customerFName, customerLName, phone, email);

		// ensure unique conf_ID is created
		confNum = createConfID();

		// Checks if room is booked
		if (Room.isBooked(room)) {
			System.out.println("Sorry, room is already booked. Please choose another room");
			return false;
		}

		String sqlQuery = "INSERT INTO Booking VALUES (" + confNum + ", '" + customer.getEmail() + "', "
				+ room.roomNumber + ", " + lengthStay + ", NULL, NULL)";
		int result = connection().executeUpdate(sqlQuery);

		if (result != 0) {
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

	public static void main(String[] args) {

		Room testRoom = new Room(2, "1", false);
		testRoom.createRoom(2, "1");
		Account testAct = new Account("fname", "lname", "6127779999", "cegustner@gmail.com");
		Booking test = new Booking(testAct, testRoom, 2);
		try {
			test.createBooking();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
