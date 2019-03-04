package dk.gundmann.userclient;

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
	public void getToken() {
		try {
			if (token.getToken() == null) {
				token.setToken(userClient
						.login(AccountCredentials.builder().username("sys@gundmann.dk")
								.password(properties.getSyspassword()).build())
						.getHeaders().get(properties.getHeaderString()).get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
