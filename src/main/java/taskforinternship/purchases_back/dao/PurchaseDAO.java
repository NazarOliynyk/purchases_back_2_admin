package taskforinternship.purchases_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import taskforinternship.purchases_back.models.Purchase;
import taskforinternship.purchases_back.models.User;

import java.util.Date;
import java.util.List;

public interface PurchaseDAO extends JpaRepository<Purchase, Integer> {

    List<Purchase> findAllByUserId(int id);

}
