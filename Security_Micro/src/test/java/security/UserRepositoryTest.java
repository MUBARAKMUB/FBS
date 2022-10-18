package security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import security.models.ERole;
import security.models.Role;
import security.models.User;
import security.payload.request.SignupRequest;
import security.repository.RoleRepository;
import security.repository.UserRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Mock
	SignupRequest signupRequest;
	
	@Test
	@Order(1)
	public void registerUser() {
		
	Boolean a=	userRepository.existsByUsername("Mu123");
	Boolean b= userRepository.existsByEmail("mubarak13122000@gmail.com");
	if(a==false&&b==false) {
		Role role= new Role(ERole.ROLE_USER) ;
		Set<Role> rol= new HashSet<>();
		rol.add(role);
	User user = new User("Mubarak", "LN", "Mu123", "mubarak13122000@gmail.com", "yenoondhkodu", "Male","9876543210");
	user.setRoles(rol);	
	roleRepository.save(role);
	userRepository.save(user);
	}
	else {
		System.out.println("UserName or UserEmail already exists");
	}
		
	}
	
//	@Test
//	@Order(2)
//	public void updateUser() {
//	Optional<User>	a=userRepository.findById("6332eb3c01cff928b892c190");
//		User user = new User("6332eb3c01cff928b892c190","Mubarak", "LN", "Mu123", "mubarak13122000@gmail.com", "yenoondhkodu", "Male","9876543210");
//		userRepository.save(user);
//		assertEquals(a.get().getUsername(), user.getUsername());
//		
//	}
	
	
	
	
	
	
	
}
