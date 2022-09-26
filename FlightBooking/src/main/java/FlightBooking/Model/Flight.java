package FlightBooking.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="flights")
public class Flight {
	@Id
	 private String id;
	 private String from;
	 private String to;
	 private int price;
	 private int total_seats;
	 private int available_seats;
	 private String departure_Date;
	 @DBRef
	  private List<FlightData> flight;
	 
	 
	 
	public Flight() {
		super();
	}
	public Flight(String id, String from, String to, int price, int total_seats, int available_seats,
			String departure_Date, List<FlightData> flight) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.price = price;
		this.total_seats = total_seats;
		this.available_seats = available_seats;
		this.departure_Date = departure_Date;
		this.flight = flight;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal_seats() {
		return total_seats;
	}
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	public int getAvailable_seats() {
		return available_seats;
	}
	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
	public String getDeparture_Date() {
		return departure_Date;
	}
	public void setDeparture_Date(String departure_Date) {
		this.departure_Date = departure_Date;
	}
	public List<FlightData> getFlight() {
		return flight;
	}
	public void setFlight(List<FlightData> flight) {
		this.flight = flight;
	}
	 
	 
}
