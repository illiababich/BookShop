package illia.bookshop.order;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private void addBookToCart(Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();

    }
}
