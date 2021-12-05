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
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import objectclasses.*;

public class LoggedInGUI implements Initializable {
	
	@FXML
	private Button btn_lengthstay;
	@FXML
	private Button btn_request;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_checkin;
	@FXML
	private Label label_welcome;
	private static Controller control;
	private boolean isCheckedIn = false;
	
	public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
		window.show();
		window.setFullScreen(true);
	}
	
	public void setInformation(Controller control) {
		LoggedInGUI.control = control;
		label_welcome.setText("Welcome " + control.getAccount().getFName() + "!");
		isCheckedIn = control.getBooking().isCheckedIn();
		if(control.getBooking().isCheckedIn()) {
			btn_checkin.setText("Check Out");
		}
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
				Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
				MakeRequestGUI makeRequest  = new MakeRequestGUI();
				Scene makeRequestScene = makeRequest.getScene(screenSize.getWidth(), screenSize.getHeight());
				makeRequest.setInformation(control);
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primary.setScene(makeRequestScene);
				primary.show();
				primary.setFullScreen(true);
			}
			
		});
		btn_logout.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent event) {
				try {
					changeToSplash(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		btn_checkin.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent event) {
				if (!isCheckedIn) {
					Booking b = control.getBooking();
					b.checkIn();
					Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("You have checked-in to your room!");
					alert.setTitle("Success!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					LoggedInGUI loggedin = new LoggedInGUI();
					Scene loggedInScene = loggedin.getScene();
					loggedin.setInformation(control);
					primary.setFullScreen(true);
					primary.setScene(loggedInScene);
					primary.show();
					primary.setFullScreen(true);
					primary.setMaximized(true);
				} else {
					//check out
				}
			}
		});
		
		
		btn_lengthstay.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
				AdjustStayGUI adjustStay  = new AdjustStayGUI();
				Scene adjustStayScene = adjustStay.getScene();
				adjustStay.setInformation(control);
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primary.setScene(adjustStayScene);
				primary.show();
				primary.setFullScreen(true);
			}
			
		});
		
		
		
	}

}
