package dk.gundmann.plan;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import dk.gundmann.plan.Plan.PlanBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

	@Id
	private String name;
	
}
