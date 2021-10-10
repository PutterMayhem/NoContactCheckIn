package objectClasses;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
	int custID;
	String custFName;
	String custLName;
	String phone;
	String email;
	
	public Customer(String custFName, String custLName, String phone, String email) {
		this.custFName = custFName;
		this.custLName = custLName;
		this.phone = phone;
		this.email = email;
	}
	
	public String getFName() {
		return custFName;
	}
	public void setFName(String fName) {
		this.custFName = fName;
	}
	public String getLName() {
		return custLName;
	}
	public void setLName(String lName) {
		this.custLName = lName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String newPhone) {
		this.phone = newPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getCustID(String email) throws SQLException {
    	String sqlQuery = "SELECT cust_ID FROM Customer WHERE cust_Email = '" + email + "'";
    	ResultSet sqlResults = connection().executeQuery(sqlQuery);
    	if(sqlResults.next()) {
			custID = sqlResults.getInt("cust_ID");
			System.out.println("Customer Data Exists");
			connection().close();
			return custID;
		} else {
			System.out.println("Customer data does not exist");
			connection().close();
			return 0;
		}
    }
	
	
	public boolean createCustomer() throws SQLException {
		String sqlQuery = "INSERT INTO Customer (cust_Fname, cust_Lname, cust_Phone, cust_Email)"
				+ " VALUES ('" + custFName + "', '" + custLName + "', '" + phone + "', '" + email + "');";
		int result = connection().executeUpdate(sqlQuery);
    	
    	if(result != 0) {
    		System.out.println("Customer Created");
    		connection().close();
    		return true;
    	} else {
    		System.out.println("Oops, something went wrong!");
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

}
