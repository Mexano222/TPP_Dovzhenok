package stu.lab4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crew", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
public class Crew implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String job;

    @ManyToMany(targetEntity = Flight.class, cascade = CascadeType.ALL)
    @JoinTable(name = "flight_crew", joinColumns = @JoinColumn(name = "crew_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"))
    @OrderBy("id")
    private List<Flight> flights = new ArrayList<>();

    @Override
    public String toString() {
        return "[" + getId() + "] " + getName() + " (" + getJob() + ")";
    }

}
