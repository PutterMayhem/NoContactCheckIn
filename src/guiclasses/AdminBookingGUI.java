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
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import objectclasses.Account;
import objectclasses.Booking;
import objectclasses.Controller;
import objectclasses.Room;
import objectclasses.VirtualCCProcessor;

public class AdminBookingGUI extends Application implements Initializable {

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
	@FXML
	private DatePicker dtp_expiration;
	@FXML
	private TextField txt_cardnum;
	@FXML
	private TextField txt_csc;

	Controller control = Controller.getInstance();

	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminBookingGUI.fxml"));
		loader.setController(this);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			primary.setTitle("uCheckin -- Book a Room");
			primary.setFullScreen(true);
			primary.setScene(scene);
			primary.show();
			

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminBooking.fxml"));
		loader.setController(this);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 1920, 1080);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean validate(String fname, String lname, String email, String room, LocalDate localDate,
			LocalDate localDate2, String cardnum, String csc, LocalDate expiration) {
		if (fname.equals("") || lname.equals("") || email.equals("") || room.equals("") || localDate == null
				|| localDate2 == null || cardnum.equals(null)  || csc.equals(null) || expiration == null) {
			return false;
		} else {
			return true;
		}
	}
	
	 // Return true if the card number is valid
    public static boolean isValid(long number)
    {
       return (getSize(number) >= 13 &&
               getSize(number) <= 16) &&
               (prefixMatched(number, 4) ||
                prefixMatched(number, 5) ||
                prefixMatched(number, 37) ||
                prefixMatched(number, 6)) &&
              ((sumOfDoubleEvenPlace(number) +
                sumOfOddPlace(number)) % 10 == 0);
    }
 
    // Get the result from Step 2
    public static int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
         
        return sum;
    }
 
    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }
 
    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");       
        return sum;
    }
 
    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }
 
    // Return the number of digits in d
    public static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }
 
    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
    
    public static boolean checkCSC(String csc) {
    	return csc.matches("^\\d{3}$");
    }


	
	
	public static boolean checkDate(LocalDate arrival, LocalDate departure) {
		Date now = new Date();
		String sArrival = arrival.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String sDeparture = departure.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dateDeparture = null;
		Date dateArrival = null;
		try {
			dateDeparture = new SimpleDateFormat("MM/dd/yyyy").parse(sDeparture);
			dateArrival = new SimpleDateFormat("MM/dd/yyyy").parse(sArrival);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(dateDeparture.compareTo(now) < 0 || dateArrival.compareTo(now) < 0) {
			return false;
		} else if (dateDeparture.compareTo(dateArrival) <= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				LoggedInAdminGUI loggedin = new LoggedInAdminGUI();
				Scene loggedInScene = loggedin.getScene();
				primary.setFullScreen(true);
				primary.setScene(loggedInScene);
				primary.setFullScreen(true);
				primary.show();
			}
			
		});
		btn_submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				if (!(validate(txt_fname.getText(), txt_lname.getText(), txt_email.getText(), txt_room.getText(),
						dtp_checkin.getValue(), dtp_checkout.getValue(), txt_cardnum.getText(), 
						txt_csc.getText(), dtp_expiration.getValue()))) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill in all the required fields");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				if (!checkDate(dtp_checkin.getValue(), dtp_checkout.getValue())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please make sure the dates provided make sense!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				
				String checkin = dtp_checkin.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				String checkout = dtp_checkout.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				String fname = txt_fname.getText();
				String lname = txt_lname.getText();
				String email = txt_email.getText();
				int room_num = Integer.parseInt(txt_room.getText());
				String stringexp = dtp_expiration.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				String cardnum = txt_cardnum.getText();
				long longcard = 0;
				try {
					longcard = Long.parseLong(cardnum);
					if (!isValid(longcard)) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Please enter a valid credit card number!");
						alert.setTitle("Error!");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.initOwner(primary);
						alert.showAndWait();
						return;
					}
				} catch(Exception e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please enter a valid credit card number!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				int csc = 0;
				try {
					csc = Integer.parseInt(txt_csc.getText());
				} catch (Exception e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please ensure CSC is a 3-digit number!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}
				if (!checkCSC(txt_csc.getText())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please ensure CSC is a 3-digit number!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				}

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

					try {
						if (booking.createBooking() == true) {
							int hash = VirtualCCProcessor.hashCode(stringexp, cardnum, csc);
							booking.insertCCToken(hash);
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setContentText("Booking Created! The confirmation number is: " + booking.getConfNum());
							alert.setTitle("Success!");
							alert.initModality(Modality.APPLICATION_MODAL);
							alert.initOwner(primary);
							alert.showAndWait();
							LoggedInAdminGUI loggedin = new LoggedInAdminGUI();
							Scene loggedInScene = loggedin.getScene();
							primary.setFullScreen(true);
							primary.setScene(loggedInScene);
							primary.show();
							primary.setFullScreen(true);
						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setContentText("Failed to create booking");
							alert.setTitle("Error!");
							alert.initModality(Modality.APPLICATION_MODAL);
							alert.initOwner(primary);
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
