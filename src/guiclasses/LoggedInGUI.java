package guiclasses;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import objectclasses.Booking;
import objectclasses.Controller;

public class LoggedInGUI implements Initializable {

	@FXML
	private Button btn_lengthstay;
	@FXML
	private Button btn_request;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_checkin;

	@FXML
	private Button cancelBooking;

	@FXML
	private Label label_welcome;
	private static Controller control;
	private boolean isCheckedIn = false;

	public void changeToSplash(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("splashgui.fxml"));
		Scene splashView = new Scene(root);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(splashView);
		window.show();
		window.setFullScreen(true);
	}

	public void setInformation(Controller control) {
		LoggedInGUI.control = control;
		label_welcome.setText("Welcome " + control.getAccount().getFName() + "!");
		isCheckedIn = control.getBooking().isCheckedIn();
		if (control.getBooking().isCheckedIn()) {
			btn_checkin.setText("Check Out");
			cancelBooking.setVisible(false);
		}
	}
	

	public Scene getScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedIn.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btn_request.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				if (!control.getBooking().isCheckedIn()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please check in first to make requests!");
					alert.setTitle("Error!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					return;
				} else {
					MakeRequestGUI makeRequest = new MakeRequestGUI();
					Scene makeRequestScene = makeRequest.getScene();
					makeRequest.setInformation(control);
					primary.setScene(makeRequestScene);
					primary.show();
					primary.setFullScreen(true);
				}
			}

		});
		btn_logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					changeToSplash(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		btn_checkin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!isCheckedIn) {
					Booking b = control.getBooking();
					b.checkIn();
					Date td = new Date();
					control.setArrival(td);
					Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("You have checked-in to your room!");
					alert.setTitle("Success!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					LoggedInGUI loggedin = new LoggedInGUI();
					Scene loggedInScene = loggedin.getScene();
					loggedin.setInformation(control);
					primary.setFullScreen(true);
					primary.setScene(loggedInScene);
					primary.show();
					primary.setFullScreen(true);
					primary.setMaximized(true);
				} else {
					// check out
					Booking b = control.getBooking();
					Date today = new Date();
					int lengthStay = (int) ChronoUnit.DAYS.between(control.getArrival().toInstant(), today.toInstant());
					Booking.setLengthStay(lengthStay, b.getConfNum());
					b.checkOut(control.getVcc().hashCode());
					if (lengthStay == 0) {
						lengthStay += 1;
					}
					float rate = control.getRoom().getRate();
					float total = rate * lengthStay;
					float hotelfee = total;
					float servicetotal = control.getAmountOwed();
					String sql = "SELECT r.conf_ID, ri.reqitem_ID, r.req_ID, i.item_Name, r.req_DateTime, "
							+ "ri.fulfilled, i.item_price FROM Request r INNER JOIN RequestItems ri "
							+ "ON r.req_ID = ri.req_ID INNER JOIN Items i ON i.item_ID = ri.item_ID "
							+ "WHERE conf_ID = " + b.getConfNum() + " ORDER BY ri.fulfilled";
					try {
						ResultSet rs = Controller.connection().executeQuery(sql);
						while (rs.next()) {
							if (rs.getInt("fulfilled") == 0) {
								servicetotal -= rs.getFloat("item_price");
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					total += servicetotal;
					String price = String.format("%.2f", total);
					Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("Days Stayed = " + lengthStay + "\n"
							+ "Services Fee = $" + servicetotal + "\n"
							+ "Hotel Stay Fee = $" + hotelfee + "\n"
											+ "Total = $" + total + "\n"
									+ "Payment has been Approved! Thank you for your stay!");
					alert.setTitle("Success!");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					try {
						changeToSplash(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}							
					
				}
			}
		});

		btn_lengthstay.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
				AdjustStayGUI adjustStay = new AdjustStayGUI();
				Scene adjustStayScene = adjustStay.getScene();
				adjustStay.setInformation(control);
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primary.setScene(adjustStayScene);
				primary.show();
				primary.setFullScreen(true);
			}

		});

		cancelBooking.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
				if (!control.cancelBooking()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Cancel Booking");
					alert.setContentText("There was an error cancelling your booking");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Cancel Booking");
					alert.setContentText("Booking has been cancelled");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(primary);
					alert.showAndWait();
					try {
						changeToSplash(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});

	}

}
