package dk.gundmann.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, String> {
	
	@Query("SELECT r FROM Plan r WHERE r.to > CURRENT_DATE ORDER BY r.to")
	Iterable<Plan> findActive();

}
