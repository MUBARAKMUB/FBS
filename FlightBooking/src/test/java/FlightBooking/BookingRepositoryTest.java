package FlightBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import FlightBooking.Model.Booking;
import FlightBooking.Model.Flight;
import FlightBooking.Model.FlightData;
import FlightBooking.Model.Passenger;
import FlightBooking.Repository.BookingRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookingRepositoryTest {
	
	@Autowired
	BookingRepository bookingRepository;

	
	@Test
	@Order(1)
	public void addBookings() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		 List<Flight> flight = new ArrayList<Flight>();
		 flight.add(new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",flightdatas));
		LocalDate booking_date = LocalDate.of(2022, 12, 27);
		 List<Passenger> passenger=new ArrayList<Passenger>();
		 passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking= new Booking(111, flight, booking_date, passenger, 600.00, false, false, false);
		bookingRepository.save(booking);
		List<Booking> booking1 = bookingRepository.findAll();
		assertEquals(3, booking1.size());
	}
	
	
	@Test
	@Order(2)
	public void getAllBookings() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		 List<Flight> flight = new ArrayList<Flight>();
		 flight.add(new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",flightdatas));
		LocalDate booking_date = LocalDate.of(2022, 12, 27);
		 List<Passenger> passenger=new ArrayList<Passenger>();
		 passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking= new Booking(111, flight, booking_date, passenger, 600.00, false, false, false);
		bookingRepository.save(booking);
		List<Booking> booking1 = bookingRepository.findAll();
		assertEquals(3, booking1.size());
	}
	
	@Test
	@Order(3)
	public void getAllBookingsbyId() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		 List<Flight> flight = new ArrayList<Flight>();
		 flight.add(new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",flightdatas));
		LocalDate booking_date = LocalDate.of(2022, 12, 27);
		 List<Passenger> passenger=new ArrayList<Passenger>();
		 passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking= new Booking(111, flight, booking_date, passenger, 600.00, false, false, false);
		bookingRepository.save(booking);
		Booking booking1 =  bookingRepository.findById1(111);
		assertEquals(111, booking1.booking_id);
	}
	
	@Test
	@Order(4)
	public void updateBookingsbyId() {
//		List<FlightData> flightdatas = new ArrayList<FlightData>();
//		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
//		 List<Flight> flight = new ArrayList<Flight>();
//		 flight.add(new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",flightdatas));
//		LocalDate booking_date = LocalDate.of(2022, 12, 27);
//		 List<Passenger> passenger=new ArrayList<Passenger>();
//		 passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
//		Booking booking= new Booking(111, flight, booking_date, passenger, 600.00, false, false, false);
		Booking booking1 =  bookingRepository.findById1(111);
		booking1.setBooking_cancelled(false);
		booking1.setChecked_in(false);
		bookingRepository.save(booking1);
		assertEquals(111, booking1.booking_id);
	}
	
	@Test
	@Order(5)
	public void deleteBooking() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		 List<Flight> flight = new ArrayList<Flight>();
		 flight.add(new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",flightdatas));
		LocalDate booking_date = LocalDate.of(2022, 12, 27);
		 List<Passenger> passenger=new ArrayList<Passenger>();
		 passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking= new Booking(111, flight, booking_date, passenger, 600.00, false, false, false);
		bookingRepository.save(booking);
		bookingRepository.deleteById((long) 111);
		List<Booking> booking1 = bookingRepository.findAll();
		assertEquals(2, booking1.size());
	
	}
	
}
