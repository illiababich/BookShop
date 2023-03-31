package illia.bookshop.order;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
}
