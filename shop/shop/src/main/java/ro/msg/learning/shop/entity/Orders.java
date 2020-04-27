package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="order")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Orders {
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
    private Location location;

   @ManyToOne
   @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

   @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails;

}
