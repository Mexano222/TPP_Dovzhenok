package stu.lab5.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab5.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> findAllByOrderByIdAsc();

    @Override
    public default List<City> findAll() {
        return findAllByOrderByIdAsc();
    }

    public Optional<City> findByName(String name);

}
