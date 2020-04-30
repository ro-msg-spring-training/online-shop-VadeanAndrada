package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email_address")
    private String emailAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

}
