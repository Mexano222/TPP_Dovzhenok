package stu.lab5.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "flight", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight", nullable = false, length = 8)
    private String number;

    @ManyToOne
    @JoinColumn(name = "from_airport", nullable = false)
    @JsonIgnoreProperties("city")
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name = "to_airport", nullable = false)
    @JsonIgnoreProperties("city")
    private Airport toAirport;

    @ManyToMany(targetEntity = Crew.class, cascade = CascadeType.ALL)
    @JoinTable(name = "flight_crew", joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "crew_id", referencedColumnName = "id"))
    @OrderBy("id")
    @JsonIgnore
    private List<Crew> crews;

}
