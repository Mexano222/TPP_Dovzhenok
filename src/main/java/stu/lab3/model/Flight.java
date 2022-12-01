package stu.lab3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import stu.lab3.Runner;
import stu.lab3.database.InsertData;
import stu.lab3.util.HibernateUtil;
import stu.lab3.util.Menu;

@Entity
@Table(name = "flight", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "flight", nullable = false, length = 8)
    private String number;

    @ManyToOne
    @JoinColumn(name = "from_airport", nullable = false)
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name = "to_airport", nullable = false)
    private Airport toAirport;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "flight_crew", joinColumns = { @JoinColumn(name = "flight_id") }, inverseJoinColumns = {
            @JoinColumn(name = "crew_id") })
    @OrderBy("id")
    Set<Crew> crews = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public Set<Crew> getCrews() {
        return crews;
    }

    public void setCrews(Set<Crew> crews) {
        this.crews = crews;
    }

    public void addCrew(Crew crew) {
        this.crews.add(crew);
    }

    @Override
    public String toString() {
        return "[" + getId() + "] "
                + getNumber() + " - "
                + getFromAirport().getCity().getName() + "("
                + getFromAirport().getCode() + ") => "
                + getToAirport().getCity().getName() + "("
                + getToAirport().getCode() + ")";
    }

    public static List<Flight> select() {
        return HibernateUtil.getEntityManager()
                .createQuery("from Flight order by id", Flight.class)
                .getResultList();
    }

    public Flight create() {
        System.out.println("\nEnter flight number:");
        setNumber(Runner.sysIn.nextLine());

        // Get list of all airports
        List<Airport> airports = Airport.select();

        Menu airportSelect = new Menu();
        airports.forEach(a -> airportSelect.addOption(a.getName()));

        setFromAirport(airports.get(
                airportSelect.setTitle("Select departure airport from list:").takeOneInput()));

        setToAirport(airports.get(
                airportSelect.setTitle("Select arrival airport from list:").takeOneInput()));

        InsertData.insertObject(this);
        System.out.println("Successfully created flight " + toString());
        return this;
    }

    public Flight selectCrews() {
        List<Crew> crews = Crew.select();
        List<String> options = new ArrayList<String>();
        crews.forEach(c -> options.add(c.getName()));

        new Menu("Select crews from list:")
                .setOptions(options).takeSelectInput()
                .forEach(i -> addCrew(crews.get(i)));

        System.out.println("Successfully selected crews " + toString());
        return this;
    }
}