package guiclasses;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmpLogin extends Application {

	@FXML
	Button loginButton;
	@FXML
	Button cancelButton;

	@FXML
	TextField userField;

	@FXML
	TextField passField;

	@Override
	public void start(Stage primary) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("emplogin.fxml"));
		Scene scene = new Scene(root, 1920, 1080);
		primary.setTitle("UCheckIn");
		primary.setScene(scene);
		primary.show();

	}

	public void initialize() {
		loginButton.setOnAction(actonEvent -> {

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
	}

	public static void main(String[] args) {
		launch(args);
	}
}
