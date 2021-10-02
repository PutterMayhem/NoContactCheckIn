package objectClasses;

public class Room {
    //TODO: Validate all needed properties, setters, and getters
    int rooNumber;
    boolean booked;

    public int getRooNumber() {
        return rooNumber;
    }

    public void setRooNumber(int rooNumber) {
        this.rooNumber = rooNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
