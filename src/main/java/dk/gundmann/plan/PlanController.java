package dk.gundmann.plan;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class PlanController {

	private PlanRepository planRepository;

	public PlanController(PlanRepository planRepository) {
		this.planRepository = planRepository;
	}
	
	@GetMapping
	public Iterable<Plan> getAllPlans() {
		return planRepository.findAll();
	}
	
	@GetMapping("/active")
	public Iterable<Plan> getActivePlans() {
		return planRepository.findActive();
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
