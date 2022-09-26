package AdminService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import AdminService.Exception.FlightNotFoundException;
import AdminService.Model.Flight;
import AdminService.Model.FlightData;
import AdminService.Repository.AdminFlightDataRepository;
import AdminService.Repository.AdminFlightRepository;
import AdminService.Service.AdminService;
import AdminService.Service.AdminServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AdminServiceTest {

	@Mock
	AdminFlightRepository frepo;
	
	@Mock
	AdminFlightDataRepository fdrepo;
	
//	@Autowired
//	AdminService fserv;
//	
	@InjectMocks
	AdminServiceImpl admin;
	
	@Test
	@Order(1)
	public void test_addFlights() {
		List<FlightData> flightdata=new ArrayList<FlightData>();
			flightdata.add(new	FlightData("1","23543","3:40am","3:50am"));
		Flight flight=new Flight();
		flight.setId("1");
		flight.setFrom("hyd");
		flight.setTo("del");
		flight.setPrice(230);
		flight.setTotal_seats(40);
		flight.setAvailable_seats(20);
		flight.setDeparture_Date("2022-09-23");
		flight.setFlight(flightdata);
		when(frepo.save(flight)).thenReturn(flight);
		assertEquals(flight.getId(),admin.save(flight));
	}
	
	@Test
	@Order(2)
	public void test_addFlightData() {
		FlightData flightdata=new FlightData("12","BH234","9:40AM","9:50AM");
		when(fdrepo.save(flightdata)).thenReturn(flightdata);
		assertEquals(flightdata.getFlight_id(),admin.save(flightdata));
	}
	
//	public void Test_getFlightbyid() throws FlightNotFoundException  {
//		List<FlightData> flightdata=new ArrayList<FlightData>();
//		flightdata.add(new	FlightData("1","23543","3:40am","3:50am"));
////		Flight flight=  new Flight ("1","Hyd","guj",123,23,12,"2022-09-24",flightdata);
////		 new Flight ("2","Hyd","del",223,43,22,"2022-09-25",flightdata);
//	Optional<Flight> flight= Optional.of( new Flight ("1","Hyd","guj",123,23,12,"2022-09-24",flightdata));
//		String id="1";
//		when(frepo.findById(id)).thenReturn(flight);
//		assertEquals(flight.get(),admin.getby(id));
//		
//		
//	}
	
	@Test
	@Order(3)
	public void test_getby() throws FlightNotFoundException
	{
		List<FlightData> flightdata=new ArrayList<FlightData>();
		flightdata.add(new	FlightData("1","23543","3:40am","3:50am"));
		Optional<Flight> flight = Optional.of(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		String id="1";
		when(frepo.findById(id)).thenReturn(flight);
		assertEquals(flight.get(),admin.getby(id));
	}
	
	
	@Test
	@Order(4)
	public void test_getbyFlightData() throws FlightNotFoundException
	{
		Optional<FlightData> flightdata=Optional.of(new	FlightData("1","23543","3:40am","3:50am"));
		String flight_id="1";
		when(fdrepo.findById(flight_id)).thenReturn(flightdata);
		assertEquals(flightdata.get(),admin.getbyFlightData(flight_id));
	}
	
	@Test
	@Order(5)
	public void test_updateflight() throws FlightNotFoundException{
		List<FlightData> flightdata=new ArrayList<FlightData>();
		flightdata.add(new	FlightData("1","23543","3:40am","3:50am"));
		Optional<Flight> flight = Optional.of(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		String id="1";
		when(frepo.findById(id)).thenReturn(flight);
		assertEquals(flight.get(),admin.get(id));
	}
	
	@Test
	@Order(6)
	public void test_updatebyFlightData() throws FlightNotFoundException
	{
		Optional<FlightData> flightdata=Optional.of(new	FlightData("1","23543","3:40am","3:50am"));
		String flight_id="1";
		when(fdrepo.findById(flight_id)).thenReturn(flightdata);
		assertEquals(flightdata.get(),admin.updateby(flight_id));
	}
	
	@Test
	@Order(7)
	public void test_deletebyFlight() throws FlightNotFoundException{
		List<FlightData> flightdata=new ArrayList<FlightData>();
		flightdata.add(new	FlightData("1","23543","3:40am","3:50am"));
		Optional<Flight> flight = Optional.of(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		String id="12";
		frepo.deleteById(id);
	    verify(frepo, times(1)).deleteById(id);
	
	}
	
	@Test
	@Order(8)
	public void test_deletebyFlightData() throws FlightNotFoundException{
		List<FlightData> flightdata=new ArrayList<FlightData>();
		flightdata.add(new	FlightData("1","23543","3:40am","3:50am"));
		Optional<Flight> flight = Optional.of(new Flight("12", "hyd", "blr", 123, 12, 23, "2022-03-02", flightdata));
		String id="12";
		fdrepo.deleteById(id);
	    verify(fdrepo, times(1)).deleteById(id);
	
	}
	
	
	
}
