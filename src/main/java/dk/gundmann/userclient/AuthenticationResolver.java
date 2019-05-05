package dk.gundmann.userclient;

import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import dk.gundmann.security.SecurityConfig;

@Component
public class AuthenticationResolver {

	private UserClient userClient;

	private SecurityConfig properties;

	private Token token;

	public AuthenticationResolver(SecurityConfig properties, UserClient userClient, Token token) {
		this.properties = properties;
		this.userClient = userClient;
		this.token = token;
	}

	@PostConstruct
	public void updateToken() {
		try {
			if (token.getToken() == null) {
				token.setToken(userClient
						.login(AccountCredentials.builder().username("sys@gundmann.dk")
								.password(btoa(properties.getSyspassword())).build())
						.getHeaders().get(properties.getHeaderString()).get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String btoa(String base64) {
		return new String(Base64.getEncoder().encode(base64.getBytes()));
	}

}
