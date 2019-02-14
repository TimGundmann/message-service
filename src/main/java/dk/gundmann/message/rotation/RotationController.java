package dk.gundmann.message.rotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rotations")
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
	
}
