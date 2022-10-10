package FlightBooking.Payment;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import FlightBooking.Model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import FlightBooking.BookingServiceTest;
import FlightBooking.Model.Flight;
import FlightBooking.Model.OrderRequest;
import FlightBooking.Repository.OrderRepository;
import FlightBooking.Service.OrderService;

@SpringBootTest(classes = { OrderServiceTest.class })
public class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderService orderService;

	@Mock
	Order order;

	@Test
	public void Test_addorder() throws Exception{
		Order order = new Order("1","2","3","4");
//		order.setRazorpayOrderId("hshxbshxbcyc");
//		order.setUserId("1");
		String a="3";
		String b="1";
		when(orderRepository.save(order)).thenReturn(order);
		assertEquals(null, orderService.saveOrder(a,b));
	}

}
