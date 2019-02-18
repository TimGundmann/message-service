package dk.gundmann.plan;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@OneToMany
	private List<SubCategory> subCategories;
	
}
