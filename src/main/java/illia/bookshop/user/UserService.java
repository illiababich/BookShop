package illia.bookshop.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return null;
    }

    public ResponseEntity<String> createUser(User user) {
        return null;
    }

    public ResponseEntity<String> deleteUserById(Long userId) {
        return null;
    }

    public User updateUserById(User user, Long userId) {
        return null;
    }
}
