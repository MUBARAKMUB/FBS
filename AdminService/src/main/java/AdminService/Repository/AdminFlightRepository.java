package AdminService.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import AdminService.Model.Flight;

@Repository
public interface AdminFlightRepository extends MongoRepository<Flight, String> {

}
