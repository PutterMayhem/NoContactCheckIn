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
import javafx.stage.Stage;
import objectclasses.*;

public class CheckRequestsGUI extends Application implements Initializable {
	@FXML
	private TableView<CheckTable> table_checkreq;
	@FXML
	private TableColumn<CheckTable, Integer> col_confnum;
	@FXML
	private TableColumn<CheckTable, Integer> col_reqID;
	@FXML
	private TableColumn<CheckTable, String> col_reqname;
	@FXML
	private TableColumn<CheckTable, String> col_price;
	@FXML
	private TableColumn<CheckTable, Date> col_time;
	@FXML
	private TableColumn<CheckTable, String> col_status;
	@FXML
	private TableColumn<CheckTable, CheckBox> col_select;
	@FXML
	private Button btn_complete;
	@FXML
	private Button btn_back;
	
	
	
	ObservableList<CheckTable> checklist = FXCollections.observableArrayList();
	
	
	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckRequests.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("Admin Page");
			primary.setScene(scene);
			primary.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckRequests.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		btn_complete.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				checklist.forEach((check) -> {
					if (check.getSelect().isSelected()) {
						Request.setRequestItemComplete(check.getReqID());
					}
				});
			}
			
		});
		
		btn_back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				LoggedInAdminGUI loggedin = new LoggedInAdminGUI();
				Scene loggedInScene = loggedin.getScene();
				primary.setScene(loggedInScene);
				primary.show();
			}
			
		});
		// TODO Auto-generated method stub
		try {
			String query = "SELECT r.conf_ID, ri.reqitem_ID, i.item_Name, i.item_price, r.req_DateTime, ri.fulfilled FROM Request r \n" + 
					"INNER JOIN RequestItems ri ON r.req_ID = ri.req_ID\n" + 
					"INNER JOIN Items i ON ri.item_ID = i.item_ID "
					+ "ORDER BY r.req_DateTime";
			ResultSet rs = connection().executeQuery(query);
			while(rs.next()) {
				String price = null;
				if (rs.getFloat("item_price") != 0) {
					price = "$" + String.valueOf(rs.getFloat("item_price"));
				}
				String temp = null;
				if (rs.getInt("fulfilled") == 0) {
					temp = "Pending";
					checklist.add(new CheckTable(rs.getInt("conf_ID"), rs.getInt("reqitem_ID"), 
							rs.getString("item_Name"), price, 
							rs.getDate("req_DateTime"), temp, new CheckBox()));
				} else if (rs.getInt("fulfilled") == 1) {
					temp = "Completed";
					checklist.add(new CheckTable(rs.getInt("conf_ID"), rs.getInt("reqitem_ID"), 
							rs.getString("item_Name"), price, 
							rs.getDate("req_DateTime"), temp, null));
				}
				
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		col_confnum.setCellValueFactory(new PropertyValueFactory<>("confNum"));
		col_reqID.setCellValueFactory(new PropertyValueFactory<>("reqID"));
		col_reqname.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		col_select.setCellValueFactory(new PropertyValueFactory<>("select"));
		
		table_checkreq.setItems(checklist);
	}
	
	
	

}
