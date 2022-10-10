package FlightBooking.Payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import FlightBooking.Model.Order;
import FlightBooking.Model.OrderRequest;
import FlightBooking.Model.OrderResponse;
import FlightBooking.Repository.OrderRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void saveorder() {
		Order order=new Order();
		order.setUserId("1");
		order.setRazorpayOrderId("1");
		Order o= orderRepository.save(order);
		assertEquals(order.getRazorpayOrderId(), o.getRazorpayOrderId());
		
	}
}
