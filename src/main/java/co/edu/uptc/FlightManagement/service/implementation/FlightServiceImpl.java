package co.edu.uptc.FlightManagement.service.implementation;


import co.edu.uptc.FlightManagement.enums.Airline;
import co.edu.uptc.FlightManagement.enums.Status;
import co.edu.uptc.FlightManagement.model.Flight;
import co.edu.uptc.FlightManagement.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService {
    /*
    Queue class is abstract, cannot be instatiated directly
     */
    Queue<Flight> flightsQueue = new LinkedList<Flight>();


    @Override
    public boolean create(Flight flight) {
        log.info("Creating flight: {}", flight.getId());
        setServerImageUrl(flight);
        return flightsQueue.offer(flight);
    }

    @Override
    public Flight get() {
        log.info("Retrieving flight: {}", flightsQueue.peek().getId());
        return flightsQueue.peek();
    }

    @Override
    public Flight delete() {
        log.info("Deleting flight: {}", flightsQueue.peek().getId());
        return flightsQueue.poll();
    }

    @Override
    public boolean update(int id, Flight flightUpdated) {
        log.info("Updating flight: {}", flightUpdated.getId());
        Iterator<Flight> iterator = flightsQueue.iterator();
        while (iterator.hasNext()) {
            Flight flight = iterator.next();
            if (flight.getId() == id) {
                flight.setDepartureTime(flightUpdated.getDepartureTime());
                flight.setPlaneNumber(flightUpdated.getPlaneNumber());
                flight.setAirline(flightUpdated.getAirline());
                flight.setGate(flightUpdated.getGate());
                flight.setStatus(flightUpdated.getStatus());
                flight.setImageUrl(setServerImageUrl(flight));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Flight> getAll() {
        createTestFlights();
        return flightsQueue.stream().toList();
    }

    @Override
    public List<Flight> getAll(int limit) {
        return flightsQueue.stream().toList().subList(0, limit);
    }

    private String setServerImageUrl(Flight flight){
        String[] imageNames = {"airline1.png", "airline2.png", "airline3.png", "airline4.png"};
        int selector = 0;

        if (flight.getAirline().equals(Airline.SINGAPOREAIRLINES)) selector = 1;
        else if (flight.getAirline().equals(Airline.INDIANAIRWAYS)) selector = 2;
        else if (flight.getAirline().equals(Airline.EMIRATES)) selector = 3;

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/FlightManagement/image" + imageNames[selector]).toUriString();
    }

    private void createTestFlights(){
        Flight flight1 = new Flight();
        flight1.setId(1);
        flight1.setDepartureTime(LocalTime.of(16, 50));
        flight1.setPlaneNumber("123");
        flight1.setAirline(Airline.QATARAIRWAYS);
        flight1.setGate("Gate 1");
        flight1.setStatus(Status.PENDING);
        flight1.setImageUrl(setServerImageUrl(flight1));

        Flight flight2 = new Flight();
        flight2.setId(2);
        flight2.setDepartureTime(LocalTime.of(12, 0));
        flight2.setPlaneNumber("123");
        flight2.setAirline(Airline.QATARAIRWAYS);
        flight2.setGate("Gate 1");
        flight2.setStatus(Status.DELAYED);
        flight2.setImageUrl(setServerImageUrl(flight2));

        Flight flight3 = new Flight();
        flight3.setId(3);
        flight3.setDepartureTime(LocalTime.of(12, 0));
        flight3.setPlaneNumber("123");
        flight3.setAirline(Airline.INDIANAIRWAYS);
        flight3.setGate("Gate 2");
        flight3.setStatus(Status.CANCELLED);
        flight3.setImageUrl(setServerImageUrl(flight3));

        create(flight1);
        create(flight2);
        create(flight3);

    }


}
