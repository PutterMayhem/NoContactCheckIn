package objectclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.Alert;

public class Controller {

	private Booking booking;
	private Account account;
	private Room room;
	private Employee employee;
	private Request request;
	private VirtualCCProcessor vcc;

	private float amountOwed;
	private static Controller controller = new Controller();

	private Date arrival;
	private Date depart;

	private Controller() {
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDepart() {
		return depart;
	}

	public void setDepart(Date depart) {
		this.depart = depart;
	}

	public VirtualCCProcessor getVcc() {
		return vcc;
	}

	public void setVcc(VirtualCCProcessor vcc) {
		this.vcc = vcc;
	}

	public float getAmountOwed() {
		return amountOwed;
	}

	public void setAmountOwed(float amountOwed) {
		this.amountOwed = amountOwed;
	}

	public Controller getController() {
		return controller;
	}

	/*
	 * Returns true if customer entered valid data. False if not. Use if statement
	 * to determine actions taken.
	 */
	public boolean custLogIn(String email, String confID) {
		String sqlQuery1 = "Select * from Booking where cust_email ='" + email + "' and conf_ID = '" + confID + "';";
		ResultSet sqlResults1;
		try {
			sqlResults1 = connection().executeQuery(sqlQuery1);

			if (sqlResults1.next()) {

				int roomNum = sqlResults1.getInt("room_num");
				Date checkIn = sqlResults1.getDate("check_in");
				Date checkOut = sqlResults1.getDate("check_out");
				Room room = Room.getRoomFromDB(roomNum);
				booking = new Booking(account, room, checkIn, checkOut);
				setBooking(booking);

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			createAlert("No query ran");
			e.printStackTrace();
		}

		return false;
	}

	public boolean empLogin(String username, String password) {

		String query = "select * from employee where username = '" + username + "' and password = '" + password + "';";

		try {
			ResultSet result = connection().executeQuery(query);
			if (result.next()) {
				int empID = result.getInt("emp_ID");
				String fName = result.getString("emp_Fname");
				String lName = result.getString("emp_Lname");
				int admin = result.getInt("admin");
				boolean admin2 = false;
				if (admin == 1) {
					admin2 = true;
				}
				employee = new Employee(empID, fName, lName, "", "", admin2);
				setEmployee(employee);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public ArrayList<Room> getAllAvailableType(String type) {
		return Room.getAllAvailableType(type);
	}

	public ArrayList<Room> getAllAvailable() {
		return Room.getAllAvailable();
	}

	public boolean bookingProcess(int roomNum, Date checkIn, Date checkOut, String type, String ccNum, String expDate) {
		ArrayList<Room> available = Room.getAllAvailableType(type);
		// TODO Add code to retrieve data from GUI. Then delete test data
		Room test = Room.getRoomFromDB(roomNum);

		int csc = 456;
		VirtualCCProcessor ccp = new VirtualCCProcessor(ccNum, expDate, csc);
		int token = ccp.hashCode();
		// TODO insert token into booking table
		return false;
	}

	/*
	 * Returns a list of open requests.
	 */
	public ArrayList<Request> checkRequests() {
		String query = "select * from requests where fullfilled = 0";
		try {
			ResultSet result = connection().executeQuery(query);
			ArrayList<Request> request = new ArrayList<>();
			while (result.next()) {
				Request temp = new Request();
				temp.setConf_id(result.getInt("req_ID"));
				temp.setFulfilled(true);
				temp.setItem_id(result.getInt("item_ID"));
				temp.setRequestDate(result.getDate("req_FulfillDate"));
				temp.setReq_id(result.getInt("req_ID"));
				request.add(temp);
			}
			return request;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkIn() {
		return false;
		// TODO add code to check in
	}

	public boolean checkOut() {

		getBooking().checkOut();
		controller = null;
		return true;
	}

	public void createRequest(String type, Date reqDate, Date fulfillDate, int conf_id, int item_id, int quant) {
		String query = "insert into request(req_Type, req_DateTime, req_FulfillDate, conf_ID, item_ID values('" + type
				+ "', " + reqDate + "', " + fulfillDate + "', " + conf_id + "'," + item_id + "';";
		try {
			connection().execute(query);
			String itmeQuery = "select * from items where item_ID = " + item_id + ";";
			ResultSet result = connection().executeQuery(itmeQuery);
			float price = result.getFloat("item_price");
			price = price * quant;
			amountOwed += price;
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public boolean createCustomer(String fName, String lName, String email, String phone) {
		Account newAccount = new Account(fName, lName, phone, email);
		try {
			boolean isCreated = newAccount.createCustomer();
			return isCreated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean createEmployee(int empID, String fName, String lName, String email, String phone, boolean admin) {
		Employee newEmp = new Employee(empID, fName, lName, phone, email, admin);
		try {
			boolean isCreated = newEmp.createEmployee();
			return isCreated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void createAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Testing");
		alert.setContentText(msg);
		alert.showAndWait();
	}

}
