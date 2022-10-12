package FlightSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import FlightSearch.Model.Flight;
import FlightSearch.Model.FlightData;
import FlightSearch.Repository.FlightDataRepository;
import FlightSearch.Repository.FlightRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FlightRepositoryTest {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	FlightDataRepository flightDataRepository;
	
	@Test
	@Order(1)
	public void getAllflights() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",
				flightdatas);
		flightRepository.save(flight1);
		List<Flight> flight = flightRepository.findAll();
		assertEquals(8, flight.size());
	}
	
	@Test
	@Order(2)
	public void getAllFlightdata() {
		FlightData flightdata1 = new FlightData("63276510499571332a277e0d", "BH333", "5:40am", "11:50am");
		flightDataRepository.save(flightdata1);
		List<FlightData> flightdata = flightDataRepository.findAll();
		assertEquals(8, flightdata.size());
	}
	
	@Test
	@Order(3)
	public void findbyFromandTo() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",
				flightdatas);
		flightRepository.save(flight1);
		List<Flight> flight = flightRepository.findByFromandTo("Hyderabad", "Mumbai");

		assertEquals(2, flight.size());
	}
	
	@Test
	@Order(4)
	public void findbyAll() {
		List<FlightData> flightdatas = new ArrayList<FlightData>();
		flightdatas.add(new FlightData("63276510499571332a277e0d", "25543", "5:40am", "5:50am"));
		Flight flight1 = new Flight("6327e968de59757bd5dbb74e", "Hyderabad", "Mumbai", 6650, 52, 23, "2022-09-22",
				flightdatas);
		flightRepository.save(flight1);
		List<Flight> flight = flightRepository.findByAll("Hyderabad", "Mumbai", "2022-09-22");

		assertEquals(1, flight.size());
	}

	
}
