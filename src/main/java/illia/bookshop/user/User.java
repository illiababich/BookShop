package illia.bookshop.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private UserType type;
    private String address;
    private int zipCode;
    private String phoneNumber;

    public User(String email, String password, UserType type, String address, int zipCode, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }
}
