package guiclasses;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import objectclasses.Account;
import objectclasses.Booking;
import objectclasses.Controller;
import objectclasses.Employee;
import objectclasses.Room;

public class AddEmployeeGUI extends Application implements Initializable{
	
	@FXML
	private TextField txt_fname;
	@FXML
	private TextField txt_lname;
	@FXML
	private TextField txt_username;
	@FXML
	private TextField txt_password;
	@FXML
	private RadioButton rbtn_admin;
	@FXML
	private Button btn_cancel;
	@FXML
	private Button btn_submit;
	
	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployee.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("uCheckin");
			primary.setScene(scene);
			primary.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployee.fxml"));
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
	
	public static boolean validate(String fname, String lname, String username, String password) {
		if (fname.equals("") || lname.equals("") || username.equals("") || password.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				LoggedInAdminGUI loggedin = new LoggedInAdminGUI();
				Scene loggedInScene = loggedin.getScene();
				primary.setFullScreen(true);
				primary.setScene(loggedInScene);
				primary.setFullScreen(true);
				primary.show();
			}
		});
		
		btn_submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				if (!validate(txt_fname.getText(), txt_lname.getText(), txt_username.getText(), txt_password.getText())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill in all the required fields");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				try {
					if (Employee.createEmployee(txt_fname.getText(), txt_lname.getText(), txt_password.getText(), txt_username.getText(), rbtn_admin.isSelected())) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Employee Created!");
						alert.setTitle("Success!");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.initOwner(primary);
						alert.showAndWait();
						LoggedInAdminGUI loggedin = new LoggedInAdminGUI();
						Scene loggedInScene = loggedin.getScene();
						primary.setFullScreen(true);
						primary.setScene(loggedInScene);
						primary.show();
						primary.setFullScreen(true);
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Unable to create employee!");
						alert.setTitle("Error!");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.initOwner(primary);
						alert.showAndWait();
						AddEmployeeGUI ae = new AddEmployeeGUI();
						Scene aes = ae.getScene();
						primary.setFullScreen(true);
						primary.setScene(aes);
						primary.show();
						primary.setFullScreen(true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}



}
