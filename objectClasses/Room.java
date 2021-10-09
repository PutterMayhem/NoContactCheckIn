package objectClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Room {
    //TODO: Validate all needed properties, setters, and getters
    int roomNumber;
    int roomType;
    boolean booked;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getRoomType() {
    	return roomType;
    }
    public void setRoomType(int roomType) {
    	this.roomType = roomType;
    }
    //returns true if room is booked, false if room is not booked or does not exist
    public boolean isBooked(int roomNumber) {
    	String sqlQuery = "SELECT room_status FROM Room WHERE room_num = " + roomNumber;
    	int roomStatus = 0;
    	try {
			ResultSet result = connection().executeQuery(sqlQuery);
			if (result.next()) {
				roomStatus = result.getInt("room_status");
			} else {
				System.out.println("Sorry, room does not exist");
				return booked;
			}
			connection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	booked = (roomStatus == 1);
        return booked;
    }
    
    //prints a list of booked rooms
    public void getBooked() {
    	try {
    		String sqlQuery = "SELECT * FROM Room WHERE room_status = 1;";
    		ResultSet result = connection().executeQuery(sqlQuery);
    		while (result.next()) {
    			int roomNum = result.getInt("room_num");
    			String type = result.getString("roomType_ID");
    			int floor = result.getInt("floor");
    			System.out.println("Room Number: " + roomNum + " Room Type: " + type +
    					 " Room Floor: " + floor);
    		}
    		connection().close();
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
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
    
    public static void main(String[] args) {
    	Room test = new Room();
    	test.getBooked();
    }
}
