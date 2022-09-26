package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.Exception.UserNotFoundException;
import security.models.User;
import security.security.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/updateuser/{id}")
	public String update(@RequestBody User user, @PathVariable String id)
			throws UserNotFoundException {
		User existuser = userService.updateby(id);
		return  userService.save(user);
		
	}
	

}
