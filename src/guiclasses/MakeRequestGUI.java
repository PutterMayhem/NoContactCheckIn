package guiclasses;

import java.io.IOException;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.Controller;
import objectclasses.Request;


public class MakeRequestGUI extends Application implements Initializable{
	
	@FXML
	private TableView<ServiceTable> table_room;
	@FXML
	private TableView<FoodTable> table_food;
	@FXML
	private Label label_welcome;
	@FXML
	private Button btn_cancel;
	@FXML
	private Button btn_submit;
	@FXML
	private TableColumn<ServiceTable, String> col_namefree;
	@FXML
	private TableColumn<FoodTable, String> col_namefood;
	@FXML
	private TableColumn<FoodTable, String> col_price;
	@FXML
	private TableColumn<ServiceTable, CheckBox> col_select;
	@FXML
	private TableColumn<FoodTable, CheckBox> col_selectfood;
	private static Controller control;
	
	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MakeRequest.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("Make Request");
			primary.setScene(scene);
			primary.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MakeRequest.fxml"));
		loader.setController(this);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setInformation(Controller control) {
		MakeRequestGUI.control = control;
		label_welcome.setText("Make a Request for Room " + control.getRoom().getRoomNumber());
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
	
	ObservableList<ServiceTable> servicelist = FXCollections.observableArrayList();
	ObservableList<FoodTable> foodlist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btn_submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				// TODO Auto-generated method stub
				ArrayList<Integer> items = new ArrayList<Integer>();
				servicelist.forEach((service) -> {
					if(service.getSelect().isSelected()) {
						String query = "Select * FROM Items WHERE item_Name = '" + service.getName() + "';";
						try {
							ResultSet result = connection().executeQuery(query);
							result.next();
							items.add(result.getInt("item_ID"));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				foodlist.forEach((food) -> {
					if(food.getSelect().isSelected()) {
						String query = "Select * FROM Items WHERE item_Name = '" + food.getName() + "';";
						try {
							ResultSet result = connection().executeQuery(query);
							result.next();
							items.add(result.getInt("item_ID"));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				int reqID = Request.createRequest(control.getBooking().getConfNum());
				items.forEach((item) -> {
					Request.createRequestItem(reqID, item);
				});
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Requests Have Been Submitted!");
				alert.setTitle("Success!");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(primary);
				alert.showAndWait();
				LoggedInGUI loggedin  = new LoggedInGUI();
				Scene loggedInScene = loggedin.getScene();
				loggedin.setInformation(control);
				
				primary.setScene(loggedInScene);
				primary.show();
			}
			
		});
		
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					LoggedInGUI loggedin  = new LoggedInGUI();
					Scene loggedInScene = loggedin.getScene();
					loggedin.setInformation(control);
					Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primary.setScene(loggedInScene);
					primary.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			String query1 = "SELECT * FROM Items WHERE item_price IS NULL";
			ResultSet rs = connection().executeQuery(query1);
			while(rs.next()) {
				servicelist.add(new ServiceTable(rs.getString("item_Name"), new CheckBox())); 
			}
			String query2 = "SELECT * FROM Items WHERE item_price IS NOT NULL";
			ResultSet rs1 = connection().executeQuery(query2);
			while(rs1.next()) {
				String price = String.format("%.2f",rs1.getFloat("item_price"));
				foodlist.add(new FoodTable(rs1.getString("item_Name"), "$" + price, new CheckBox()));
			}
			rs.close();
			rs1.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		col_namefree.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_namefood.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_select.setCellValueFactory(new PropertyValueFactory<>("select"));
		col_selectfood.setCellValueFactory(new PropertyValueFactory<>("select"));
		
		table_room.setItems(servicelist);
		table_food.setItems(foodlist);
	}

}
