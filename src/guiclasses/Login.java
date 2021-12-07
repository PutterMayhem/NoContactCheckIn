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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.Controller;

public class Login extends Application {

	@FXML
	Button loginButton;
	@FXML
	Button cancelButton;

	@FXML
	TextField emailField;

	@FXML
	TextField confField;
	@FXML
	Button empLogin;

	Controller control = Controller.getInstance();

	public void changeToLoggedIn(ActionEvent event) throws IOException {
		Parent tableView = FXMLLoader.load(getClass().getResource("LoggedIn.fxml"));
		Scene loggedInView = new Scene(tableView);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(loggedInView);
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

	public void initialize() {

		loginButton.setOnAction(eventAction -> {
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
					try {
						changeToLoggedIn(eventAction);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Wrong email or confirmation #");
					alert.setTitle("Error");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
					return;
				}
			}

		});

		cancelButton.setOnAction(actionEvent -> {
			try {
				changeToSplash(actionEvent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		empLogin.setOnAction(actionEvent -> {
			try {
				changeToEmpLogin(actionEvent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
	}

	public void changeToEmpLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("emplogin.fxml"));
		Scene scene = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
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

}
