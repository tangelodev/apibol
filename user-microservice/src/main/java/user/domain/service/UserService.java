package user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.domain.User;
import user.domain.repository.UserRepository;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getUser(String email){
        return this.getUser(email);
    }
    
    public User addUser(User user){
        User savedUser = this.userRepository.addUser(user.getEmail(), user.getNickname());
        return savedUser;
    }

}