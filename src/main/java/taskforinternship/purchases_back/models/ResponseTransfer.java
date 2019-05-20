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
public class ResponseTransfer <S>{

    S text;
    Date date;
    CurrencyType currency;
    public ResponseTransfer(S text) {
        this.text = text;
    }

    public ResponseTransfer(CurrencyType currency) {
        this.currency = currency;
    }
}


