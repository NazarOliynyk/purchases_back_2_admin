package taskforinternship.purchases_back.restTemplates;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import taskforinternship.purchases_back.models.CurrencyType;
import taskforinternship.purchases_back.models.Purchase;
import java.io.IOException;

@Component
public class MainRestTemplate {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public double read(Purchase purchase, CurrencyType currency)
            throws IOException {

        String dateSubstring = String.valueOf(purchase.getDate().toInstant()).substring(0, 10);

        String urlFixer
                = "http://data.fixer.io/api/" + dateSubstring +
                "?access_key=229d1da7b736ef77d158ea0c224c4344" +
                "&symbols=USD,EUR,PLN,UAH";
        ResponseEntity<String> response
                = restTemplate.getForEntity(urlFixer , String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode rates = root.path("rates");

        double uah = Double.parseDouble(rates.path("UAH").asText());
        double usd = Double.parseDouble(rates.path("USD").asText());
        double pln = Double.parseDouble(rates.path("PLN").asText());

        double rate = 0;

        switch (purchase.getCurrency()){
            case EUR:
                switch (currency){
                    case EUR: rate = 1; break;
                    case UAH: rate = uah; break;
                    case PLN: rate =  pln; break;
                    case USD: rate =  usd; break;
                }break;
            case UAH:
                switch (currency){
                    case UAH: rate = 1.0; break;
                    case EUR: rate = 1/uah; break;
                    case USD: rate = usd/uah; break;
                    case PLN: rate = pln/uah; break;
                }break;
            case USD:
                switch (currency){
                    case USD: rate = 1; break;
                    case UAH: rate = uah/usd; break;
                    case EUR: rate = 1/usd; break;
                    case PLN: rate = pln/usd; break;
                }break;
            case PLN:
                switch (currency){
                    case PLN: rate = 1; break;
                    case EUR: rate = 1/pln; break;
                    case UAH: rate = uah/pln; break;
                    case USD: rate = usd/pln; break;
                }break;
        }
        return rate;
    }

}
