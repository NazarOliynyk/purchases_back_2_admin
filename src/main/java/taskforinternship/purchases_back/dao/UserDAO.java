package taskforinternship.purchases_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import taskforinternship.purchases_back.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsByUsername(String username);

}
