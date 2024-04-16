package co.edu.uptc.FlightManagement.model;


import co.edu.uptc.FlightManagement.enums.Airline;
import co.edu.uptc.FlightManagement.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class Flight {
    private int id;
    private LocalTime departureTime;
    private String planeNumber;
    private Airline airline;
    private String imageUrl;
    private String gate;
    private Status status;
}
