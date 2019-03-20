package dk.gundmann.plan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

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
public class Category {

	@Id
	private String name;
	
	private CategoryType type;
	
	@Column(name="sort_order")
	private int order;
	
	@Builder.Default
	@ElementCollection(fetch=FetchType.EAGER) 	
	private Set<String> alterRoles = new HashSet<>(Arrays.asList("ADMIN"));
	
	@ElementCollection
	private Set<String> subCategories;
	
}
