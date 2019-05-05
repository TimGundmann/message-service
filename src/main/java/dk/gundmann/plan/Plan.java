package dk.gundmann.plan;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(indexes = { @Index(name = "IDX_CATEGORY", columnList = "category_name") })
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
	
	@ManyToOne
	@JoinColumn(name="category_name", nullable=false)
	private Category category;
	
	private String subCategory;
	
	@Setter
	private LocalDateTime createdDate;
	
}
