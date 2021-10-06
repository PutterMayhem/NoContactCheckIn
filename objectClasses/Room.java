package objectClasses;

public class Room {
    //TODO: Validate all needed properties, setters, and getters
    int roomNumber;
    boolean booked;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
