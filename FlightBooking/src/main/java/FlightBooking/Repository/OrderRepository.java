package FlightBooking.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import FlightBooking.Model.Order;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	Order findByRazorpayOrderId(String razorpayOrderId);
}
