package FlightSearch.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import FlightSearch.Exception.FlightNotFoundException;
import FlightSearch.Model.Flight;
import FlightSearch.Model.FlightData;

public interface FlightService{

	

	List<Flight> getAll();

	List<FlightData> getFlightData();


	List<Flight> findByFromandTo(String from, String to);

	List<Flight> findByAll(String from, String to, String departure_Date);

	String save(FlightData flightdata);

	String save(Flight flight);



	

	

	

	

	

}
