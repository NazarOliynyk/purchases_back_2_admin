package taskforinternship.purchases_back.restTemplates;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.decimal4j.util.DoubleRounder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import taskforinternship.purchases_back.models.CurrencyType;
import taskforinternship.purchases_back.models.Purchase;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

@Component
public class MainRestTemplate {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public double read(Purchase purchase) throws IOException {

        String dateSubstring = String.valueOf(purchase.getDate().toInstant()).substring(0, 10);

        String urlFixer
                = "http://data.fixer.io/api/" + dateSubstring +
                "?access_key=229d1da7b736ef77d158ea0c224c4344" +
                "&symbols=USD,EUR,PLN,UAH";
        ResponseEntity<String> response
                = restTemplate.getForEntity(urlFixer , String.class);
        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode base = root.path("base");
        JsonNode rates = root.path("rates");
//        JsonNode date1 = root.path("date");
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);

        double eur = Double.parseDouble(rates.path("UAH").asText());
        // double usd = DoubleRounder.round(eur / Double.parseDouble(rates.path("USD").asText()), 6);
       // double pln = DoubleRounder.round(eur / Double.parseDouble(rates.path("PLN").asText()), 6);

        if(purchase.getCurrency().equals(CurrencyType.EUR)){
            return eur;
        }else if (purchase.getCurrency().equals(CurrencyType.USD)){
            return DoubleRounder.round(eur / Double.parseDouble(rates.path("USD").asText()), 6);
        }else if(purchase.getCurrency().equals(CurrencyType.PLN)){
            return DoubleRounder.round(eur / Double.parseDouble(rates.path("PLN").asText()), 6);
        }else {
            return 1.0;
        }
    }

}
