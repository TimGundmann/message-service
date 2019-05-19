package dk.gundmann.userclient;

import org.springframework.stereotype.Service;

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
        } catch (Exception ex) {
            this.authenticationResolver.updateToken();
            this.userClinet.notifiy(type);
        }
    }
	
}