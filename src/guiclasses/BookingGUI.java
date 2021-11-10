package guiclasses;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookingGUI extends Application {
	
	@FXML
	private Button submit;
	@FXML
	private DatePicker checkin;
	@FXML
	private DatePicker checkout;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField email;
	@FXML
	private TextField room;

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass().getResource("BookingGUI.fxml"));
			Scene scene = new Scene(root, 600, 400);
			primary.setTitle("Booking");
			primary.setScene(scene);
			primary.show();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
