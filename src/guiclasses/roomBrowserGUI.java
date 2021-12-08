package guiclasses;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class roomBrowserGUI implements Initializable {
	
	@FXML
	private TableView<RoomTable> table_rooms;
	@FXML
	private TableColumn<RoomTable, Integer> col_roomnum;
	@FXML
	private TableColumn<RoomTable, String> col_roomtype;
	@FXML
	private TableColumn<RoomTable, String> col_price;
	@FXML
	private TableColumn<RoomTable, CheckBox> col_select;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_select;
	@FXML 
	private VBox vbox;
	@FXML 
	private Button btn_refresh;
	
	Controller control = Controller.getInstance();
	private static int count = 0;
	private static int index = 0;
	
	ObservableList<RoomTable> roomlist = FXCollections.observableArrayList();

    
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
    
    public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
		window.show();
		window.setFullScreen(true);
	}
    public void changeToSplash(Stage window) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		window.setScene(splashView);
		window.show();
		window.setFullScreen(true);
	}
    
    public Scene getScene() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("roomBrowser.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static boolean checkSelected(ObservableList<RoomTable> roomlist2) {
    	roomlist2.forEach((room) -> {
    		if (room.getSelect().isSelected()) {
    			count += 1;
    		}
    	});
    	if (count == 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("test");
		// TODO Auto-generated method stub
		btn_back.setOnAction(actionEvent -> {
			try {
				changeToSplash(actionEvent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				roomBrowserGUI rb  = new roomBrowserGUI();
				Scene rbs = rb.getScene();
				primary.setScene(rbs);
				primary.show();
				primary.setFullScreen(true);
			}
		});
		try {
			String query = "SELECT room_num, r.roomType_ID, room_status, rate FROM Room r INNER JOIN RoomType rt\n" + 
					"ON r.roomType_ID = rt.roomType_ID WHERE room_status = 0;";
			ResultSet rs = connection().executeQuery(query);
			while(rs.next()) {
				String price = "$" + String.format("%.2f",rs.getFloat("rate"));
				int roomNum = rs.getInt("room_num");
				String roomType = rs.getString("roomType_ID");
				roomlist.add(new RoomTable(roomNum, roomType, price, new CheckBox()));
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		btn_select.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				boolean boo = false;
				roomlist.forEach((room) -> {
		    		if (room.getSelect().isSelected()) {
		    			count += 1;
		    			index = roomlist.indexOf(room);
		    		}
		    	});
		    	if (count == 1) {
		    		boo = true;
		    	} else {
		    		boo = false;
		    	}
				// TODO Auto-generated method stub
				if (!boo) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please make sure 1 room is seleced!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					count = 0;
					return;
				}
				int roomnum = roomlist.get(index).getRoomNum();
				System.out.println(roomnum);
				control.setRoom(Room.getRoomFromDB(roomnum));
				BookingGUI b  = new BookingGUI();
				Scene bs = b.getScene();
				b.setInformation(control);
				primary.setScene(bs);
				primary.show();
				primary.setFullScreen(true);
			}
			
		});
		
		col_roomnum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
		col_roomtype.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_select.setCellValueFactory(new PropertyValueFactory<>("select"));
		table_rooms.setItems(roomlist);
		
	}

}
