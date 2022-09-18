package CheckIn.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="flightdetails")
public class FlightData {
	@Id
	private String flight_id;
	private String flight_No;
	private String departure_Time;
	private String arrival_Time;
	
	
	public FlightData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlightData(String flight_id, String flight_No, String departure_Time, String arrival_Time) {
		super();
		this.flight_id = flight_id;
		this.flight_No = flight_No;
		this.departure_Time = departure_Time;
		this.arrival_Time = arrival_Time;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public String getFlight_No() {
		return flight_No;
	}
	public void setFlight_No(String flight_No) {
		this.flight_No = flight_No;
	}
	public String getDeparture_Time() {
		return departure_Time;
	}
	public void setDeparture_Time(String departure_Time) {
		this.departure_Time = departure_Time;
	}
	public String getArrival_Time() {
		return arrival_Time;
	}
	public void setArrival_Time(String arrival_Time) {
		this.arrival_Time = arrival_Time;
	}
	
	
	
	
}
