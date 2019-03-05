package dk.gundmann.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, String> {
	
	@Query("SELECT p FROM Plan p WHERE (p.category.type = dk.gundmann.plan.CategoryType.SIMPLE or p.to >= CURRENT_DATE) AND p.category.name = :category ORDER BY p.to")
	Iterable<Plan> findActiveByCategory(String category);

}
