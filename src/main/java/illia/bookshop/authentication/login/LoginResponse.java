package illia.bookshop.authentication.login;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String bearer;

    public LoginResponse(String bearer) {
        this.bearer = bearer;
    }
}
