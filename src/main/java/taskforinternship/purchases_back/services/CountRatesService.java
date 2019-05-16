package taskforinternship.purchases_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskforinternship.purchases_back.dao.PurchaseDAO;
import taskforinternship.purchases_back.models.Purchase;
import taskforinternship.purchases_back.restTemplates.MainRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CountRatesService {

    @Autowired
    PurchaseDAO purchaseDAO;
    @Autowired
    MainRestTemplate mainRestTemplate;

    private double sum = 0;

    private double getRate(Purchase purchase){
        double rate = 0;
        try {
           rate= mainRestTemplate.read(purchase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }

    private boolean yearEquals(String year, Purchase purchase){
        String yearFromDate =
                String.valueOf(purchase.getDate().toInstant()).substring(0, 4);
        return yearFromDate.equals(year);
    }

    public double count(String year, int id){
        List<Purchase> purchases = purchaseDAO.findAllByUserId(id);
        List<Purchase> purchasesOfYear = new ArrayList<>();
        purchases.forEach(p->{
            if(yearEquals(year, p)){
                purchasesOfYear.add(p);
            }
        });
        purchasesOfYear.forEach(purchase -> {
            double priceUAH = purchase.getPrice()*getRate(purchase);
            sum+= priceUAH;
        });
        return sum;
    }
}
