package FlightSearch.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import FlightSearch.Model.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight,String>{

	Flight findById();

	@Query ("{'from' : :#{#from},'to' : :#{#to}}")
	List<Flight> findByFromandTo(@Param("from") String from, @Param("to") String to);

	@Query ("{'from' : :#{#from},'to' : :#{#to},'departure_Date': :#{#departure_Date}}")
	List<Flight> findByAll(@Param("from") String from, @Param("to") String to,@Param("departure_Date") String departure_Date);
	

}
