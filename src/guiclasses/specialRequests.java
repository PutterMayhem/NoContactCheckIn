package guiclasses;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import javax.swing.plaf.ButtonUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;

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
        //Declare env variables
        String custEmail = "john.doe@yourdomain.com";

        //Declare asset variables.
        String hiltonLogo = "src/images/download2.png";
        String hiltonPhoto = "src/images/hiltonimage2.jpg";

        //Base Pane for this scene
        GridPane baseGp = new GridPane();


        //////////////////////////////////////////////
        //Top-Left Pane
        //////////////////////////////////////////////

        //This pane contains the hotel Logo and customer email. Add to the upper left corner.
        Pane imgPane = new Pane();
        GridPane logoGrid = new GridPane();
        Text custEmailTxt = new Text(" " + custEmail);
        logoGrid.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        custEmailTxt.setStyle("-fx-background-color: #808080;");
        ImageView imgView = formatPicture(getAbsolutePath(hiltonLogo));
        imgPane.getChildren().add(imgView);
        imgPane.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        logoGrid.add(custEmailTxt, 0, 0);
        logoGrid.add(imgPane, 0, 1);
        baseGp.add(logoGrid, 0, 0);


        //////////////////////////////////////////////
        //Top-Middle Pane
        //////////////////////////////////////////////

        //This pane contains the title of the scene and customer lookup box
        //Create parent panes
        Pane lookup = new Pane();
        GridPane login = new GridPane();
        GridPane titleLogin = new GridPane();
        VBox blankVBox = new VBox();

        //Create text, text box, label, and button elements
        Text blankText = new Text("");
        Label emailLbl = new Label("Email Address: ");
        TextField emailTxtFld = new TextField("john.doe@yourdomain.com");
        Label confCodeLbl = new Label("Confirmation Code: ");
        TextField confCodeTxtFld = new TextField();
        Button lookupButton = new Button("Lookup");
        Text title = new Text("Make a Request");
        title.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 48));
        blankText.setFont(Font.font("verdana", 48));

        //Add elements to respective panes for formatting.
        blankVBox.getChildren().add(blankText);
        title.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(login, HPos.CENTER);

        //Add display panes and elements to the login GridPane
        titleLogin.add(title, 0, 0);
        titleLogin.add(blankVBox, 0, 1);
        titleLogin.add(login, 0,2);
        login.add(emailLbl, 0, 2);
        login.add(emailTxtFld, 1, 2);
        login.add(confCodeLbl, 2, 2);
        login.add(confCodeTxtFld, 3, 2);
        login.add(lookupButton, 4, 2);

        //add all elements to the primary top middle pane and display in the base pane.
        lookup.getChildren().add(titleLogin);
        lookup.setMinWidth(860);
        titleLogin.setMinWidth(860);

        lookup.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        baseGp.add(lookup, 1, 0);


        //////////////////////////////////////////////
        //Top-Right Pane
        //////////////////////////////////////////////

        //This is a blank, gray pane
        Pane blankPaneGray = new Pane();
        blankPaneGray.setMinWidth(600);
        blankPaneGray.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        baseGp.add(blankPaneGray, 2, 0);


        //////////////////////////////////////////////
        //Middle-Left Pane
        //////////////////////////////////////////////

        Pane blankPaneWhite = new Pane();
        blankPaneWhite.setMinWidth(600);
        baseGp.add(blankPaneWhite, 0, 1);


        //////////////////////////////////////////////
        //Middle Pane
        //////////////////////////////////////////////
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now();
        String custName = "John Doe";
        int roomNum = 203;
        Button  makeReq = new Button("Make a Request");

        Pane custInfo = new Pane();
        GridPane centerPane = new GridPane();
        Text custNotice = new Text("Verify the information below is correct,\n then click the 'Make a Request' button.");
        centerPane.add(custNotice, 0, 0);
        custInfo.getChildren().add(centerPane);

        GridPane bookingInfo = new GridPane();
        GridPane hotelInfo = new GridPane();
        Text custNameTxt = new Text(custName);
        bookingInfo.add(custNameTxt, 0, 0);

        Label checkInLbl = new Label("Check in: ");
        Label checkOutLbl = new Label("Check out: ");
        Label rmNum = new Label("Room #: ");
        Text checkInTxt = new Text(String.valueOf(checkIn));
        Text checkOutTxt = new Text(String.valueOf(checkOut));
        Text roomNumTxt = new Text(String.valueOf(roomNum));
        hotelInfo.add(blankText, 0,0);
        hotelInfo.add(checkInLbl, 0,1);
        hotelInfo.add(checkInTxt,1,1);
        hotelInfo.add(checkOutLbl,0,2);
        hotelInfo.add(checkOutTxt, 1,2);
        hotelInfo.add(rmNum, 2, 1);
        hotelInfo.add(roomNumTxt,3,1);
        bookingInfo.add(hotelInfo, 0, 2);
        bookingInfo.add(makeReq, 0, 3);
        centerPane.add(bookingInfo, 0, 1);
        GridPane.setHalignment(makeReq, HPos.RIGHT);
        GridPane.setHalignment(custNotice, HPos.CENTER);
        GridPane.setHalignment(custNameTxt, HPos.CENTER);

        custInfo.setMinWidth(860);
        bookingInfo.setMinWidth(860);
        hotelInfo.setMinWidth(860);
        baseGp.add(custInfo, 1,1);



        Pane hiltonBg = new Pane();
//        hiltonBg.setBackground(
//                new Background(
//                    new BackgroundImage(
//                        new Image(String.valueOf(getAbsolutePath(hiltonPhoto))),
//                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
//                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
//                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
//                    )
//                )
//        );
        baseGp.add(hiltonBg, 0, 1);

        Scene scene = new Scene(baseGp, 1920, 1080);

        stage.setTitle("UCheckIn - Make a Request");
        stage.setScene(scene);
        stage.show();
    }
}
