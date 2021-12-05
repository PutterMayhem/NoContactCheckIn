package objectclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee extends Account {

	boolean isAdmin;

	public Employee(String custFName, String custLName, String phone, String email) {
		super(custFName, custLName, phone, email);
		isAdmin = false;
	}

	public Employee(String custFName, String custLName, String phone, String email, boolean isAdmin) {
		super(custFName, custLName, phone, email);
		this.isAdmin = isAdmin;
	}

	public boolean createEmployee() throws SQLException {
		String sqlQuery = "INSERT INTO Employee (emp_Fname, emp_lname, cust_Phone, cust_Email)" + " VALUES ('"
				+ this.getFName() + "', '" + this.getLName() + "', '" + this.getPhone() + "', '" + this.getEmail()
				+ "');";
		int result = connection().executeUpdate(sqlQuery);

		if (result != 0) {
			System.out.println("Employee Created");
			connection().close();
			return true;
		} else {
			System.out.println("Oops, something went wrong!");
			connection().close();
			return false;
		}
	}
	
	public static boolean createEmployee(String fname, String lname, String username, String password, boolean admin) throws SQLException {
		int i = 0;
		if (admin) {
			i = 1;
		}
		String sqlQuery = "INSERT INTO Employee (emp_Fname, emp_lname, admin, username, password) VALUES ('"
				+ fname + "', '" + lname + "', " + i + ", '" + username + "', '" + password + "');";
		int result = connection().executeUpdate(sqlQuery);

		if (result != 0) {
			System.out.println("Employee Created");
			connection().close();
			return true;
		} else {
			System.out.println("Oops, something went wrong!");
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
}
