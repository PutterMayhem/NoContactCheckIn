package guiclasses;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.plaf.ButtonUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class specialRequests extends Application {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
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

    // Method takes in the relative path for a file and returns the absolute path on the system.
    private String getAbsolutePath (String relativePath) {
        File f = new File(relativePath);
        String absolutePath = f.getAbsolutePath();
        return absolutePath;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Declare asset variables.
        String hiltonLogo = "src/images/download2.png";
        String hiltonPhoto = "src/images/hiltonimage2.jpg";

        GridPane gp = new GridPane();

        Pane imgPane = new Pane();
        ImageView imgView = formatPicture(getAbsolutePath(hiltonLogo));
        imgPane.getChildren().add(imgView);
        imgPane.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        gp.add(imgPane, 0, 0);

        // TODO: Add buttons and fields for special requests
        Pane lookup = new Pane();
        GridPane login = new GridPane();
        Label emailLbl = new Label("Email Address: ");
        TextField emailTxtFld = new TextField("john.doe@yourdomain.com");
        Label confCodeLbl = new Label("Confirmation Code: ");
        TextField confCodeTxtFld = new TextField();
        Button lookupButton = new Button("Lookup");

        login.add(emailLbl, 0, 0);
        login.add(emailTxtFld, 1, 0);
        login.add(confCodeLbl, 2, 0);
        login.add(confCodeTxtFld, 3, 0);
        login.add(lookupButton, 4, 0);

        lookup.getChildren().add(login);
        lookup.setMinWidth(825);
        lookup.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));

        gp.add(lookup, 1, 0);

        Scene scene = new Scene(gp, 1920, 1080);

        stage.setTitle("UCheckIn - Make a Request");
        stage.setScene(scene);
        stage.show();
    }
}
