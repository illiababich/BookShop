package illia.bookshop.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        return userService.deleteUserById(userId);
    }

    @PatchMapping(path = "/{userId}", consumes = "application/json")
    public User updateUserById(@RequestBody User user, @PathVariable Long userId) {
        return userService.updateUserById(user, userId);
    }
}
