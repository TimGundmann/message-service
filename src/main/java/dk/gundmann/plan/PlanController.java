package dk.gundmann.plan;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dk.gundmann.security.IsAdmin;
import dk.gundmann.userclient.UserService;

@RestController
public class PlanController {

	private PlanRepository planRepository;
	private CategoryRepository categoryRepository;
	private UserService userNotification;

	public PlanController(
			PlanRepository planRepository, 
			CategoryRepository categoryRepository,
			UserService userNotification) {
		this.planRepository = planRepository;
		this.categoryRepository = categoryRepository;
		this.userNotification = userNotification;
	}
	
	@GetMapping
	public Iterable<Plan> getAllPlans() {
		return planRepository.findAll();
	}
	
	@GetMapping("/categories")
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@IsAdmin
	@PostMapping("/categories/{name}/delete")
	public void deleteCategorie(@PathVariable String name) {
		categoryRepository.deleteById(name);
	}

	@IsAdmin
	@PostMapping("/categories/update")
	public void updateCategorie(@RequestBody Category category) {
		categoryRepository.save(category);
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
		plan.setCreatedDate(LocalDateTime.now());
		planRepository.save(plan);
		userNotification.notifiy(plan.getCategory().getName());
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
	
	@PostMapping(path = "/categories/{name}/subcategories/add/{subname}")
	public void addSubCategory(@PathVariable String name, @PathVariable String subname) {
		categoryRepository.findById(name).ifPresent(category -> { 
			category.getSubCategories().add(subname); 
			categoryRepository.save(category);
		});
	}
	
}
