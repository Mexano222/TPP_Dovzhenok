package stu.lab4.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab4.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    public List<Airport> findAllByOrderByIdAsc();

    @Override
    public default List<Airport> findAll() {
        return findAllByOrderByIdAsc();
    }
}
