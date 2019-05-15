package taskforinternship.purchases_back.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import taskforinternship.purchases_back.models.ResponseTransfer;
import taskforinternship.purchases_back.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseTransfer saveUser(User user);

    User findOneById(Integer id);

    ResponseTransfer deleteById(int id);
}
