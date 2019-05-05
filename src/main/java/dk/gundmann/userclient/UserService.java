package dk.gundmann.userclient;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;

@Service
public class UserService {

    private UserClient userClinet;
    private AuthenticationResolver authenticationResolver;

    UserService(UserClient userClinet, AuthenticationResolver authenticationResolver) {
        this.userClinet = userClinet;
        this.authenticationResolver = authenticationResolver;
    }

    public void notifiy(String type) {
        try {
            this.userClinet.notifiy(type);
        } catch (ExpiredJwtException ex) {
            this.authenticationResolver.updateToken();
            this.userClinet.notifiy(type);
        }
    }
	
}