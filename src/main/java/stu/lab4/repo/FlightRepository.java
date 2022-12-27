package stu.lab4.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab4.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    public List<Flight> findAllByOrderByIdAsc();

    @Override
    public default List<Flight> findAll() {
        return findAllByOrderByIdAsc();
    }
}
