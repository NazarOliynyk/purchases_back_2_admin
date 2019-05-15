package taskforinternship.purchases_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import taskforinternship.purchases_back.models.Purchase;

import java.util.Date;
import java.util.List;

public interface PurchaseDAO extends JpaRepository<Purchase, Integer> {

    Purchase findByDate(Date date);
    boolean deleteAllByDate(Date date);
    List<Purchase> findAllByUserId(int id);

}
