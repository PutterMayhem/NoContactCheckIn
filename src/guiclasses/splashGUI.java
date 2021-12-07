package guiclasses;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		primary.setFullScreen(true);
		primary.setScene(scene);
		primary.show();

	}

	public void initialize() {
		/*
		 * Button actions
		 */
		bookButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					roomBrowserGUI rb  = new roomBrowserGUI();
					Scene rbs = rb.getScene();
					Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primary.setScene(rbs);
					primary.show();
					primary.setFullScreen(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
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

	}

	public void changeToLogin(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene loginView = new Scene(view);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setFullScreen(true);
		window.setScene(loginView);
	}

	public void changeToBooking(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("BookingGUI.fxml"));
		Scene scene = new Scene(view);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setFullScreen(true);
		window.setScene(scene);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
