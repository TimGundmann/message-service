package dk.gundmann.plan;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {
	
	@Override
	@Query("SELECT c FROM Category c ORDER BY c.order")
	Iterable<Category> findAll();

}
