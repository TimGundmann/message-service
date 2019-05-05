package dk.gundmann.userclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user", url = "${user.url}")
interface UserClient {

	@RequestMapping(method = RequestMethod.POST, value = "/notification/{type}")
    void notifiy(@PathVariable String type);
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	ResponseEntity<Void> login(@RequestBody AccountCredentials user);
	
}
