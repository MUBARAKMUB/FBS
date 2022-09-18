package FlightBooking.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="bookingdata")
public class Booking {
	
	@Id
	public long booking_id;
//	@DBRef
//	private FlightData flightdata;
	//
	@DBRef
	private List<Flight> flight;
	//
	public LocalDate booking_date;
//	@DBRef
	public List<Passenger> passenger = new ArrayList<>();
	public double total_amount;
	@Field
	public boolean booking_cancelled = false;
	public boolean checked_in = false;
	public boolean payment_completed = false;
	
	
	
	
	
	public Booking(long booking_id, List<Flight> flight, LocalDate booking_date, List<Passenger> passenger,
			double total_amount, boolean booking_cancelled, boolean checked_in, boolean payment_completed) {
		super();
		this.booking_id = booking_id;
		this.flight = flight;
		this.booking_date = booking_date;
		this.passenger = passenger;
		this.total_amount = total_amount;
		this.booking_cancelled = booking_cancelled;
		this.checked_in = checked_in;
		this.payment_completed = payment_completed;
	}
	
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public long getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(long booking_id) {
		this.booking_id = booking_id;
	}
	public List<Flight> getFlight() {
		return flight;
	}
	public void setFlight(List<Flight> flight) {
		this.flight = flight;
	}
	public LocalDate getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(LocalDate booking_date) {
		this.booking_date = booking_date;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public boolean isBooking_cancelled() {
		return booking_cancelled;
	}
	public void setBooking_cancelled(boolean booking_cancelled) {
		this.booking_cancelled = booking_cancelled;
	}
	public boolean isChecked_in() {
		return checked_in;
	}
	public void setChecked_in(boolean checked_in) {
		this.checked_in = checked_in;
	}
	public boolean isPayment_completed() {
		return payment_completed;
	}
	public void setPayment_completed(boolean payment_completed) {
		this.payment_completed = payment_completed;
	}
	
	
	


	
	
}
