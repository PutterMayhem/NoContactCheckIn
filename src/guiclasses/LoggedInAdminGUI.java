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

public class LoggedInAdminGUI extends Application implements Initializable{
	
	@FXML
	private Label label_welcome;
	@FXML
	private Button btn_checkrequests;
	@FXML
	private Button btn_add;
	@FXML
	private Button btn_createbooking;
	@FXML
	private Button btn_logout;

	
	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedInAdmin.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("Admin Page");
			primary.setScene(scene);
			primary.setFullScreen(true);
			primary.show();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
		window.show();
		window.setFullScreen(true);
	}
	
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedInAdmin.fxml"));
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
				btn_checkrequests.setOnAction(new EventHandler<ActionEvent>( ) {

					@Override
					public void handle(ActionEvent event) {
						Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
						CheckRequestsGUI cr = new CheckRequestsGUI();
						Scene crs = cr.getScene();
						primary.setScene(crs);
						primary.setFullScreen(true);
						primary.show();
					}
					
				});
				btn_add.setOnAction(new EventHandler<ActionEvent>( ) {

					@Override
					public void handle(ActionEvent event) {
						Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
						AddEmployeeGUI ae = new AddEmployeeGUI();
						Scene aes = ae.getScene();
						primary.setFullScreen(true);
						primary.setScene(aes);
						primary.setFullScreen(true);
						primary.setTitle("uCheckin");
						primary.show();
					}
					
				});
				btn_createbooking.setOnAction(new EventHandler<ActionEvent>( ) {

					@Override
					public void handle(ActionEvent event) {
						Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
						AdminBookingGUI ab = new AdminBookingGUI();
						Scene abs = ab.getScene();
						primary.setFullScreen(true);
						primary.setScene(abs);
						primary.setFullScreen(true);
						primary.setTitle("uCheckin ");
						primary.show();
						
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
				
		
	}

	

}
