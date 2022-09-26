package security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;

import security.models.Role;
import security.models.User;
import security.repository.UserRepository;
import security.security.services.UserDetailsImpl;
import security.security.services.UserDetailsServiceImpl;

@SpringBootTest(classes= {SecurityServiceTest.class})
public class SecurityServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	UserDetailsImpl userDetailsImpl;
	
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	@Test
	public void Test_loadUserByUsername() {

		Optional<User> user= Optional.of(new User( "Mubarak", "LNU", "Mub", "mubarak@13122000@gmail.com", "yenoondhkodu", "Male", "9876543210"));
		User user1= new User( "Mubarak", "LNU", "Mub", "mubarak@13122000@gmail.com", "yenoondhkodu", "Male","9876543210");
		String username="Mub";
		when(userRepository.findByUsername(username)).thenReturn(user);
		assertEquals(UserDetailsImpl.build(user1), userDetailsServiceImpl.loadUserByUsername(username));
	}
	
	
	
	
	
	
	
	
	
	

}
