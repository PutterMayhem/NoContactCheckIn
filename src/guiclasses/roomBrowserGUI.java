package guiclasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class roomBrowserGUI extends Application {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    @Override
    public void start(Stage primary) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("roomBrowser.fxml"));
            Scene scene = new Scene(root, 1920,1080);
            primary.setTitle("Make a Request");
            primary.setScene(scene);
            primary.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
