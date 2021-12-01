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

public class LoggedInGUI implements Initializable {
	
	@FXML
	private Button btn_lengthstay;
	@FXML 
	private Button btn_checkout;
	@FXML
	private Button btn_request;
	@FXML
	private Button btn_logout;
	@FXML
	private Label label_welcome;
	private static Controller control;
	
	
	public void setInformation(Controller control) {
		LoggedInGUI.control = control;
		label_welcome.setText("Welcome " + control.getAccount().getFName() + "!");
	}
	
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedIn.fxml"));
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
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
		btn_request.setOnAction(new EventHandler<ActionEvent>( ) {

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
		btn_lengthstay.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				AdjustStayGUI adjustStay  = new AdjustStayGUI();
				Scene adjustStayScene = adjustStay.getScene();
				adjustStay.setInformation(control);
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primary.setScene(adjustStayScene);
				primary.show();
			}
			
		});
		
		
		
	}

}
