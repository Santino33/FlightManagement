package co.edu.uptc.FlightManagement.controller;

import co.edu.uptc.FlightManagement.model.Flight;
import co.edu.uptc.FlightManagement.model.Response;
import co.edu.uptc.FlightManagement.service.implementation.FlightServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightsController {
    private final FlightServiceImpl flightService;



    @GetMapping("/list/{limit}")
    public ResponseEntity<Response> getFlights(@PathVariable int limit){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("flights", flightService.getAll(limit)))
                        .message("Flights retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).
                        build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getFlights(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("flights", flightService.getAll()))
                        .message("Flights retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).
                        build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveFlight(@RequestBody @Valid Flight flight){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("flights", flightService.create(flight)))
                        .message("Flight created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value()).
                        build()
        );
    }

    @DeleteMapping("/pop")
    public ResponseEntity<Response> deleteFlight(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("flights", flightService.delete()))
                        .message("Flight in row deleted")
                        .status(HttpStatus.FOUND)
                        .statusCode(HttpStatus.FOUND.value()).
                        build()
        );
    }

    @PutMapping("/modify")
    public ResponseEntity<Response> updateFlight(@RequestBody @Valid Flight flight){
        boolean result = false;
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("flights", result = flightService.update(flight.getId(), flight)))
                        .message(result ? "Flight updated successfully" : "Unable to find the flight to update")
                        .status(result ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                        .statusCode(result ? HttpStatus.FOUND.value() : HttpStatus.NOT_FOUND.value()).
                        build()
        );
    }

}
