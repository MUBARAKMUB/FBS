package CheckIn.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import CheckIn.Exception.BusinessException;
import CheckIn.Exception.CustomException;
import CheckIn.Model.Booking;
import CheckIn.Model.Flight;
import CheckIn.Model.FlightData;
import CheckIn.Model.Passenger;

@RestController
@RequestMapping("checkin")
public class CheckinController {

	@Autowired
	private RestTemplate resttemplate;

	
	@PutMapping("/booking/{booking_id}")
	/* @HystrixCommand(fallbackMethod = "UpdateBookingFallback") */
	public String updateBooking(Booking booking, @PathVariable("booking_id") long pnr) throws InterruptedException {
		booking.setChecked_in(true); 
		resttemplate.put("http://localhost:8081/booking/booking/"+ pnr , booking);
		return"Checked In Successfully:"+pnr ; 
		

	}

}
