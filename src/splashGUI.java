import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import objectclasses.DataStore;

public class splashGUI extends Application {

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		// Parent root = FXMLLoader.load(getClass().getResource("\\GUI.fxml"));
		// Group root = new Group();
		DataStore ds = DataStore.getInstance();
		GridPane gp = new GridPane();

		Pane imgPane = new Pane();
		ImageView imgView = formatPicture("c:/users/cegus/ucheckin/src/images/download2.png");
		imgPane.getChildren().add(imgView);
		imgPane.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
		gp.add(imgPane, 0, 0);

		Pane blank = new Pane();
		blank.setMinWidth(825);
		blank.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
		gp.add(blank, 1, 0);

		Pane cornerText = new Pane();
		Text powered = new Text("Powered by UCheckIn");
		powered.setFill(Color.GREEN);
		powered.setTextAlignment(TextAlignment.CENTER);
		powered.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, FontPosture.REGULAR, 14));
		cornerText.getChildren().add(powered);
		cornerText.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
		gp.add(cornerText, 2, 0);

		imgView = formatPicture("c:/users/cegus/ucheckin/src/images/hiltonimage2.jpg");
		Pane hiltonPane = new Pane();
		hiltonPane.getChildren().add(imgView);
		gp.add(hiltonPane, 0, 1);

		Scene scene = new Scene(gp, 1920, 1080);

		primary.setTitle("GUI");
		primary.setScene(scene);
		primary.show();
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
