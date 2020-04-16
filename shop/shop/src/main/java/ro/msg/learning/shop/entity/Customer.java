package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
public class Customer implements Serializable {
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

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
