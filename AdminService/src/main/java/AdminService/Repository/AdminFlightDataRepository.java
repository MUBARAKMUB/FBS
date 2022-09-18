package AdminService.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import AdminService.Model.FlightData;

@Repository
public interface AdminFlightDataRepository extends MongoRepository<FlightData, String> {

}
