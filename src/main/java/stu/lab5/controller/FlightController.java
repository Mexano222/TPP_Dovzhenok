package stu.lab5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stu.lab5.model.Flight;
import stu.lab5.repo.FlightRepository;

@RestController
@RequestMapping(value = "/api")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    // Display all
    @GetMapping("/flights")
    private List<Flight> findAll() {
        return flightRepository.findAll();
    }

    // Add object
    @PostMapping("/flights")
    private Flight add(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    // Display by id
    @GetMapping("/flights/{id}")
    private Optional<Flight> findById(@PathVariable Long id) {
        return flightRepository.findById(id);
    }

    // Display by crew job
    @GetMapping(value = "/flights", params = "crew_job")
    private List<Flight> findByAirportName(@RequestParam String crew_job) {
        return flightRepository.findByCrewsJob(crew_job);
    }
}
