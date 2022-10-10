package FlightSearch.Controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import FlightSearch.Exception.FlightNotFoundException;
import FlightSearch.Model.Booking;
import FlightSearch.Model.Flight;
import FlightSearch.Model.FlightData;
import FlightSearch.Service.FlightService;

@RestController
@RequestMapping("/Search")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FlightController {

	@Autowired
	private FlightService fserv;
	
	@Autowired
	RestTemplate resttemplate;


	@GetMapping("/allFlights")
	public List<Flight> getAllFlights() {
		return fserv.getAll();
	}

	@GetMapping("/find/{from}/{to}")
	public List<Flight> getFlightByData(@PathVariable("from") String from, @PathVariable("to") String to) {
		return fserv.findByFromandTo(from, to);
	}

	@GetMapping("/find/{from}/{to}/{departure_Date}")
	public List<Flight> getFlightByAll(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("departure_Date") String departure_Date) {
		return fserv.findByAll(from, to, departure_Date);
	}

	@GetMapping("/allFlightData")
	public List<FlightData> getAllFlightData() {
		return fserv.getFlightData();
	}

	
	@GetMapping("/find/{booking_id}")
	public Booking getBooking(@PathVariable("booking_id") long booking_id) {
		Booking booking = resttemplate.getForObject("http://localhost:8081/booking/"+ booking_id, Booking.class);
		return booking;
	}


	
}
