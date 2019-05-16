package taskforinternship.purchases_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskforinternship.purchases_back.dao.PurchaseDAO;
import taskforinternship.purchases_back.models.Purchase;
import taskforinternship.purchases_back.models.ResponseTransfer;
import taskforinternship.purchases_back.models.User;
import taskforinternship.purchases_back.restTemplates.MainRestTemplate;
import taskforinternship.purchases_back.services.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PurchaseService {

    @Autowired
    PurchaseDAO purchaseDAO;

    @Autowired
    UserServiceImpl userServiceImpl;


    public ResponseTransfer savePurchase(int id, Purchase purchase){

        User user = userServiceImpl.findOneById(id);
        purchase.setUser(user);
        purchaseDAO.save(purchase);
        return new ResponseTransfer("Purchase was saved successfully");
    }

    public List<Purchase> findAllByUserId(int id){
        List<Purchase> purchases = purchaseDAO.findAllByUserId(id);
        Collections.sort(purchases);
        return purchases;
    }

    public ResponseTransfer deleteAllByUserIdAndDate(int id, Date date){

        AtomicInteger counter = new AtomicInteger();
        List<Purchase> purchases = purchaseDAO.findAllByUserId(id);
        purchases.forEach(purchase -> {
            if (purchase.getDate().compareTo(date) == 0){
                counter.getAndIncrement();
                purchaseDAO.delete(purchase);
            }
        });
        if(counter.intValue()>0){
            return new ResponseTransfer(
                    "There were deleted: "+counter.intValue()+" purchases");
        }else {
            return new ResponseTransfer("No purchases of this date!");
        }
    }

}
