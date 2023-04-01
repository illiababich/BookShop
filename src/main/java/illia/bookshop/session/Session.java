package illia.bookshop.session;

import illia.bookshop.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "SESSION")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private User activeUser;
}
