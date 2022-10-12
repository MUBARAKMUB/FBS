package AdminService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import AdminService.Model.Flight;
import AdminService.Model.FlightData;
import AdminService.Repository.AdminFlightDataRepository;
import AdminService.Repository.AdminFlightRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminRepositoryTest {

	@Autowired
	AdminFlightRepository adminFlightRepository;

	@Autowired
	AdminFlightDataRepository adminFlightDataRepository;

	
	@Test
	@Order(1)
	public void getflightbyid() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",
				flightdatas);
		adminFlightRepository.save(flight1);
		Optional<Flight> flight = adminFlightRepository.findById("6327e968de59757bd5dbb74e");
		assertEquals("6327e968de59757bd5dbb74e", flight.get().getId());
	}

	@Test
	@Order(2)
	public void getflightdatabyid() {
		FlightData flightdata1 = new FlightData("63276510499571332a277e0d", "BH333", "5:40am", "11:50am");
		adminFlightDataRepository.save(flightdata1);
		Optional<FlightData> flightdata = adminFlightDataRepository.findById("63276510499571332a277e0d");
		assertEquals("63276510499571332a277e0d", flightdata.get().getFlight_id());
	}

	@Test
	@Order(3)
	public void addFlightdata() {
		FlightData flightdata1 = new FlightData("63276510499571332a277e0d", "BH333", "5:40am", "11:50am");
		adminFlightDataRepository.save(flightdata1);
		List<FlightData> flightdata = adminFlightDataRepository.findAll();
		assertEquals(8, flightdata.size());
	}

	@Test
	@Order(4)
	public void addFlights() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",flightdatas);
		adminFlightRepository.save(flight1);
		List<Flight> flight = adminFlightRepository.findAll();
		assertEquals(8, flight.size());
	}

	@Test
	@Order(5)
	public void updateFlights() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("6327dfd0de59757bd5dbb84c", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb84e", "Hyderabad", "Bangalore", 8650, 52, 23, "2022-09-22",
				flightdatas);
		adminFlightRepository.save(flight1);
		Optional<Flight> flight = adminFlightRepository.findById("6327e968de59757bd5dbb84e");
		Flight flight2 = new Flight("6327e968de59757bd5dbb84e", "Hyderabad", "Bangalore", 8650, 52, 25, "2022-09-22",
				flightdatas);
		adminFlightRepository.save(flight2);
		assertEquals("6327e968de59757bd5dbb84e", flight.get().getId());
	}

	@Test
	@Order(6)
	public void updateFlightdata() {
	FlightData flightdata1 = new FlightData("6327dfd0de59757bd5dbb84c", "BH233", "5:40am", "11:50am");
		adminFlightDataRepository.save(flightdata1);
		Optional<FlightData> flightdata = adminFlightDataRepository.findById("6327dfd0de59757bd5dbb84c");
		FlightData flightdata2 = new FlightData("6327dfd0de59757bd5dbb84c", "BH233", "4:40am", "11:50am");
		adminFlightDataRepository.save(flightdata2);
		assertEquals("6327dfd0de59757bd5dbb84c", flightdata.get().getFlight_id());
	}
	
	
	@Test
	@Order(7)
	public void deleteFlightdata() {
		FlightData flightdata1 = new FlightData("6327dfd0de59757bd5dbb84c", "BH233", "5:40am", "11:50am");
		adminFlightDataRepository.save(flightdata1);
	 adminFlightDataRepository.deleteById("6327dfd0de59757bd5dbb84c");
	 List<FlightData> flightdata = adminFlightDataRepository.findAll();
		assertEquals(8, flightdata.size());
	
	}
	
	@Test
	@Order(8)
	public void deleteFlight() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("6327dfd0de59757bd5dbb84c", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb84e", "Hyderabad", "Bangalore", 8650, 52, 23, "2022-09-22",
				flightdatas);
		adminFlightRepository.save(flight1);
	 adminFlightRepository.deleteById("6327e968de59757bd5dbb84e");
	 List<Flight> flight = adminFlightRepository.findAll();
		assertEquals(8, flight.size());
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
