package stu.lab3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
import stu.lab3.database.InsertData;
import stu.lab3.util.HibernateUtil;
import stu.lab3.util.Menu;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crew", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
public class Crew implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "job", nullable = false)
    private String job;

    @ManyToMany
    @JoinTable(name = "flight_crew", joinColumns = {
        @JoinColumn(name = "crew_id")}, inverseJoinColumns = {
        @JoinColumn(name = "flight_id")})
    @OrderBy("id")
    private List<Flight> flights = new ArrayList<>();

    @Override
    public String toString() {
        return "[" + getId() + "] " + getName() + " (" + getJob() + ")";
    }

    public static List<Crew> select() {
        return HibernateUtil.getEntityManager()
                .createQuery("from Crew order by id", Crew.class)
                .getResultList();
    }

    public Crew create() {
        setName(Menu.takeSimpleInput("Enter person name:"));
        setJob(Menu.takeSimpleInput("Enter person job:"));

        InsertData.insertObject(this);
        System.out.println("Successfully created crew " + toString());
        return this;
    }

    public Crew selectFlight() {
        List<Flight> allFlights = Flight.select();
        List<String> options = new ArrayList<>();
        allFlights.forEach(f -> options.add(f.getNumber()));

        new Menu("Select flights from list:")
                .setOptions(options).takeSelectInput()
                .forEach(i -> getFlights().add(allFlights.get(i)));

        System.out.println("Successfully selected flights for " + toString());
        return this;
    }
}
