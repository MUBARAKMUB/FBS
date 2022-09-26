package FlightBooking;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import FlightBooking.Controller.BookingController;
import FlightBooking.Model.Booking;
import FlightBooking.Model.Flight;
import FlightBooking.Model.FlightData;
import FlightBooking.Model.Passenger;
import FlightBooking.Service.BookingService;

@TestMethodOrder(OrderAnnotation.class)

@ComponentScan(basePackages = "FlightBooking")

@AutoConfigureMockMvc

@ContextConfiguration

@SpringBootTest(classes = { BookingControllerTest.class })
public class BookingControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	BookingService bookingService;

	@Mock
	RestTemplate resttemplate;

	@InjectMocks
	BookingController bookingController;

	public List<Passenger> passenger = new ArrayList<Passenger>();
	public List<FlightData> flightdata = new ArrayList<FlightData>();
	public List<Flight> flight = new ArrayList<Flight>();
	public List<Booking> booking = new ArrayList<Booking>();
	LocalDate booking_date = LocalDate.of(2022, 12, 1);

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}

	@Test
	@Order(1)
	public void test_getallBookings() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38 AM", "4:06 PM"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		booking.add(new Booking(123, flight, booking_date, passenger, 600.00, false, false, false));
		booking.add(new Booking(124, flight, booking_date, passenger, 600.00, false, false, false));
		when(bookingService.findAll()).thenReturn(booking);
		this.mockMvc.perform(get("/booking/AllBookings")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(2)
	public void test_addBookings() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38 AM", "4:06 PM"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking = new Booking(23, flight, booking_date, passenger, 600.00, false, false, false);
		booking.setTotal_amount(600.00);
		when(bookingService.addBookings(booking)).thenReturn(booking);
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonbody = mapper.writeValueAsString(booking);
		this.mockMvc.perform(post("/booking/flightbooking").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(3)
	public void test_getbyBookingId() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38 AM", "4:06 PM"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		flight.add(new Flight("13", "hyd", "del", 123, 12, 23, "2022-09-02", flightdata));
		passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking = new Booking(23, flight, booking_date, passenger, 600.00, false, false, false);
		long booking_id = 123;
		when(bookingService.findById1(booking_id)).thenReturn(booking);
		this.mockMvc.perform(get("/booking/{booking_id}", booking_id)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(4)
	public void test_deleteUser() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38 AM", "4:06 PM"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking = new Booking(23, flight, booking_date, passenger, 600.00, false, false, false);
		long booking_id = 123;
		when(bookingService.deleteUser(booking_id)).thenReturn(booking.toString());
		this.mockMvc.perform(delete("/booking/BookedFlight/{booking_id}", booking_id));
	}

	@Test
	@Order(5)
	public void test_updateBooking() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38 AM", "4:06 PM"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		passenger.add(new Passenger(1233, "Mubarak", 21, 12, 600.00));
		Booking booking = new Booking(23, flight, booking_date, passenger, 600.00, false, false, false);
		long booking_id = 123;
		booking.setBooking_cancelled(false);
		booking.setChecked_in(true);
		when(bookingService.findById1(booking_id)).thenReturn(booking);
		when(bookingService.save(booking)).thenReturn(booking);
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonbody = mapper.writeValueAsString(booking);
		this.mockMvc.perform(put("/booking/booking/{booking_id}", booking_id).content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(6)
	public void test_getbySearchinBooking() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38 AM", "4:06 PM"));
		flight.add(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		String from = "hyd";
		String to = "blr";
		String departure_Date = "2022-09-02";
		Flight[] arr = flight.stream().toArray(Flight[]::new);
		when(resttemplate.getForObject("http://localhost:8080/Search/find/" + from + "/" + to + "/" + departure_Date,
				Flight[].class)).thenReturn(arr);
		this.mockMvc.perform(get("/booking/find/{from}/{to}/{departure_Date}", from, to, departure_Date))
				.andExpect(status().isOk()).andDo(print());

	}
}