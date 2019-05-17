package taskforinternship.purchases_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskforinternship.purchases_back.models.Purchase;
import taskforinternship.purchases_back.models.ResponseTransfer;
import taskforinternship.purchases_back.models.User;
import taskforinternship.purchases_back.services.CountRatesService;
import taskforinternship.purchases_back.services.PurchaseService;
import taskforinternship.purchases_back.services.impl.UserServiceImpl;

import java.util.Date;
import java.util.List;

@RestController
public class MainRestController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    CountRatesService countRatesService;

    @CrossOrigin(origins = "*")
    @PostMapping("/saveUser")
    public ResponseTransfer saveUser(@RequestBody User user){

        return userServiceImpl.saveUser(user);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseTransfer deleteUser(@PathVariable("id") int id) {

        return userServiceImpl.deleteById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/savePurchase/{id}")
    public ResponseTransfer savePurchase(@PathVariable("id") int id,
                                         @RequestBody Purchase purchase){
        return purchaseService.savePurchase(id, purchase);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getPurchases/{id}")
    public List<Purchase> getPurchases(@PathVariable("id") int id){

        return purchaseService.findAllByUserId(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/deleteByDate/{id}")
    public ResponseTransfer deleteByDate(@PathVariable("id") int id,
                                         @RequestBody ResponseTransfer responseTransfer){
        Date date = responseTransfer.getDate();
        return purchaseService.deleteAllByUserIdAndDate(id, date);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/report/{id}")
    public ResponseTransfer report(@PathVariable("id") int id,
                           @RequestBody ResponseTransfer responseTransfer){
        String year = responseTransfer.getText();
        return new ResponseTransfer(countRatesService.count(year, id));
    }
}
