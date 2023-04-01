package illia.bookshop.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user.isPresent()) {
            authorities.add(new SimpleGrantedAuthority(user.get().getType().toString()));

            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                    user.get().getPassword(), authorities);
        }

        return null;
    }
}
