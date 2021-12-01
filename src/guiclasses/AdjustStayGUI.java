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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.Account;
import objectclasses.Booking;
import objectclasses.Controller;
import objectclasses.Room;

public class AdjustStayGUI extends Application implements Initializable {
	
	@FXML
	private Label label_arrival;
	@FXML
	private Label label_departure;
	@FXML
	private DatePicker dp_departure;
	@FXML
	private Label label_room;
	@FXML
	private Button btn_confirm;
	@FXML
	private Button btn_back;

	private static Controller control;
	
	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdjustStay.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("Adjust Length of Stay");
			primary.setScene(scene);
			primary.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdjustStay.fxml"));
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
		AdjustStayGUI.control = control;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String checkin = sdf.format(control.getBooking().getArrival());
		String checkout = sdf.format(control.getBooking().getDeparture());
		label_arrival.setText(checkin);
		label_departure.setText(checkout);
		String room = String.valueOf(control.getRoom().getRoomNumber());
		label_room.setText(room);
	}
	
	public static boolean validate(LocalDate date) {
		if (date == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean checkDate(LocalDate newDate) {
		Date now = new Date();
		String sDeparture = newDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dateDeparture = null;
		try {
			dateDeparture = new SimpleDateFormat("MM/dd/yyyy").parse(sDeparture);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(dateDeparture.compareTo(now) < 0) {
			return false;
		} else if (dateDeparture.compareTo(control.getArrival()) < 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btn_confirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				if (!(validate(dp_departure.getValue()))) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please provide a new departure date!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				if(!(checkDate(dp_departure.getValue()))) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Date provided is invalid");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				try {
					String temp = dp_departure.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					Date newDeparture = sdf.parse(temp);
					Booking.adjustStay(control.getArrival(), newDeparture, control.getBooking().getConfNum());
					control.getBooking().setDeparture(newDeparture);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("Length of Stay has been Changed!");
					alert.setTitle("Success!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					LoggedInGUI loggedin  = new LoggedInGUI();
					Scene loggedInScene = loggedin.getScene();
					loggedin.setInformation(control);
					primary.setScene(loggedInScene);
					primary.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}

		});
		btn_back.setOnAction(new EventHandler<ActionEvent>() {

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
		
	}

}
