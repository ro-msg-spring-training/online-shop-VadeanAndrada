package ro.msg.learning.shop.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name="revenue")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name="sum")
    private BigDecimal sum;

}
