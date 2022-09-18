package FlightSearch.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FlightSearch.Exception.FlightNotFoundException;
import FlightSearch.Model.Flight;
import FlightSearch.Model.FlightData;
import FlightSearch.Repository.FlightDataRepository;
import FlightSearch.Repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDataRepository fdrepo;
	@Autowired
	private FlightRepository frepo;

	@Override
	public List<Flight> getAll() {
		return frepo.findAll();
	}

	@Override
	public List<FlightData> getFlightData() {
		return fdrepo.findAll();
	}

	@Override
	public List<Flight> findByFromandTo(String from, String to) {
		return frepo.findByFromandTo(from, to);
	}

	@Override
	public List<Flight> findByAll(String from, String to, String departure_Date) {
		return frepo.findByAll(from, to, departure_Date);
	}

	@Override
	public String save(FlightData flightdata) {
		return fdrepo.save(flightdata).getFlight_id();

	}

	@Override
	public String save(Flight flight) {
		return frepo.save(flight).getId();
	}

}
