package dk.gundmann.message.rotation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RotationRepository extends CrudRepository<Rotation, String> {
	
	@Query("SELECT r FROM Rotation r WHERE r.to > CURRENT_DATE")
	Iterable<Rotation> findActive();

}
