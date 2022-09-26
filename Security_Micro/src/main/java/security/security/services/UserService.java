package security.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.Exception.UserNotFoundException;
import security.models.User;
import security.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User updateby(String id) throws UserNotFoundException {
		return userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException ("user details not found"));
	}

	public String save(User user) {
		return  userRepository.save(user).getUsername()+": Your profile has been updated!";
	}

	

}
