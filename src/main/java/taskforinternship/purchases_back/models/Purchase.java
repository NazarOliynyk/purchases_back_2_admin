package taskforinternship.purchases_back.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Purchases")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase implements Comparable<Purchase>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    double price;
    Date date;
    @Enumerated(EnumType.STRING)
    CurrencyType currency;

    @ManyToOne(cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY)
    User user;

    @Override
    public int compareTo(Purchase o) {
        return this.getDate().compareTo(o.getDate());
    }
}
