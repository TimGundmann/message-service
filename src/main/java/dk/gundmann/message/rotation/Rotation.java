package dk.gundmann.message.rotation;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rotation {

	@Id
	@GeneratedValue
	private Long id;
	
	private String headline;
	@NotNull
	private LocalDate from;
	@NotNull
	private LocalDate to;
	@Lob
	@NotNull
    private byte[] pdf;
	
}
