package AdminService;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import AdminService.Controller.AdminController;
import AdminService.Exception.FlightNotFoundException;
import AdminService.Model.Booking;
import AdminService.Model.Flight;
import AdminService.Model.FlightData;
import AdminService.Model.Passenger;
import AdminService.Service.AdminService;

@ComponentScan(basePackages = "AdminService")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {AdminControllerTest.class})
@TestMethodOrder(OrderAnnotation.class)
public class AdminControllerTest {
	
	@Mock
	AdminService fserv;
	
	@Mock
	RestTemplate resttemplate;
	
	
	
	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	AdminController controller;
	
	List<FlightData> flightdatas;
	FlightData flightdata;
	FlightData[] flightd;
	
	List<Flight> flightss;
	Flight flight;
	Flight flights;
	Flight[] fligh;
	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@Order(1)
	public void test_get() throws Exception  {
//		 flightdatas= new ArrayList<FlightData>();
//		 flightdatas.add(new FlightData("1","23543","3:40am","3:50am"));
//		 flightdatas.add(new FlightData("2","24543","4:40am","4:50am"));
//		 flightdatas.add(new FlightData("3","25543","5:40am","5:50am"));
     flightdata = new FlightData("3","25543","5:40am","5:50am");
		
		String flight_id="3";
		when(fserv.getbyFlightData(flight_id)).thenReturn(flightdata);
			
	this.mockMvc.perform(get("/admin/allFlightData/{flight_id}",flight_id))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("flight_id").value("3"))
			.andExpect(MockMvcResultMatchers.jsonPath("flight_No").value("25543"))
			.andExpect(MockMvcResultMatchers.jsonPath("departure_Time").value("5:40am"))
			.andExpect(MockMvcResultMatchers.jsonPath("arrival_Time").value("5:50am"))
			.andDo(print());
		
}
		 
	@Test
	@Order(2)
	public void test_getFlight() throws Exception  {
//		List<FlightData> flightdatas = new ArrayList<FlightData>();
//	flightdatas.add(new FlightData("12", "123", "12:38", "4:06"));
		  flightdatas = new ArrayList<FlightData>();
		  flightdatas.add(new FlightData("3","25543","5:40am","5:50am"));
		  flightdata = new FlightData("3","25543","5:40am","5:50am");
     flight = new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdatas);
		
		String id="12";
		when(fserv.getby(id)).thenReturn(flight);
			
	this.mockMvc.perform(get("/admin/allFlights/{id}",id))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("id").value("12"))
			.andExpect(MockMvcResultMatchers.jsonPath("from").value("hyd"))
			.andExpect(MockMvcResultMatchers.jsonPath("to").value("blr"))
			.andExpect(MockMvcResultMatchers.jsonPath("price").value(123))
			.andExpect(MockMvcResultMatchers.jsonPath("total_seats").value(12))
			.andExpect(MockMvcResultMatchers.jsonPath("available_seats").value(23))
			.andExpect(MockMvcResultMatchers.jsonPath("departure_Date").value("2022-09-02"))
			.andExpect(MockMvcResultMatchers.jsonPath("flight").hasJsonPath())
			.andDo(print());
		
}
	
	@Test
	@Order(3)
	public void test_addFlightData() throws Exception  {
		 flightdata = new FlightData("3","25543","5:40am","5:50am");
     	
		
		when(fserv.save(flightdata)).thenReturn(flightdata.getFlight_id());
		
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody= mapper.writeValueAsString(flightdata);	
		this.mockMvc.perform(post("/admin/addFlightData").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
			
	
		
}
	
	@Test
	@Order(4)
	public void test_addFlights() throws Exception  {
		 flightdatas = new ArrayList<FlightData>();
		  flightdatas.add(new FlightData("3","25543","5:40am","5:50am"));
		  flightdata = new FlightData("3","25543","5:40am","5:50am");
    flights = new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdatas);
		
		
		when(fserv.save(flights)).thenReturn(flights.getId());
		
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody= mapper.writeValueAsString(flights);	
		this.mockMvc.perform(post("/admin/addFlights").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
			
}
	
	
	@Test
	@Order(5)
	public void test_updateFlightData() throws Exception  {
		 flightdata = new FlightData("3","25543","5:40am","5:50am");
     	String flight_id="3";
		when(fserv.updateby(flight_id)).thenReturn(flightdata);
		when(fserv.save(flightdata)).thenReturn(flightdata.getFlight_id());
		
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody= mapper.writeValueAsString(flightdata);	
		this.mockMvc.perform(put("/admin/allFlightData/{flight_id}",flight_id).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
			
	
		
}
		 
	@Test
	@Order(6)
	public void test_updateFlights() throws Exception  {
		 flightdatas = new ArrayList<FlightData>();
		  flightdatas.add(new FlightData("3","25543","5:40am","5:50am"));
		  flightdata = new FlightData("3","25543","5:40am","5:50am");
    flights = new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdatas);
		String id="3";
		when(fserv.get(id)).thenReturn(flights);
		when(fserv.save(flights)).thenReturn(flights.getId());
		
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody= mapper.writeValueAsString(flights);	
		this.mockMvc.perform(put("/admin/allFlights/{id}",id).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
			
}
	
	@Test
	@Order(7)
	public void test_deleteFlightData() throws Exception  {
		
		 flightdata = new FlightData("3","25543","5:40am","5:50am");
		String flight_id="3";
	   	String res="flightdata deleted";
		when(fserv.deletebyFlightData(flight_id)).thenReturn(res);
		
		this.mockMvc.perform(delete("/admin/allFlightData/{flight_id}",flight_id))
		.andExpect(status().isOk()).andDo(print());
}
	@Test
	@Order(8)
	public void test_delete() throws Exception
	{
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		Flight flight = new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata);
		String id = "12";
		String res = "flight has been deleted";
		when(fserv.deleteby(id)).thenReturn(res);
		this.mockMvc.perform(delete("/admin/allFlights/{id}",id));
	}
	
	@Test
	@Order(9)
	public void test_getallFlights() throws Exception
	{
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		List<Flight> flight = new ArrayList<Flight>();
		flight.add( new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		flight.add( new Flight("13", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		Flight[] arr =  flight.stream().toArray(Flight[]::new);

		when(resttemplate.getForObject("http://localhost:8080/Search/allFlights", Flight[].class)).thenReturn(arr);
		this.mockMvc.perform(get("/admin/allflights"))
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@Order(10)
	public void test_getallFlightData() throws Exception
	{
		FlightData flightdata = new FlightData("3","25543","5:40am","5:50am");
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("12", "123", "12:38", "4:06"));
		flightdatas.add(new FlightData("13", "123", "12:38", "4:06"));
		FlightData[] arr = flightdatas.stream().toArray(FlightData[]::new);
		when(resttemplate.getForObject("http://localhost:8080/Search/allFlightData",FlightData[].class)).thenReturn(arr);
		this.mockMvc.perform(get("/admin/access/allflightdata"))
		.andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(11)
	public void test_getallBookings() throws Exception
	{
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		List<Flight> flight = new ArrayList<Flight>();
		flight.add( new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		List<Passenger> passenger=new ArrayList<Passenger>();
		passenger.add(new Passenger(1233,"Mubarak",21,12,600.00));
		List<Booking> booking=new ArrayList<Booking>();
		LocalDate booking_date = LocalDate.of(2022, 12, 1);
		booking.add(new Booking(123,flight,booking_date,passenger,600.00,false,false,false));
		booking.add(new Booking(124,flight,booking_date,passenger,600.00,false,false,false));
		Booking[] arr =  booking.stream().toArray(Booking[]::new);
		when(resttemplate.getForObject("http://localhost:8081/booking/AllBookings", Booking[].class)).thenReturn(arr);
		this.mockMvc.perform(get("/admin/getallbookings"))
		.andExpect(status().isOk()).andDo(print());
	}
	
	
	@Test
	@Order(12)
	public void test_getbySearch() throws Exception
	{
		List<FlightData> flightdata = new ArrayList<FlightData>();
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		List<Flight> flight = new ArrayList<Flight>();
		flight.add( new Flight("12", "hyd", "blr", 123, 12, 23, "2022-09-02", flightdata));
		String from="hyd";
		String to="blr";
		String departure_Date="2022-09-02";
		Flight[] arr =  flight.stream().toArray(Flight[]::new);
		when(resttemplate.getForObject("http://localhost:8080/Search/find/" + from + "/" + to + "/" + departure_Date, Flight[].class)).thenReturn(arr);
		this.mockMvc.perform(get("/admin/access/{from}/{to}/{departure_Date}",from,to,departure_Date))
		.andExpect(status().isOk())
	    .andDo(print());
		
	}
	
//	@Test
//	@Order(7)
//	public void test_delete() throws FlightNotFoundException {
//		List<FlightData> flightdata = new ArrayList<FlightData>();
//		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
//		Flight flight = new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata);
//		String res = "flight has been deleted";
//		String id = "1";
//		fserv.deleteby(id);
//		assertEquals(res, controller.delete(id));
//	}
	
//	@Test
//	@Order(7)
//	public void test_delete() throws FlightNotFoundException {
//		List<FlightData> flightdata = new ArrayList<FlightData>();
//		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
//		Flight flight = new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata);
////		String res = flight.toString();
//		String id = "1";
//		fserv.deleteby(id);
//		verify(fserv,times(1)).deleteby(id);
////		assertEquals(res, adminController.delete(id));
//	}
	
	
	

}