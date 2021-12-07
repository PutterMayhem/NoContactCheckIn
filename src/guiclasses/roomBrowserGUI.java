package guiclasses;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objectclasses.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class roomBrowserGUI extends Application implements Initializable {
	
	@FXML
	private TableView<RoomTable> table_rooms;
	@FXML
	private TableColumn<RoomTable, Integer> col_roomnum;
	@FXML
	private TableColumn<RoomTable, String> col_roomtype;
	@FXML
	private TableColumn<RoomTable, String> col_price;
	@FXML
	private TableColumn<RoomTable, CheckBox> col_select;
	

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
