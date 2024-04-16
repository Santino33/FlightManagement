package co.edu.uptc.FlightManagement.enums;

public enum Status {
    PENDING("Pending"),
    CANCELLED("Cancelled"),
    DELAYED("Delayed");


    private final String description;


    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
