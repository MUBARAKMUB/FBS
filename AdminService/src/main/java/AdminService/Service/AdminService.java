package AdminService.Service;

import AdminService.Model.Flight;
import AdminService.Model.FlightData;
import AdminService.Exception.FlightNotFoundException;

public interface AdminService {

	Flight getby(String id) throws FlightNotFoundException;

	FlightData getbyFlightData(String flight_id) throws FlightNotFoundException;

	String save(FlightData flightdata);

	String save(Flight flights);

	FlightData updateby(String flight_id) throws FlightNotFoundException;

	Flight get(String id) throws FlightNotFoundException;

	String deleteby(String id) throws FlightNotFoundException;

	String deletebyFlightData(String id) throws FlightNotFoundException;
}
