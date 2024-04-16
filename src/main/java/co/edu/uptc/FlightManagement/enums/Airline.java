package co.edu.uptc.FlightManagement.enums;

public enum Airline {
    QATARAIRWAYS("Qatar Airways"),
    SINGAPOREAIRLINES("Singapore Airlines"),
    INDIANAIRWAYS("Indian Airways"),
    EMIRATES("Emirates");

    private final String name;

    Airline(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
