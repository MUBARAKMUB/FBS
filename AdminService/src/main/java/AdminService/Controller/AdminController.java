package AdminService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import AdminService.Exception.FlightNotFoundException;
import AdminService.Model.Booking;
import AdminService.Model.Flight;
import AdminService.Model.FlightData;
import AdminService.Model.User;
import AdminService.Service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

	@Autowired
	RestTemplate resttemplate;

	@Autowired
	AdminService fserv;

	@GetMapping("/allflights")
	public Flight[] getAllFlights() {
		Flight[] flight = resttemplate.getForObject("http://localhost:8080/Search/allFlights", Flight[].class);
		return flight;
	}
	
//	@GetMapping("/{id}/allflights")
//	public User getUser(@PathVariable ("id") String id) {
//		User use=resttemplate.getForObject("http://localhost:8079/api/auth/"+id,User.class);
//		return use;
//	}
//	public Flight[] getAllFlights() {
//		Flight[] flight = resttemplate.getForObject("http://localhost:8080/Search/allFlights", Flight[].class);
//		return flight;
//	}
	
	

	@GetMapping("/access/{from}/{to}/{departure_Date}")
	public Flight[] getFlightByAll(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("departure_Date") String departure_Date) {
		Flight[] flight = resttemplate.getForObject("http://localhost:8080/Search/find/" + from + "/" + to + "/" + departure_Date, Flight[].class);
		return flight;
	}

	

	@PutMapping("/allFlightData/{flight_id}")
	public String updatebyFlightData(@RequestBody FlightData flightdata, @PathVariable String flight_id)
			throws FlightNotFoundException {
		FlightData existflightData = fserv.updateby(flight_id);
		return fserv.save(flightdata);

	}

	@PutMapping("/allFlights/{id}")
	public String update(@RequestBody Flight flight, @PathVariable String id)
			throws FlightNotFoundException {
		Flight existflight = fserv.get(id);
		return  fserv.save(flight);
		
	}

	/*@DeleteMapping("/access/allflights/{id}")
	public String delete(@PathVariable("id") String id) throws FlightNotFoundException {
		resttemplate.delete("http://localhost:8080/Search/allFlights/" + id);
		return " deleted successfully ";

	}

	@DeleteMapping("/access/allflightdata/{flight_id}")
	public String deletebyFlightData(@PathVariable("flight_id") String flight_id) throws FlightNotFoundException {
		resttemplate.delete("http://localhost:8080/Search/allFlightData/" + flight_id);
		return " deleted successfully ";

	}*/



	@GetMapping("/allFlights/{id}")
	public ResponseEntity<?> get(@PathVariable String id) throws FlightNotFoundException {
		Flight flight = fserv.getby(id);
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}

	@GetMapping("/allFlightData/{flight_id}")
	public ResponseEntity<?> getbyFlightData(@PathVariable String flight_id) throws FlightNotFoundException {
		FlightData flight = fserv.getbyFlightData(flight_id);
		return new ResponseEntity<FlightData>(flight, HttpStatus.OK);
	}

	@PostMapping("/addFlights")
	public String addFlights(@RequestBody Flight flights) {
		return fserv.save(flights);

	}

	@PostMapping("/addFlightData")
	public String addFlightData(@RequestBody FlightData flightdata) {
		return fserv.save(flightdata);
	}

	@GetMapping("/access/allflightdata")
	public FlightData[] getAllFlightData() {
		FlightData[] flightdata = resttemplate.getForObject("http://localhost:8080/Search/allFlightData",
				FlightData[].class);
		return flightdata;
	}
	
	@DeleteMapping("/allFlights/{id}")
	public String delete(@PathVariable String id) throws FlightNotFoundException {
		fserv.deleteby(id);
		return "flight has been deleted";

	}
	
	@DeleteMapping("/allFlightData/{flight_id}")
	public String deletebyFlightData(@PathVariable String flight_id) throws FlightNotFoundException {
		fserv.deletebyFlightData(flight_id);
		return "flightdata deleted";
	}
	
	
	@GetMapping("/getallbookings")
	public Booking[] getAllBookings() {
		Booking[] booking = resttemplate.getForObject(
				"http://localhost:8081/booking/AllBookings", Booking[].class);
		return booking;
	}


}
