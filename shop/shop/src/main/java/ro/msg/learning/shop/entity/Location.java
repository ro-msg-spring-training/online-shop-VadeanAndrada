package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="location")
public class Location implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="county")
    private String county;

    @Column(name="street_address")
    private String streetAddress;

    @OneToMany(mappedBy = "location")
    @EqualsAndHashCode.Exclude
    private List<Revenue> revenues;

    @OneToMany(mappedBy = "location")
    @EqualsAndHashCode.Exclude
    private List<Stock> stocks;

    @OneToMany(mappedBy = "location")
    @EqualsAndHashCode.Exclude
    private List<Orders> orders;

    public Location(Integer id, String name, String country, String city, String county, String streetAddress) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }
}
