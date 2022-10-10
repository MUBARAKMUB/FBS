package FlightBooking.Payment;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import FlightBooking.Controller.OrderController;
import FlightBooking.Model.Order;
import FlightBooking.Model.OrderRequest;
import FlightBooking.Model.OrderResponse;
import FlightBooking.Service.OrderService;

@TestMethodOrder(OrderAnnotation.class)

@ComponentScan(basePackages = "FlightBooking")

@AutoConfigureMockMvc

@ContextConfiguration

@SpringBootTest(classes = { OrderControllerTest.class })
public class OrderControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	OrderService orderService;

	@Autowired
	OrderController orderController;

	@Mock
	Order order;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void test_addOrder() throws Exception {
		Order order = new Order();

		OrderRequest orderrequest = new OrderRequest("Mu123", "mubarak", "mub@gmail.com", "9876543210", "600");
		OrderResponse razorPay = new OrderResponse("60000", "1", "secret");
		when(orderService.saveOrder(razorPay.getRazorpayOrderId(), orderrequest.getUserName())).thenReturn(order);

		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(orderrequest);
		this.mockMvc.perform(post("/api/order").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());

	}

}
