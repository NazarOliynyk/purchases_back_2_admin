package taskforinternship.purchases_back.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskforinternship.purchases_back.dao.UserDAO;
import taskforinternship.purchases_back.models.ResponseTransfer;
import taskforinternship.purchases_back.models.User;
import taskforinternship.purchases_back.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseTransfer saveUser(User user) {

        if (user.getUsername().equals("admin") ||
                userDAO.existsByUsername(user.getUsername())) {
            return new ResponseTransfer<>("User with such login already exists!!");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.save(user);
            return new ResponseTransfer<>("User was saved successfully.");
        }
    }

    @Override
    public User findOneById(Integer id) {
        return userDAO.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public ResponseTransfer deleteById(int id) {
        userDAO.deleteById(id);
        return new ResponseTransfer<>("Account was deleted successfully");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }
}
