package guiclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import objectclasses.Account;
import objectclasses.Booking;
import objectclasses.Controller;

public class LogIn extends Application {

	Controller control = Controller.getInstance();
	String stylesheet = getClass().getResource("/guiclasses/splashGUIstylesheet.css").toExternalForm();
	Stage primary;

	public LogIn(Stage primary) {
		this.primary = primary;
	}

	public Scene logIn() {

		GridPane gp = new GridPane();
		gp.getStyleClass().add("gpane");

		HBox imgPane = new HBox();
		File f = new File("src/images/download2.png");
		String imagePath = f.getAbsolutePath();
		ImageView imgView = formatPicture(imagePath);
		imgPane.setAlignment(Pos.CENTER);
		imgPane.getChildren().add(imgView);

		VBox inputBox = new VBox();
		Text lblUserName = new Text("Last Name:");
		lblUserName.getStyleClass().add("lblUserName");
		Text lblPassword = new Text("Confirmation #:");
		lblPassword.getStyleClass().add("lblPassword");
		TextField userField = new TextField();
		TextField passwordField = new TextField();
		inputBox.setMargin(inputBox, new Insets(500));

		Button submit = new Button("Log In");
		Button cancel = new Button("Cancel");

		inputBox.getChildren().add(lblUserName);
		inputBox.getChildren().add(userField);
		inputBox.getChildren().add(lblPassword);
		inputBox.getChildren().add(passwordField);
		inputBox.getChildren().add(submit);
		inputBox.getChildren().add(cancel);

		HBox imagePane = new HBox();
		File hiltonImage = new File("src/images/hiltonimage2.jpg");
		String hiltonImagePath = hiltonImage.getAbsolutePath();
		ImageView imgView2 = formatPicture(hiltonImagePath);
		imagePane.setAlignment(Pos.CENTER);
		imagePane.getChildren().add(imgView2);

		gp.add(imgPane, 0, 0, 3, 1);
		gp.add(imagePane, 0, 1);
		gp.add(inputBox, 1, 1);
		Scene output = new Scene(gp, 1920, 1080);
		output.getStylesheets().add(stylesheet);

		/*
		 * Button Actions
		 */
		submit.setOnAction(actionEvent -> {
			String username = userField.getText();
			String password = passwordField.getText();
			if (control.logIn(username, password)) {
				String query = "Select cust_email from booking where conf_ID = " + password + ";";
				try {
					ResultSet result = connection().executeQuery(query);
					String email = result.getString("cust_email");
					Account loggedIn = Booking.getAccountFromDB(username, email);
					control.setAccount(loggedIn);
					splashGUI main = new splashGUI();
					Scene splash = main.getScene();
					main.showWindow(primary, splash);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		cancel.setOnAction(actionEvent -> {
			splashGUI main = new splashGUI();
			Scene splash = main.getScene();
			main.showWindow(primary, splash);
		});
		return output;
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

	// Connection to database method
	private static Statement connection() {
		Statement statement = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uCheckIn", "root", "");
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	@Override
	public void start(Stage primary) throws Exception {
		primary.setScene(logIn());
		primary.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
