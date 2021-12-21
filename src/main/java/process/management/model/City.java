package process.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "cities")
public class City {

    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Size(max = 45)
    private String name;

    @Size(max = 45)
    private String country;

    @PositiveOrZero
    private double area;

    @PositiveOrZero
    private double population;

    @PositiveOrZero
    private double GDP;

    @Size(max = 255)
    private String description;

    public City() {
    }

    public City(String name, String country, double area, double population, double GDP, String description) {
        this.name = name;
        this.country = country;
        this.area = area;
        this.population = population;
        this.GDP = GDP;
        this.description = description;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", area=" + area +
                ", population=" + population +
                ", GDP=" + GDP +
                ", description='" + description + '\'' +
                '}';
    }
}
