package FlightSearch;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import FlightSearch.Exception.FlightNotFoundException;
import FlightSearch.Model.FlightData;
import FlightSearch.Repository.FlightDataRepository;
import FlightSearch.Service.FlightService;

@SpringBootTest
class FlightSearchApplicationTests {

	@Autowired
	private FlightService fserv;
	@MockBean
	private FlightDataRepository fdrepo;

       @Test
    public void addFlightData() {
	FlightData flightdata= new FlightData("1","104","10:20AM","10:00AM");
	when(fdrepo.save(flightdata)).thenReturn(flightdata);
	assertEquals(flightdata,fdrepo.save(flightdata));
       }
       
	     @Test
		public void deletebyFlightDataTest() throws FlightNotFoundException {
		FlightData flightdata=new FlightData("1","104","10:20AM","10:00AM");
		fdrepo.deleteById("id");
	    verify(fdrepo, times(1)).deleteById("id");		
		}
	
	
	

	

}
