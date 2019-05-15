package taskforinternship.purchases_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskforinternship.purchases_back.dao.PurchaseDAO;
import taskforinternship.purchases_back.models.Purchase;
import taskforinternship.purchases_back.models.ResponseTransfer;
import taskforinternship.purchases_back.models.User;
import taskforinternship.purchases_back.services.impl.UserServiceImpl;

import java.util.Date;
import java.util.List;

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

        return purchaseDAO.findAllByUserId(id);
    }

    public ResponseTransfer deleteByDate(Date date){
        boolean responseOnDelete = purchaseDAO.deleteAllByDate(date);
        if(responseOnDelete){
            return new ResponseTransfer("All purchases from: "+date.toString()+" were deleted");
        }else {
            return new ResponseTransfer("There are no purchases from: "+date.toString());
        }
    }
}
