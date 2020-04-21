package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="orders")
@NoArgsConstructor
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="created_at")
    private LocalDateTime createAt;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="county")
    private String county;

    @Column(name="street_address")
    private String streetAddress;

   @ManyToOne
   @JoinColumn(name="shipped_form", referencedColumnName = "id")
   @EqualsAndHashCode.Exclude
    private Location location;

   @ManyToOne
   @JoinColumn(name = "customer", referencedColumnName = "id")
   @EqualsAndHashCode.Exclude
    private Customer customer;

   @OneToMany(mappedBy = "orders")
   @EqualsAndHashCode.Exclude
    private List<OrderDetail> orderDetails;

    public Orders(Integer id, LocalDateTime createAt, String country, String city, String county, String streetAddress, Location location, Customer customer) {
        this.id = id;
        this.createAt = createAt;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
        this.location = location;
        this.customer = customer;
    }
}
