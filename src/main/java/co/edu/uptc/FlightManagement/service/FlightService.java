package co.edu.uptc.FlightManagement.service;

import co.edu.uptc.FlightManagement.model.Flight;

import java.util.List;

public interface FlightService {
    boolean create(Flight flight);
    Flight get();
    Flight delete();
    boolean update(int id, Flight flightUpdated);
    List<Flight> getAll();
    List<Flight> getAll(int limit);
}
