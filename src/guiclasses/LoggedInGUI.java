package guiclasses;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objectclasses.*;

public class LoggedInGUI extends Application implements Initializable{
	
	@FXML
	private Button btn_lengthstay;
	@FXML 
	private Button btn_checkout;
	@FXML
	private Button btn_request;
	@FXML
	private Button btn_logout;
	@FXML
	private static Label label_welcome;
	
	
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiclasses/LoggedIn.fxml"));
		loader.setController(this);
		
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1335, 720);
			primary.setTitle("Logged In");
			primary.setScene(scene);
			primary.show();
			
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void setUpInformation(String fname, int room_num) {
		label_welcome.setText("Welcome " + fname + "!");
	}
	
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String fname, int room) {
		Parent root = null;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if (fxmlFile != null) {
			try {
				root = FXMLLoader.load(BookingGUI.class.getResource(fxmlFile));
				stage.setTitle(title);
				stage.setScene(new Scene(root, 1920, 1080));
				stage.show(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			//Application.launch(splashGUI.class, new String[]{});
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initializing....");
		// TODO Auto-generated method stub
		btn_request.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				changeScene(event, "specialRequests.fxml", "Make a Request", null, 0);
			}
			
		});
		
		
	}

}
