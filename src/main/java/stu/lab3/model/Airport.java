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
import jakarta.persistence.ManyToOne;
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
@Table(name = "airport", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "iata_code", nullable = false, length = 3, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "city", nullable = false)
    @OrderBy("id")
    private City city;

    @Override
    public String toString() {
        return "[" + getId() + "] " + getName() + " (" + getCode() + ")";
    }

    public static List<Airport> select() {
        return HibernateUtil.getEntityManager()
                .createQuery("from Airport order by id", Airport.class)
                .getResultList();
    }

    public Airport create() {
        setName(Menu.takeSimpleInput("Enter airport name:"));
        setCode(Menu.takeSimpleInput("Enter airport IATA code:"));

        // Get list of all cities
        List<City> cities = City.select();
        List<String> options = new ArrayList<>();
        cities.forEach(c -> options.add(c.getName()));
        options.add("Create new");

        int selected = new Menu("Select city from list:").setOptions(options).takeOneInput();
        if (selected < options.size() - 1) {
            setCity(cities.get(selected));
        } else {
            setCity(new City().create());
        }

        InsertData.insertObject(this);
        System.out.println("Successfully created airport " + toString());
        return this;
    }

}
