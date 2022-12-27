package stu.lab5.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab5.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    public List<Airport> findAllByOrderByIdAsc();

    @Override
    public default List<Airport> findAll() {
        return findAllByOrderByIdAsc();
    }

    public List<Airport> findByCityName(String name);

}
