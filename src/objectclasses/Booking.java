
package objectclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Functionality: Create booking, creates user data if it doesn't exist check
 * confID for duplicates in the check if room is currently booked or not; proper
 * response TODO: checking in makes room unavailable? Authentication of email
 * conf ID
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
		// Records booking into database
		String sqlQuery = "INSERT INTO Booking VALUES (" + confNum + ", '" + customer.getEmail() + "', "
				+ room.roomNumber + ", " + lengthStay + ", NULL, NULL)";
		int result = connection().executeUpdate(sqlQuery);
		// Updates room status in room database
		String sqlUpdate = "UPDATE room SET room_status = 1;";
		connection().execute(sqlUpdate);
		if (result != 0) {
			room.setBooked(true);
			System.out.println("Booking Created");
			System.out.println("Your Confirmation Number is: " + confNum);
			System.out.println("To login to our kiosks use your e-mail address and your confirmation number");
			connection().close();
			return true;
		} else {
			System.out.println("Could not create booking");
			connection().close();
			return false;
		}
	}

	public static void checkOut(int roomNumber) throws SQLException {
		String sqlQuery = "Update room SET room_status = 0;";
		connection().execute(sqlQuery);
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

		/*
		 * Removes entries from database so program can be run again. Comment out to see
		 * what happens when a room is double booked. Will show exceptions for duplicate
		 * entries but still runs.
		 */
		String sqldelete = "delete from roomtype;";
		try {
			connection().execute(sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqldelete = "delete from room;";
		try {
			connection().execute(sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqldelete = "delete from booking;";
		try {
			connection().execute(sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner input = new Scanner(System.in);
		String sqlQuery = "replace into roomtype(roomtype_ID, king, queen, full, pull_out, suite, rate) Values(\"1\", 1, 0, 0, 0, 0, 100)";
		try {
			connection().execute(sqlQuery);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Creates a room to book
		Room testRoom = new Room(2, "1", false);
		// Adds room to database
		testRoom.createRoom(2, "1");
		// Creates account for booking
		System.out.println("Enter first name:");
		String fname = input.next();
		System.out.println("Enter Last Name:");
		String lname = input.next();
		System.out.println("Enter phone number: (Example: 7635555555)");
		String phone = input.next();
		System.out.println("Enter e-mail:");
		String email = input.next();
		System.out.println("How long are you going to stay?");
		int days = input.nextInt();
		Account testAct = new Account(fname, lname, phone, email);
		// Creates booking
		Booking test = new Booking(testAct, testRoom, days);
		try {
			test.createBooking();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}