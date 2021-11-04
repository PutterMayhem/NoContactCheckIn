import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class splashGUI extends Application {

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		// Parent root =
		// FXMLLoader.load(getClass().getResource("src/splashGUItest.fxml"));
		// Group root = new Group();
		GridPane gp = new GridPane();
		Scene scene = new Scene(gp, 750, 500);
		HBox hb = new HBox();

		gp.add(hb, 0, 1);
		primary.setTitle("GUI");
		primary.setScene(scene);
		primary.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
