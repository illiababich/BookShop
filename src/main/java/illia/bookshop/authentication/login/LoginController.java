package illia.bookshop.authentication.login;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login", produces = "application/json")
@AllArgsConstructor
public class LoginController {
    public final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> handleLogin(@RequestBody LoginRequest loginBody) {
        return loginService.handleLogin(loginBody);
    }
}
