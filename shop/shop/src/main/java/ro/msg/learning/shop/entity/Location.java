package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="location")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Location {
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

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Revenue> revenues;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Order> orders;

}
