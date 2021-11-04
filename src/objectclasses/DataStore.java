package objectclasses;

public class DataStore {
	private Account user;
	private Room room;
	private Booking booking;
	private static DataStore ds = new DataStore();

	private DataStore() {
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public static DataStore getDs() {
		return ds;
	}

	public static DataStore getInstance() {
		if (ds == null) {
			ds = new DataStore();
		}
		return ds;
	}

}
