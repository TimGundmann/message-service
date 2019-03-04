package dk.gundmann.userclient;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountCredentials {

    private String username;
    private String password;

}
