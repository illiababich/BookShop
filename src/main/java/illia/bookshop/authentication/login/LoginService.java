package illia.bookshop.authentication.login;

import illia.bookshop.security.TokenService;
import illia.bookshop.user.User;
import illia.bookshop.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<LoginResponse> handleLogin(LoginRequest loginBody) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(loginBody.getEmail());

            if (passwordEncoder.matches(loginBody.getPassword(), optionalUser.get().getPassword())) {
                LoginResponse loginResponse = new LoginResponse(manageLogin(loginBody.getEmail(), loginBody.getPassword()));
                return new ResponseEntity<>(loginResponse, HttpStatus.OK);
            }

            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Provided password is invalid");
        } catch (NullPointerException | NoSuchElementException exception) {
            System.out.println("something went wrong");
        }

        return null;
    }

    private String manageLogin(String email, String password) {
        try {
            return tokenService.generateToken(authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)));
        } catch (BadCredentialsException exception) {
            System.out.println("something went wrong");
        }
        return email;
    }
}
