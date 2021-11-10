package guiclasses;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class specialRequests extends Application {

    @FXML
    private Button lookupBtn;
    @FXML
    private Button makeRequestBtn;
    @FXML
    private TextField emailAddr;
    @FXML
    private TextField confCode;
    @FXML
    private Text custName;
    @FXML
    private Text checkInTime;
    @FXML
    private Text checkOutTime;
    @FXML
    private Text roomNumber;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    @Override
    public void start(Stage primary) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("specialRequests.fxml"));
            Scene scene = new Scene(root, 1920,1080);
            primary.setTitle("Make a Request");
            primary.setScene(scene);
            primary.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
