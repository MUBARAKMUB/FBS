package security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import security.Exception.UserNotFoundException;
import security.models.User;
import security.repository.UserRepository;
import security.security.services.UserService;

@SpringBootTest(classes= {UserServiceTest.class})
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	@Test
	@Order(1)
	public void test_updateUser() throws UserNotFoundException{
		Optional<User> user= Optional.of(new User( "Mubarak", "LNU", "Mub", "mubarak@13122000@gmail.com", "yenoondhkodu", "Male","9876543210"));
	String id="1";
		when(userRepository.findById(id)).thenReturn(user);
		assertEquals(user.get(),userService.updateby(id));
	}
	
}
