package nl.yacht.molvenov3.model;

public class Table {

    private int tableNumber;
    private int numberOfSeats;
    private boolean isAvailable;

    //region getters and setters
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    //endregion

    public Table(int tableNumber, int numberOfSeats, boolean isAvailable) {
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
        this.isAvailable = isAvailable;

    }
}

