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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.Controller;

public class EmpLogin extends Application implements Initializable{

	@FXML
	private Button loginButton;
	@FXML
	private Button cancelButton;

	@FXML
	private TextField userField;

	@FXML
	private PasswordField passField;

	Controller controller = Controller.getInstance();

	@Override
	public void start(Stage primary) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("emplogin.fxml"));
		Scene scene = new Scene(root, 1920, 1080);
		primary.setTitle("UCheckIn");

		primary.setFullScreen(true);
		primary.setScene(scene);
		primary.show();

	}
	
	public void changeToLogin(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene loginView = new Scene(view);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		
		window.setScene(loginView);
		window.setFullScreen(true);
		window.show();
	}


	public void changeToEmpLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LoggedInAdmin.fxml"));

		Scene empLoggedIn = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(empLoggedIn);
		window.show();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("test");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(userField.getText());
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				String user = userField.getText();
				String pass = passField.getText();
				if (!validate(user, pass)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Employee Login");
					alert.setContentText("Please fill out all the fields");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
				} else {
					boolean login = controller.empLogin(user, pass);
					if (login == true) {
						try {
							changeToEmpLoggedIn(event);
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
			}

		});

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					changeToLogin(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
