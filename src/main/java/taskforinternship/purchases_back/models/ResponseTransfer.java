package taskforinternship.purchases_back.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseTransfer {

    String text;
    double price;
    Date date;

    public ResponseTransfer(String text) {
        this.text = text;
    }

    public ResponseTransfer(Date date) {
        this.date = date;
    }

    public ResponseTransfer(double price) {
        this.price = price;
    }
}
