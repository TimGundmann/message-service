package dk.gundmann.message.rotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RotationController {

	private RotationRepository rotationRepository;

	public RotationController(RotationRepository rotationRepository) {
		this.rotationRepository = rotationRepository;
	}
	
	@GetMapping
	public Iterable<Rotation> getAllRotations() {
		return rotationRepository.findAll();
	}
	
}
