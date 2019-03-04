package dk.gundmann.userclient;

import org.springframework.stereotype.Component;

import dk.gundmann.security.SecurityConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class AuthRequestInterceptor implements RequestInterceptor {

	private SecurityConfig properties;

	private Token token;

	public AuthRequestInterceptor(SecurityConfig properties, Token token) {
		this.properties = properties;
		this.token = token;
		
	}

	@Override
	public void apply(RequestTemplate template) {
		if (token.getToken() != null) {
			template.header(properties.getHeaderString(), token.getToken());
		}
	}


}
