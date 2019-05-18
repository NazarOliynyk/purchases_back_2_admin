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


//
//    static ResponseTransferBuilder builder(){
//        return new ResponseTransferBuilder();
//    }
//
//static class ResponseTransferBuilder{
//
//    ResponseTransfer responseTransfer = new ResponseTransfer();
//
//    public ResponseTransferBuilder text(String text){
//        responseTransfer.text = text;
//        return this;
//    }
//    public ResponseTransferBuilder sum(S sum){
//        responseTransfer.sum = sum;
//        return this;
//    }
//    public ResponseTransferBuilder mail(Date date){
//        responseTransfer.date = date;
//        return this;
//    }
//
//    public ResponseTransfer build(){
//        return responseTransfer;
//    }
//
//}

