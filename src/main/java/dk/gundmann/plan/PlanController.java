package dk.gundmann.plan;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {

	private PlanRepository planRepository;
	private CategoryRepository categoryRepository;

	public PlanController(PlanRepository planRepository, CategoryRepository categoryRepository) {
		this.planRepository = planRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping
	public Iterable<Plan> getAllPlans() {
		return planRepository.findAll();
	}
	
	@GetMapping("/categories")
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/categories/{name}")
	public Category getCategorie(@PathVariable String name) {
		return categoryRepository.findById(name).get();
	}
	
	@GetMapping("/{category}/active")
	public Iterable<Plan> getActivePlans(@PathVariable String category) {
		return planRepository.findActiveByCategory(category);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Plan plan) {
		planRepository.save(plan);
	}
	
	@PostMapping("/delete")
	public void delete(@RequestBody String id) {
		planRepository.deleteById(id);
	}
	
	@PostMapping(path = "/{id}/from/{from}")
	public void updateFrom(@PathVariable String id, @PathVariable String from) {
		planRepository.findById(id).ifPresent(plan -> { 
			plan.setFrom(LocalDate.parse(from)); 
			planRepository.save(plan);
		});
	}
	
	@PostMapping(path = "/{id}/to/{to}")
	public void updateTo(@PathVariable String id, @PathVariable String to) {
		planRepository.findById(id).ifPresent(plan -> { 
			plan.setTo(LocalDate.parse(to)); 
			planRepository.save(plan);
		});
	}
	
}
