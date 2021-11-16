package guiclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import objectclasses.Controller;

public class LogIn extends Application {

	Controller control = Controller.getInstance();
	String stylesheet = getClass().getResource("/guiclasses/splashGUIstylesheet.css").toExternalForm();

	public Scene logIn() {

		GridPane gp = new GridPane();

		HBox imgPane = new HBox();
		File f = new File("src/images/download2.png");
		String imagePath = f.getAbsolutePath();
		ImageView imgView = formatPicture(imagePath);
		imgPane.setAlignment(Pos.CENTER);
		imgPane.getChildren().add(imgView);

		gp.add(imgPane, 0, 0, 3, 1);

		Text lblUserName = new Text("Username:");
		Text lblPassword = new Text("Password:");

		TextField userField = new TextField();
		TextField passwordField = new TextField();

		Pane imagePane = new Pane();
		File hiltonImage = new File("src/images/hiltonimage2.jpg");
		String hiltonImagePath = hiltonImage.getAbsolutePath();
		ImageView imgView2 = formatPicture(hiltonImagePath);
		imagePane.getChildren().add(imgView2);
		Button submit = new Button("Log In");

		gp.add(imgPane, 0, 0, 3, 1);
		gp.add(imagePane, 0, 1, 1, 2);
		gp.add(lblUserName, 1, 1);
		gp.add(userField, 2, 1);
		gp.add(lblPassword, 1, 2);
		gp.add(passwordField, 2, 2);
		gp.add(submit, 2, 3);

		Scene output = new Scene(gp, 1920, 1080);
		output.getStylesheets().add(stylesheet);
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

	@Override
	public void start(Stage primary) throws Exception {
		primary.setScene(logIn());
		primary.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
