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

import stu.lab5.model.Airport;
import stu.lab5.repo.AirportRepository;

@RestController
@RequestMapping(value = "/api")
public class AirportController {

    @Autowired
    AirportRepository airportRepository;

    // Display all
    @GetMapping("/airports")
    private List<Airport> findAll() {
        return airportRepository.findAll();
    }

    // Add object
    @PostMapping("/airports")
    private Airport add(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    // Display by id
    @GetMapping("/airports/{id}")
    private Optional<Airport> findById(@PathVariable Long id) {
        return airportRepository.findById(id);
    }

    // Display by city name
    @GetMapping(value = "/airports", params = "city")
    private List<Airport> findByCityName(@RequestParam String city) {
        return airportRepository.findByCityName(city);
    }
}
