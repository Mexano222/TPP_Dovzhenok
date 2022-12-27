package stu.lab5.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab5.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    public List<Flight> findAllByOrderByIdAsc();

    @Override
    public default List<Flight> findAll() {
        return findAllByOrderByIdAsc();
    }

    public List<Flight> findByCrewsJob(String number);

}
