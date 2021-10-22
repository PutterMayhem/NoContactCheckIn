import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class splashGUI extends Application {

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("src/splashGUItest.fxml"));
		// Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		primary.setTitle("GUI");
		primary.setScene(scene);
		primary.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
