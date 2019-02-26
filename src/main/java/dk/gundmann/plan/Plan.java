package dk.gundmann.plan;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
	private String headline;
	@Setter
	@Column(name="fromDate")
	private LocalDate from;
	@Setter
	@Column(name="toDate")
	private LocalDate to;
	@Lob
    private String file;
	
	private String fileType;
	
	private String fileName;
	
	@OneToOne
	private Category category;
	
	@OneToOne
	private SubCategory subCategory;
	
}
