package objectClasses;

public class Booking {
    //TODO: Validate all needed properties, setters, and getters
    String customerFName;
    String customerLName;
    int confNum;
    int roomNumber;

    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public int getConfNum() {
        return confNum;
    }

    public void setConfNum(int confNum) {
        this.confNum = confNum;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
