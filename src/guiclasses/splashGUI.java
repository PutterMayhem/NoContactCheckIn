package guiclasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objectclasses.Controller;

public class splashGUI extends Application {

	private Scene scene;

	@FXML
	Button bookButton;

	@FXML
	Button loginButton;
	@FXML
	Button checkInButton;

	@Override
	public void start(Stage primary) throws Exception {

		Controller control = Controller.getInstance();
		Parent guiView = FXMLLoader.load(getClass().getResource("/guiclasses/splashgui.fxml"));
		Scene scene = new Scene(guiView);
		primary.setTitle("GUI");
		primary.setScene(scene);
		primary.show();

	}

	public void initialize() {
		/*
		 * Button actions
		 */
		bookButton.setOnAction(actionEvent -> {
			BookingGUI booking = new BookingGUI();
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

		checkInButton.setOnAction(actionEvent -> {
			// TODO make check in window appear
		});
	}

	public void changeToLogin(ActionEvent event) throws IOException {
		Parent tableView = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene loginView = new Scene(tableView);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(loginView);
	}

	public void changeToBooking(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("BookingGUI.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
	}

	public void showWindow(Stage primary, Scene scene) {
		primary.setScene(scene);
		primary.show();
	}

	protected Scene getScene() {
		return scene;
	}

	private ImageView formatPicture(String location) {
		try {
			FileInputStream fis = new FileInputStream(location);
			Image img = new Image(fis);
			return new ImageView(img);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
