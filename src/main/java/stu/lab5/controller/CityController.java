package stu.lab5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stu.lab5.model.City;
import stu.lab5.repo.CityRepository;

@RestController
@RequestMapping(value = "/api")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    // Display all
    @GetMapping("/cities")
    private List<City> findAll() {
        return cityRepository.findAll();
    }

    // Add object
    @PostMapping("/cities")
    private City add(@RequestBody City city) {
        return cityRepository.save(city);
    }

    // Display by id
    @GetMapping("/cities/{id}")
    private Optional<City> findById(@PathVariable Long id) {
        return cityRepository.findById(id);
    }

}
