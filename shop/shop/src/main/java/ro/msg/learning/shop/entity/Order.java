package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Order implements Comparable<Order> {
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

   @ManyToOne(cascade = CascadeType.MERGE)
   @JoinColumn(name="shipped_form", referencedColumnName = "id")
    private Location location;

   @ManyToOne(cascade = CascadeType.MERGE)
   @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

   @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

   public String toString (){
       return this.id + " " + this.city + " " + this.country + " " + this.county + " " + this.createAt + " " + this.customer.getId() + " " + this.location.getId();
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return
                Objects.equals(createAt, order.createAt) &&
                Objects.equals(country, order.country) &&
                Objects.equals(city, order.city) &&
                Objects.equals(county, order.county) &&
                Objects.equals(streetAddress, order.streetAddress)&&
                Objects.equals(location.getId(), order.location.getId())&&
                Objects.equals(customer.getId(), order.customer.getId());
    }

    @Override
    public int compareTo(Order o) {
        if (getLocation() == null || o.getLocation() == null) {
            return 0;
        }
        return getLocation().getId().compareTo(o.getLocation().getId());
    }
}
