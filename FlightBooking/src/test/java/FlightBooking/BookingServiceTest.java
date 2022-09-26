package FlightBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import FlightBooking.Model.Booking;
import FlightBooking.Model.Flight;
import FlightBooking.Model.FlightData;
import FlightBooking.Model.Passenger;
import FlightBooking.Repository.BookingRepository;
import FlightBooking.Service.BookingService;
import FlightBooking.Exception.BookingDataNotFoundException;

@SpringBootTest(classes = {  BookingServiceTest.class })
@TestMethodOrder(OrderAnnotation.class)
public class BookingServiceTest {

	@Mock
	BookingRepository bookingrepository;

	@InjectMocks
	BookingService bookingService;

	public List<Passenger> passenger;

	public List<FlightData> flightdata = new ArrayList<FlightData>();
	
	public List<Flight> flight = new ArrayList<Flight>();
	
	public LocalDate booking_date = LocalDate.of(2022, 12, 1);
	
	public 	List<Booking> booking = new ArrayList<Booking>();
	
	@Test
	@Order(1)
	public void test_addBookings() {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		Booking booking = new Booking(123, flight, booking_date, passenger, 2134, false, true, true);
		when(bookingrepository.save(booking)).thenReturn(booking);
		assertEquals(booking, bookingService.addBookings(booking));
	}

	@Test
	@Order(2)
	public void test_findAll() {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		booking.add(new Booking(123, flight, booking_date, passenger, 2134, false, true, true));
		when(bookingrepository.findAll()).thenReturn(booking);
		assertEquals(booking, bookingService.findAll());
	}
	
	@Test
	@Order(3)
	public void test_findById1() throws BookingDataNotFoundException
	{
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		Booking b = new Booking(123, flight, booking_date, passenger, 2134, false, true, true);
		long booking_id = 123;
		when(bookingrepository.findById1(booking_id)).thenReturn(b);
		assertEquals(b, bookingService.findById1(booking_id));
	}
	
	@Test
	@Order(4)
	public void test_findById()
	{
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		Optional<Booking> b = Optional.of(new Booking(123, flight, booking_date, passenger, 2134, false, true, true));
		long booking_id = 123;
		Boolean b1=true;
		when(bookingrepository.findById(booking_id)).thenReturn(b);
		assertEquals(b1, bookingService.findById(booking_id));
	}
	
	@Test
	@Order(5)
	public void test_deleteUser()
	{
		String res = "Your booking is canceled";
		long booking_id = 123;
		bookingrepository.deleteById(booking_id);
		assertEquals(res, bookingService.deleteUser(booking_id));
	}

}