package FlightSearch.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import FlightSearch.Model.FlightData;

@Repository
public interface FlightDataRepository extends MongoRepository<FlightData,String>{

	

	

}
