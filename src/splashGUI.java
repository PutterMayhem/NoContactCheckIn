import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
		ImageView imgView = formatPicture("c:/users/cegus/ucheckin/src/images/download.png");
		imgPane.getChildren().add(imgView);
		gp.add(imgPane, 0, 0);

		Text powered = new Text("Powered by UCheckIn");
		gp.add(powered, 0, 1);

		Scene scene = new Scene(gp, 750, 500);

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
