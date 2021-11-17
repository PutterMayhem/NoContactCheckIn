package guiclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import objectclasses.Controller;

public class splashGUI extends Application {

	private Scene scene;

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		// Parent root = FXMLLoader.load(getClass().getResource("\\GUI.fxml"));
		// Group root = new Group();
		Controller control = Controller.getInstance();
		GridPane gp = new GridPane();

		String stylesheet = getClass().getResource("/guiclasses/splashGUIstylesheet.css").toExternalForm();
		gp.getStyleClass().add("gpane");

		HBox imgPane = new HBox();
		File f = new File("src/images/download2.png");
		Text loggedIn = new Text("Not logged in");
		loggedIn.setId("loggedin");
		if (control.getAccount() != null) {
			loggedIn.setText("Logged in as: " + control.getAccount().getEmail());
		}
		String imagePath = f.getAbsolutePath();
		ImageView imgView = formatPicture(imagePath);
		imgPane.setAlignment(Pos.CENTER);
		imgPane.getChildren().add(imgView);
		imgPane.getChildren().add(loggedIn);

		gp.add(imgPane, 0, 0, 3, 1);

//		Pane cornerText = new Pane();
//		Text powered = new Text("Powered by UCheckIn");
//		powered.setFill(Color.BLACK);
//		powered.setTextAlignment(TextAlignment.CENTER);
//		powered.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, FontPosture.REGULAR, 14));
//		cornerText.getChildren().add(powered);
//
//		gp.add(cornerText, 2, 0);

		File hiltonImage = new File("src/images/hiltonimage2.jpg");
		String hiltonImagePath = hiltonImage.getAbsolutePath();
		imgView = formatPicture(hiltonImagePath);

		Pane hiltonPane = new Pane();
		hiltonPane.getChildren().add(imgView);
		gp.add(hiltonPane, 0, 1);

		VBox buttonPane = new VBox();
		Button bookRoom = new Button("Book A Room");
		Button logIn = new Button("Log in");
		Button checkIn = new Button("Check in");

		// Button Customization

		bookRoom.setMinHeight(213);
		bookRoom.setMinWidth(960);

		logIn.setMinWidth(960);
		logIn.setMinHeight(213);

		checkIn.setMinWidth(960);
		checkIn.setMinHeight(213);

		Font font = Font.font("Trebuchet MS", FontWeight.MEDIUM, 48);

		bookRoom.setFont(font);
		logIn.setFont(font);
		checkIn.setFont(font);

		buttonPane.getChildren().add(bookRoom);
		buttonPane.getChildren().add(logIn);
		buttonPane.getChildren().add(checkIn);
		gp.add(buttonPane, 2, 1);

		Pane bottom = new Pane();
		bottom.setMinHeight(113);
		Label lbl = new Label("Copyright 2021 Super Awesome Dev Team");
		Font lblFont = Font.font("Trebuchet MS", FontWeight.MEDIUM, 16);
		lbl.setFont(lblFont);
		bottom.getChildren().add(lbl);
		bottom.setMinWidth(1080);

		gp.add(bottom, 0, 3, 3, 1);

		scene = new Scene(gp, 1920, 1080);

		scene.getStylesheets().add(stylesheet);
		primary.setTitle("GUI");
		primary.setScene(scene);
		primary.show();

		/*
		 * Button actions
		 */
		bookRoom.setOnAction(actionEvent -> {
			BookingGUI booking = new BookingGUI();
			Scene bookingScene = booking.getScene();
			primary.setScene(bookingScene);
			primary.show();
		});

		logIn.setOnAction(actionEvent -> {
			LogIn login = new LogIn(primary);
			Scene loginScene = login.logIn();
			primary.setScene(loginScene);
			primary.show();
		});

		checkIn.setOnAction(actionEvent -> {
			// TODO make check in window appear
		});

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
