package dk.gundmann.message.rotation;

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
@RequestMapping("/rotations")
@Transactional
public class RotationController {

	private RotationRepository rotationRepository;

	public RotationController(RotationRepository rotationRepository) {
		this.rotationRepository = rotationRepository;
	}
	
	@GetMapping
	public Iterable<Rotation> getAllRotations() {
		return rotationRepository.findAll();
	}
	
	@GetMapping("/active")
	public Iterable<Rotation> getActiveRotations() {
		return rotationRepository.findActive();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Rotation rotation) {
		rotationRepository.save(rotation);
	}
	
	@PostMapping("/delete")
	public void delete(@RequestBody String id) {
		rotationRepository.deleteById(id);
	}
	
	@PostMapping(path = "/{id}/from/{from}")
	public void updateFrom(@PathVariable String id, @PathVariable String from) {
		rotationRepository.findById(id).ifPresent(rotation -> { 
			rotation.setFrom(LocalDate.parse(from)); 
			rotationRepository.save(rotation);
		});
	}
	
	@PostMapping(path = "/{id}/to/{to}")
	public void updateTo(@PathVariable String id, @PathVariable String to) {
		rotationRepository.findById(id).ifPresent(rotation -> { 
			rotation.setTo(LocalDate.parse(to)); 
			rotationRepository.save(rotation);
		});
	}
	
}
