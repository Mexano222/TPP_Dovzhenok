package stu.lab3.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import stu.lab3.Runner;
import stu.lab3.database.InsertData;
import stu.lab3.util.HibernateUtil;

@Entity
@Table(name = "city", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    @OrderBy("id")
    Set<Airport> airports;

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

    public Set<Airport> getAirports() {
        return airports;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public String toString() {
        return "[" + getId() + "] " + getName();
    }

    public static List<City> select() {
        return HibernateUtil.getEntityManager()
                .createQuery("from City order by id", City.class)
                .getResultList();
    }

    public City create() {
        System.out.println("\nEnter city name:");
        setName(Runner.sysIn.nextLine());

        InsertData.insertObject(this);
        System.out.println("Successfully created city " + toString());
        return this;
    }

}
