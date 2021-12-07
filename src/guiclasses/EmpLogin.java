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

public class EmpLogin extends Application {

	@FXML
	Button loginButton;
	@FXML
	Button cancelButton;

	@FXML
	TextField userField;

	@FXML
	TextField passField;

	Controller controller = Controller.getInstance();

	@Override
	public void start(Stage primary) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("emplogin.fxml"));
		Scene scene = new Scene(root, 1920, 1080);
		primary.setTitle("UCheckIn");
		primary.setScene(scene);
		primary.show();
		primary.setFullScreen(true);

	}

	public void initialize() {
		loginButton.setOnAction(actionEvent -> {
			String user = userField.getText();
			String pass = passField.getText();
			if (!validate(user, pass)) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Employee Login");
				alert.setContentText("Please fill out all the fields");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			} else {
				boolean login = controller.empLogin(userField.getText(), passField.getText());
				if (login == true) {
					try {
						changeToEmpLoggedIn(actionEvent);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Username or password is incorrect");
					alert.setTitle("Employee Login");
					alert.showAndWait();
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
	}

	public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
		window.setFullScreen(true);
	}

	public void changeToEmpLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LoggedInAdmin.fxml"));

		Scene empLoggedIn = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(empLoggedIn);
		window.setFullScreen(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean validate(String userName, String password) {
		if (userName == null || password == null) {
			return false;
		}
		return true;
	}

}
