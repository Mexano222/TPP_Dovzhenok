package stu.lab4.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab4.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> findAllByOrderByIdAsc();

    @Override
    public default List<City> findAll() {
        return findAllByOrderByIdAsc();
    }
}
