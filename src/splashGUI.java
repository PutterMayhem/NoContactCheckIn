import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class splashGUI extends Application {

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("src/splashGUI.fxml"));
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
