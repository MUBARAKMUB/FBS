package security;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import security.controllers.AuthController;
import security.models.ERole;
import security.models.Role;
import security.models.User;
import security.payload.request.SignupRequest;
import security.payload.response.MessageResponse;
import security.repository.RoleRepository;
import security.repository.UserRepository;

@ComponentScan(basePackages = "security")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { AuthControllerTest.class })
@TestMethodOrder(OrderAnnotation.class)
public class AuthControllerTest {

	@Mock
	UserRepository userRepository;

	@Mock
	RoleRepository roleRepository;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	MockMvc mockMvc;

	@Mock
	SignupRequest signUpRequest;

	@Mock
	MessageResponse messageResponse;

	@InjectMocks
	AuthController authController;

//	@MockBean
//	ERole erole;
//	
//	@MockBean
//	Role roles;

	@Test
	@Order(1)
	public void test_Signup() throws Exception {
		when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
		when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);
		Optional<Role> roles = Optional.of(new Role(ERole.ROLE_USER));
		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(roles);
		User user = new User("Mubarak", "LNU", "Mub", "mubarak@13122000@gmail.com", "yenoondhkodu", "Male",
				"9876543210");
		when(userRepository.save(user)).thenReturn(user);
		MessageResponse messageResponse = new MessageResponse("User registered successfully!");
		assertEquals(ResponseEntity.ok(messageResponse).getStatusCode(),
				authController.registerUser(signUpRequest).getStatusCode());

	}

}
