package AdminService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminService.Exception.FlightNotFoundException;
import AdminService.Model.Flight;
import AdminService.Model.FlightData;
import AdminService.Repository.AdminFlightDataRepository;
import AdminService.Repository.AdminFlightRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminFlightRepository frepo;

	@Autowired
	AdminFlightDataRepository fdrepo;

	
	@Override
	public Flight getby(String id) throws FlightNotFoundException {
		return frepo.findById(id)
				.orElseThrow(()-> new FlightNotFoundException ("flight details not found"));
		
	}

	@Override
	public FlightData getbyFlightData(String flight_id) throws FlightNotFoundException {
		return fdrepo.findById(flight_id)
				.orElseThrow(()-> new FlightNotFoundException ("flight details not found"));
	}

	@Override
	public String save(FlightData flightdata) {
		return fdrepo.save(flightdata).getFlight_id();
	}

	@Override
	public String save(Flight flights) {
		return frepo.save(flights).getId();
	}

	@Override
	public FlightData updateby(String flight_id) throws FlightNotFoundException {
		return fdrepo.findById(flight_id)
				.orElseThrow(()-> new FlightNotFoundException ("flight details not found"));
	}

	@Override
	public Flight get(String id) throws FlightNotFoundException {
		return frepo.findById(id)
				.orElseThrow(()-> new FlightNotFoundException ("flight details not found"));
	}
	
	@Override
	public void deleteby(String id) throws FlightNotFoundException {
		frepo.deleteById(id);
	}

	@Override
	public void deletebyFlightData(String id) throws FlightNotFoundException {
		fdrepo.deleteById(id);
	}

	

}
