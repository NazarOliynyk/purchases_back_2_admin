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

    private JsonNode rates;

    private double getRateFromJson(CurrencyType currencyType){
        return Double.parseDouble(rates.path(currencyType.name()).asText());
    }

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
        rates = root.path("rates");

        return getRateFromJson(currency)/getRateFromJson(purchase.getCurrency());
    }

}
