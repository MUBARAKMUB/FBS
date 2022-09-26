package FlightSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import FlightSearch.Model.Flight;
import FlightSearch.Model.FlightData;
import FlightSearch.Repository.FlightDataRepository;
import FlightSearch.Repository.FlightRepository;
import FlightSearch.Service.FlightServiceImpl;

@SpringBootTest(classes = { FlightServiceTest.class })
@TestMethodOrder(OrderAnnotation.class)
public class FlightServiceTest {
	@Mock
	FlightRepository frepo;

	@Mock
	FlightDataRepository fdrepo;

	@InjectMocks
	FlightServiceImpl flightServiceImpl;

	public List<FlightData> flightdata = new ArrayList<FlightData>();

	public List<Flight> flight = new ArrayList<Flight>();

	@Test
	@Order(1)
	public void test_getAll() {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata));
		when(frepo.findAll()).thenReturn(flight);
		assertEquals(flight, flightServiceImpl.getAll());
	}
	
	@Test
	@Order(2)
	public void test_getFlightData() {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		when(fdrepo.findAll()).thenReturn(flightdata);
		assertEquals(flightdata, flightServiceImpl.getFlightData());
	}
	
	@Test
	@Order(3)
	public void test_findByFromandTo() {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata));
		when(frepo.findByFromandTo("hyd", "blr")).thenReturn(flight);
		assertEquals(flight, flightServiceImpl.findByFromandTo("hyd", "blr"));
	}
	
	@Test
	@Order(4)
	public void test_findByAll() {
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		flight.add(new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata));
		when(frepo.findByAll("hyd", "blr","2022-02-02")).thenReturn(flight);
		assertEquals(flight, flightServiceImpl.findByAll("hyd", "blr","2022-02-02"));
	}

	@Test
	@Order(5)
	public void test_save() {
		FlightData f = new FlightData("12", "123", "12:38", "4:06");
		when(fdrepo.save(f)).thenReturn(f);
		assertEquals(f.getFlight_id(), flightServiceImpl.save(f));
	}

	@Test
	@Order(6)
	public void test_saveFlight()
	{
		flightdata.add(new FlightData("12", "123", "12:38", "4:06"));
		Flight flight = new Flight("1", "hyd", "blr", 120, 12, 1, "2022-02-02", flightdata);
		when(frepo.save(flight)).thenReturn(flight);
		assertEquals(flight.getId(),flightServiceImpl.save(flight));
	}

}
