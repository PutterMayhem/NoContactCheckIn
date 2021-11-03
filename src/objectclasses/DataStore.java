package objectclasses;

public class DataStore {
	private Account user;
	private Room room;
	private final static DataStore ds = new DataStore();

	private DataStore() {
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

}
