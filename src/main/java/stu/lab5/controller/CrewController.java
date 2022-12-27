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

import stu.lab5.model.Crew;
import stu.lab5.repo.CrewRepository;

@RestController
@RequestMapping(value = "/api")
public class CrewController {

    @Autowired
    CrewRepository crewRepository;

    // Display all
    @GetMapping("/crews")
    private List<Crew> findAll() {
        return crewRepository.findAll();
    }

    // Add object
    @PostMapping("/crews")
    private Crew add(@RequestBody Crew crew) {
        return crewRepository.save(crew);
    }

    // Display by id
    @GetMapping("/crews/{id}")
    private Optional<Crew> findById(@PathVariable Long id) {
        return crewRepository.findById(id);
    }

    // Display by flight number
    @GetMapping(value = "/crews", params = "flight")
    private List<Crew> findByFlightNumber(@RequestParam String flight) {
        return crewRepository.findByFlightsNumber(flight);
    }
}
