package guiclasses;

import java.io.IOException;

import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.Account;
import objectclasses.Booking;
import objectclasses.Controller;
import objectclasses.Room;

public class Login extends Application implements Initializable{

	@FXML
	private Button loginButton;
	@FXML
	private Button cancelButton;

	@FXML
	private TextField emailField;

	@FXML
	private TextField confField;
	@FXML
	private Button empLogin;

	Controller control = Controller.getInstance();

	public void changeToLoggedIn(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoggedInGUI loggedin = new LoggedInGUI();
		Scene loggedInScene = loggedin.getScene();
		loggedin.setInformation(control);
		window.setFullScreen(true);
		window.setScene(loggedInScene);
		window.show();
		window.setFullScreen(true);
	}

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

			Scene scene = new Scene(root, 1920, 1080);
			primary.setFullScreen(true);
			primary.setScene(scene);
			primary.setTitle("Login");
			primary.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
		window.setFullScreen(true);
		window.show();
	}

	public void changeToEmpLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("emplogin.fxml"));
		Scene scene = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
	}

	public boolean validate(String lastName, String confNum) {
		if (lastName == null || confNum == null) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				String email = emailField.getText();
				String conf = confField.getText();
				if (!validate(email, conf)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill in all the required fields");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
					return;
				} else {
					if (control.custLogIn(email, conf)) {				
						Account acc = Account.getAccountFromDB(email);
						int confNum = Integer.parseInt(conf);
						System.out.println(confNum);
						Booking book = Booking.getBookingFromDB(acc, confNum);
						book.setConfNum(confNum);
						control.setAccount(acc);
						control.setBooking(book);
						control.setArrival(book.getArrival());
						control.setDepart(book.getDeparture());
						control.setRoom(Room.getRoomFromDB(book.getRoom().getRoomNumber()));
						System.out.println(control.getAccount().getFName());
						System.out.println(control.getBooking().getConfNum());
						System.out.println(control.getBooking().getArrival());
						LoggedInGUI loggedin = new LoggedInGUI();
						Scene loggedInScene = loggedin.getScene();
						loggedin.setInformation(control);
						primary.setFullScreen(true);
						primary.setScene(loggedInScene);
						primary.show();
						primary.setFullScreen(true);
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Wrong email or confirmation #");
						alert.setTitle("Error");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.showAndWait();
						return;
					}
				}
			}
			

		});

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
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

		empLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					changeToEmpLogin(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
