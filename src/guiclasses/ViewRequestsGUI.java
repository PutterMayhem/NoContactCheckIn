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

public class ViewRequestsGUI implements Initializable{
	@FXML
    private Button btn_request1;
    @FXML
    private Button btn_back1;
    @FXML
    private Button btn_cancel;
    @FXML
    private TableView<RequestTable> table_request;
    @FXML
    private TableColumn<RequestTable, String> col_request;
    @FXML
    private TableColumn<RequestTable, Date> col_time;
    @FXML
    private TableColumn<RequestTable, String> col_status;
    @FXML
    private TableColumn<RequestTable, CheckBox> col_cancel;
    
    private static Controller control;
    
    public Scene getScene() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ViewRequests.fxml"));
			Scene scene = new Scene(root, 1920, 1080);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public void setInformation(Controller control) {
		ViewRequestsGUI.control = control;
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
    
    ObservableList<RequestTable> requestlist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			String query1 = "SELECT r.conf_ID, r.req_ID, i.item_Name, r.req_DateTime, r.fulfilled FROM Request r " + 
					"INNER JOIN RequestItems ri ON r.req_ID = ri.req_ID " + 
					"INNER JOIN Items i ON i.item_ID = ri.item_ID " + 
					"WHERE conf_ID = " + control.getBooking().getConfNum() + " ORDER BY fulfilled";
			ResultSet rs = connection().executeQuery(query1);
			while(rs.next()) {
				String temp = null;
				if (rs.getInt("fulfilled") == 0) {
					temp = "Pending";
				} else if (rs.getInt("fulfilled") ==1) {
					temp = "Completed";
				}
				
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		col_request.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("requestTime"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		col_cancel.setCellValueFactory(new PropertyValueFactory<>("select"));

		
		table_request.setItems(requestlist);
		
		btn_back1.setOnAction(new EventHandler<ActionEvent>() {

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
		btn_request1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MakeRequestGUI makeRequest  = new MakeRequestGUI();
				Scene makeRequestScene = makeRequest.getScene();
				makeRequest.setInformation(control);
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primary.setScene(makeRequestScene);
				primary.show();
			}
		});
	}

}
