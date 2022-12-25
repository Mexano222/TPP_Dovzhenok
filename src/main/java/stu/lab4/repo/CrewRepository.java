package stu.lab4.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.lab4.model.Crew;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    public List<Crew> findAllByOrderByIdAsc();

    @Override
    public default List<Crew> findAll() {
        return findAllByOrderByIdAsc();
    }
}
