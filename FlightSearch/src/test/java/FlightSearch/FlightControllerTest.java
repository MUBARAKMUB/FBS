package FlightSearch;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JacksonInject.Value;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import FlightSearch.Controller.FlightController;
import FlightSearch.Model.Booking;
import FlightSearch.Model.Flight;
import FlightSearch.Model.FlightData;
import FlightSearch.Repository.FlightRepository;
import FlightSearch.Service.FlightServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "FlightSearch")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { FlightControllerTest.class })
public class FlightControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	RestTemplate resttemplate;

	@Mock
	FlightServiceImpl flightServiceImpl;

	@Mock
	FlightRepository flightrepository;

	@InjectMocks
	FlightController flightController;

	public List<FlightData> flightdata = new ArrayList<FlightData>();
	public List<Flight> flight = new ArrayList<Flight>();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
	}

	@Test
	@Order(1)
	public void test_getAllFlights() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata));
		flight.add(new Flight("2", "lucknow", "blr", 1212, 12, 1, "2022-02-02", flightdata));
		when(flightServiceImpl.getAll()).thenReturn(flight);
		this.mockMvc.perform(get("/Search/allFlights")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(2)
	public void test_getAllFlightData() throws Exception {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flightdata.add(new FlightData("2", "13", "2:38", "7:06"));
		when(flightServiceImpl.getFlightData()).thenReturn(flightdata);
		this.mockMvc.perform(get("/Search/allFlightData")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(3)
	public void test_findbyfromandto() throws Exception {
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		List<Flight> flight = new ArrayList<Flight>();
		flight.add(new Flight("14", "hyd", "blr", 120, 12, 1, "2022-02-03", flightdata));
//		Flight[] arr = flight.stream().toArray(Flight[]::new);
		String from = "hyd";
		String to = "blr";
		when(flightServiceImpl.findByFromandTo(from, to)).thenReturn(flight);
		this.mockMvc.perform(get("/Search/find/{from}/{to}", from, to).param("from", "from").param("to", "to"))
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void test_getFlightbyAll() throws Exception
	{
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		List<Flight> flight = new ArrayList<Flight>();
		flight.add( new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		String from="hyd";
		String to="blr";
		String departure_Date="2022-09-02";
		when(flightServiceImpl.findByAll(from, to, departure_Date)).thenReturn(flight);
		this.mockMvc.perform(get("/Search/find/{from}/{to}/{departure_Date}",from,to,departure_Date))
		.andExpect(status().isOk())
	    .andDo(print());	
	}
	
	@Test
	@Order(5)
	public void test_getbyBookingId() throws Exception
	{
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		List<Flight> flight = new ArrayList<Flight>();
		flight.add( new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		flight.add( new Flight("13", "hyd", "del", 123, 12, 23, "2022-09-02", flightdata));
		List<FlightSearch.Model.Passenger> passenger=new ArrayList<FlightSearch.Model.Passenger>();
		passenger.add(new FlightSearch.Model.Passenger(1233,"Mubarak",21,12,600.00));
		List<Booking> booking=new ArrayList<Booking>();
		LocalDate booking_date = LocalDate.of(2022, 12, 1);
		booking.add(new Booking(123,flight,booking_date,passenger,600.00,false,false,false));
		booking.add(new Booking(124,flight,booking_date,passenger,600.00,false,false,false));
		long booking_id=123;
		Booking[] arr =  booking.stream().toArray(Booking[]::new);
		when(resttemplate.getForObject("http://localhost:8081/booking/"+ booking_id, Booking.class)).thenReturn(booking.get(0));
		this.mockMvc.perform(get("/Search/find/{booking_id}",booking_id))
		.andExpect(status().isOk())
	    .andDo(print());
		
	}
	
}
