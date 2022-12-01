package stu.lab3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import stu.lab3.Runner;
import stu.lab3.database.InsertData;
import stu.lab3.util.HibernateUtil;
import stu.lab3.util.Menu;

@Entity
@Table(name = "crew", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Crew implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "job", nullable = false)
    private String job;

    @ManyToMany(mappedBy = "crews")
    @OrderBy("id")
    private Set<Flight> flights = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

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
        System.out.println("\nEnter person name:");
        setName(Runner.sysIn.nextLine());

        System.out.println("\nEnter person job:");
        setJob(Runner.sysIn.nextLine());

        InsertData.insertObject(this);
        System.out.println("Successfully created crew " + toString());
        return this;
    }

    public Crew selectFlight() {
        List<Flight> flights = Flight.select();
        List<String> options = new ArrayList<String>();
        flights.forEach(f -> options.add(f.getNumber()));

        new Menu("Select flights from list:")
                .setOptions(options).takeSelectInput()
                .forEach(i -> addFlight(flights.get(i)));

        System.out.println("Successfully selected crews " + toString());
        return this;
    }
}
