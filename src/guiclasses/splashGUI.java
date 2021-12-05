package guiclasses;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import objectclasses.Controller;

public class splashGUI extends Application {

	private Scene scene;

	@FXML
	Button bookButton;

	@FXML
	Button loginButton;
	@FXML
	Button checkinButton;

	@Override
	public void start(Stage primary) throws Exception {

		Controller control = Controller.getInstance();
		Parent guiView = FXMLLoader.load(getClass().getResource("/guiclasses/splashgui.fxml"));
		Scene scene = new Scene(guiView);
		primary.setTitle("GUI");
		primary.setScene(scene);
		primary.show();
		primary.setFullScreen(true);

	}

	public void initialize() {
		/*
		 * Button actions
		 */
		bookButton.setOnAction(actionEvent -> {
			try {
				changeToBooking(actionEvent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		loginButton.setOnAction(actionEvent -> {
			try {
				changeToLogin(actionEvent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		checkinButton.setOnAction(actionEvent -> {
			Alert checkIn = new Alert(AlertType.INFORMATION);
			checkIn.setContentText("You have been checked in.");
			checkIn.setHeaderText("Check In");
			checkIn.setTitle("Check In");
			checkIn.showAndWait();
		});
	}

	public void changeToLogin(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene loginView = new Scene(view);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(loginView);
		window.setFullScreen(true);
	}

	public void changeToBooking(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("BookingGUI.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.setFullScreen(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
