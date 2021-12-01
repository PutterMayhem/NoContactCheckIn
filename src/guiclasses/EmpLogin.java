package guiclasses;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	}

}
