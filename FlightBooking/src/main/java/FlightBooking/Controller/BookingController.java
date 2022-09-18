package FlightBooking.Controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import FlightBooking.Model.Booking;
import FlightBooking.Model.Flight;
import FlightBooking.Model.FlightData;
import FlightBooking.Model.Passenger;
import FlightBooking.Repository.BookingRepository;
import FlightBooking.Service.BookingService;
import FlightBooking.Service.ExceptionService;

//@RequestMapping("/{user_id}")
@RestController
@RequestMapping("/booking")
public class BookingController {
	

	@Autowired
	private BookingService bookingservice;
	
	@Autowired
	RestTemplate resttemplate;
	
	Passenger passenger = new Passenger(); 
	
	
	private static final Logger log=LoggerFactory.getLogger(BookingController.class);
	
	@GetMapping("/find/{from}/{to}/{departure_date}")
	public Flight[] getFlightByAll(@PathVariable String from,@PathVariable String to,@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departure_date){
		Flight[] flight=resttemplate.getForObject("http://localhost:8080/Search/find/"+from+"/"+to+"/"+departure_date, Flight[].class);
		return flight;
	}

	
	
	
	@PostMapping("/flightbooking")
	public String addBooking(@RequestBody Booking booking) {
		booking.setTotal_amount(setTotal_amount(booking));
		boolean present= bookingservice.findById(booking.booking_id);
		if(present==false) {
			//seat_No(booking);
			log.info("Making the booking with pnr: " +booking.getBooking_id());
			bookingservice.addBookings(booking);
		 return "Booked Successfully, Your booking id is "+booking.getBooking_id();
		}
		log.error("Booking already present");
		return "Booked already";
	}
	
	
	
	public double setTotal_amount(Booking booking) {
		double tot_sum=0;
	List<Passenger> list=	booking.getPassenger();
	for (Passenger passenger : list) {
		tot_sum+=passenger.getAmount();

	}
	return tot_sum;
	}
	
	
	
/*	public Passenger seat_No(Booking booking) {
		int seats = 0;
		int seat = 0;
		List<Flight> listOfFlights = booking.getFlight();
		List<Passenger> list = booking.getPassenger();
		for(Flight flight : listOfFlights) {
			seats = flight.getTotal_seats();
		}
		for(int i=1;i<=seats;i++){
			boolean flag = true;
			for(Passenger passenger : list) {
				if(passenger.getPassenger_seat() == i) {
					flag = false;
					break;
				}
			}
			if(flag==true) {
				passenger.setPassenger_seat(i);
			}
			else {
			flag = true;
			}
		}
	
	}*/
	
	
	
	
	@GetMapping("/BookedFlight/{booking_id}")
	public Optional<Booking> getBooking (@PathVariable("booking_id") long booking_id){
	try {	log.info("getting Ticket: "+booking_id);
		return bookingservice.findById1(booking_id);}
	catch(Exception e){
		log.error(e.toString());
	return null;}

	}
	
	
	@GetMapping("/AllBookings")
	public List<Booking>getAllBookings(){
		log.info("All Bookings");
		try {
			return bookingservice.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	
	// For user to Cancel Booking OR Self Check-In 
	@PutMapping("/booking/{booking_id}")
	public Booking updateBooking(@RequestBody Booking booking,@PathVariable("booking_id") long pnr) {
		Booking dbResponse=bookingservice.findById1(pnr).get();
		dbResponse.setBooking_cancelled(booking.isBooking_cancelled());
		dbResponse.setChecked_in(booking.isChecked_in());
		log.info("Saving Changes In Booked Ticket Of Booking Cancelled/ CheckIn: "+pnr);
		bookingservice.save(dbResponse);

		 if(booking.checked_in==true) {System.out.println("checked in");
		 log.info("Updated CheckIn Status");
		 }
		  if(booking.booking_cancelled==true)
		  {booking.setChecked_in(false);
		  System.out.println("Booking Cancelled");
		  log.info("Updated Booking Cancelled Status");
		  
		  }
		return dbResponse; 
	}
	
	@DeleteMapping("/BookedFlight/{booking_id}")
	public String deleteUser(@PathVariable long booking_id) {
		return bookingservice.deleteUser(booking_id);
	}

	}

