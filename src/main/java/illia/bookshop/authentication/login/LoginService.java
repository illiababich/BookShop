package illia.bookshop.authentication.login;

import illia.bookshop.order.Order;
import illia.bookshop.order.OrderRepository;
import illia.bookshop.order.OrderStatus;
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
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<LoginResponse> handleLogin(LoginRequest loginBody) {
        try {
            User optionalUser = userRepository.findByUsername(loginBody.getUsername()).orElseThrow();

            if (passwordEncoder.matches(loginBody.getPassword(), optionalUser.getPassword())) {
                LoginResponse loginResponse = new LoginResponse(manageLogin(loginBody.getUsername(), loginBody.getPassword()));
                createEmptyCart(optionalUser.getId());

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

    private void createEmptyCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
        orderRepository.save(new Order(user, OrderStatus.HANDING));
    }
}
