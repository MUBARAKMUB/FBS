package FlightBooking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FlightBooking.Exception.BookingDataNotFoundException;
import FlightBooking.Model.Booking;
import FlightBooking.Repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingrepository;
	

	public Booking addBookings(Booking booking) {
		return bookingrepository.save(booking);
	}


//	public Optional<Booking> findById1(long booking_id) {
	//	return bookingrepository.findById((long) booking_id);
		//}
	public Booking findById1(long booking_id) throws BookingDataNotFoundException {
		Booking b= bookingrepository.findById1((long) booking_id);
		if(b != null) {
			return b;
		}else {
			throw new BookingDataNotFoundException("Booking data is not found with id: " + booking_id);
		}
	}
	
	public boolean findById(long booking_id) {
		boolean present =bookingrepository.findById((long) booking_id).isPresent();
		return present;
	}
	
	
	public List<Booking> findAll() {
		return bookingrepository.findAll();
		}


	public Booking save(Booking dbResponse) {
	return	bookingrepository.save(dbResponse);
		
	}


	public String deleteUser(long booking_id) {
		bookingrepository.deleteById((long) booking_id);
		return "Your booking is canceled";
	}
	
	
	//
//	public boolean findSeat(int seat_No) {
//		boolean present =bookingrepository.findOne((int) passenger_seat).isPresent();
//		return present;
//	}
	//
}


