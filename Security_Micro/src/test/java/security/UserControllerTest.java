package security;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import security.controllers.UserController;
import security.models.User;
import security.security.services.UserService;


@ComponentScan(basePackages = "security")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {UserControllerTest.class})
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {
	
	@Mock
	UserService userService;
	
	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	UserController userController;
	

	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	@Order(1)
	public void test_updateUserData() throws Exception  {
		User user= new User( "Mubarak", "LNU", "Mub", "mubarak@13122000@gmail.com", "yenoondhkodu", "Male","9876543210");
		String id="123";
		when(userService.updateby(id)).thenReturn(user);
		when(userService.save(user)).thenReturn(user.getUsername());
		
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody= mapper.writeValueAsString(user);	
		this.mockMvc.perform(put("/user/updateuser/{id}",id).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
			
	
		
}
	
}
