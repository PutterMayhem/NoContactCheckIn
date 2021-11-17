package guiclasses;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Application;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objectclasses.Account;
import objectclasses.Booking;
import objectclasses.Controller;
import objectclasses.Room;

public class BookingGUI extends Application implements Initializable {

	@FXML
	private Button btn_submit;
	@FXML
	private DatePicker dtp_checkin;
	@FXML
	private DatePicker dtp_checkout;
	@FXML
	private TextField txt_fname;
	@FXML
	private TextField txt_lname;
	@FXML
	private TextField txt_email;
	@FXML
	private TextField txt_room;
	@FXML
	private Button btn_cancel;

	Controller control = Controller.getInstance();

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingGUI.fxml"));
		loader.setController(this);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("Booking");
			primary.setScene(scene);
			primary.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingGUI.fxml"));
		loader.setController(this);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			return scene;
		} catch (IOException e) {

		}
		return null;
	}

	public static void changeScene(ActionEvent event, String fxmlFile, String title, String fname, int room) {
		Parent root = null;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if (fxmlFile != null) {
			try {
				root = FXMLLoader.load(BookingGUI.class.getResource(fxmlFile));
				stage.setTitle(title);
				stage.setScene(new Scene(root, 1920, 1080));
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
			// Application.launch(splashGUI.class, new String[]{});
		}

	}

	public static boolean validate(String fname, String lname, String email, String room, LocalDate localDate,
			LocalDate localDate2) {
		if (fname == null || lname == null || email == null || room == null || localDate == null
				|| localDate2 == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getFName() {
		return txt_fname.getText();
	}

	public int getRoomNum() {
		return (Integer.parseInt(txt_room.getText()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initializing....");
		// TODO Auto-generated method stub
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				// switch back to splash page
			}

		});
		btn_submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!(validate(txt_fname.getText(), txt_lname.getText(), txt_email.getText(), txt_room.getText(),
						dtp_checkin.getValue(), dtp_checkout.getValue()))) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill in all the required fields");
					alert.setTitle("Error!");
					alert.showAndWait();
					return;
				}
				String checkin = dtp_checkin.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				String checkout = dtp_checkout.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				String fname = txt_fname.getText();
				String lname = txt_lname.getText();
				String email = txt_email.getText();
				int room_num = Integer.parseInt(txt_room.getText());

				if (Account.checkAccount(fname, lname, null, email)) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					// System.out.println(sdf2.format(sdf.parse(startDateString)));
					Account user = new Account(fname, lname, null, email);
					Room room = Room.getRoomFromDB(room_num);
					Date date1 = null;
					Date date2 = null;
					try {
						date1 = sdf.parse(checkin);
						date2 = sdf.parse(checkout);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Booking booking = new Booking(user, room, date1, date2);
					control.setBooking(booking);

					try {
						if (booking.createBooking() == true) {
							System.out.println(booking.getArrival());
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setContentText("Booking Created! Your Confirmation Number is: " + booking.getConfNum()
									+ "\n"
									+ "To login to our kiosks use your e-mail address and your confirmation number");
							alert.setTitle("Success!");
							alert.showAndWait();
							changeScene(event, "LoggedIn.fxml", "Logged In", fname, room_num);
						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setContentText("Failed to create booking");
							alert.setTitle("Error!");
							alert.showAndWait();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
	}

}
