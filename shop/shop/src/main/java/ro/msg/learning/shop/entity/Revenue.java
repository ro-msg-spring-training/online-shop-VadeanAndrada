package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="revenue")
@Data
@NoArgsConstructor
public class Revenue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;

    @Column(name = "datee")
    @Temporal(TemporalType.DATE)
    private Date localDate;

    @Column(name="sum")
    private BigDecimal sum;

}
