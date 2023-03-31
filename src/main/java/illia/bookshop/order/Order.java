package illia.bookshop.order;

import illia.bookshop.book.Book;
import illia.bookshop.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;

    @OneToOne
    private User customer;

    @OneToMany
    private List<Book> orderedItems;

    private OrderStatus orderStatus;
}
