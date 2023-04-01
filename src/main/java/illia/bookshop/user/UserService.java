package illia.bookshop.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public ResponseEntity<String> deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);

            return new ResponseEntity<>("The user was deleted successfully.", HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>("The user with given ID not found.", HttpStatus.NOT_FOUND);
        }
    }

    public User updateUserById(User userPatch, Long userId) {
        User updatedUser = userRepository.findById(userId).map((user) -> user.toBuilder()
                .username(Optional.ofNullable(userPatch.getUsername()).orElse(user.getUsername()))
                .name(Optional.ofNullable(userPatch.getName()).orElse(user.getName()))
                .type(Optional.ofNullable(userPatch.getType()).orElse(user.getType()))
                .address(Optional.ofNullable(userPatch.getAddress()).orElse(user.getAddress()))
                .zipCode(Optional.ofNullable(userPatch.getZipCode()).orElse(user.getZipCode()))
                .phoneNumber(Optional.ofNullable(userPatch.getPhoneNumber()).orElse(user.getPhoneNumber()))
                .enabled(Optional.ofNullable(userPatch.getEnabled()).orElse(user.getEnabled()))
                .password(null != userPatch.getPassword()
                        ? Optional.ofNullable(new BCryptPasswordEncoder().encode(userPatch.getPassword())).get()
                        : user.getPassword())
                .build()
        ).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "The user with provided ID not found.")
        );

        userRepository.save(updatedUser);
        return updatedUser;
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
