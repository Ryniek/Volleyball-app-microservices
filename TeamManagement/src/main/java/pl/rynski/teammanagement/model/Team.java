package pl.rynski.teammanagement.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name", unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
	private String name;
	@Column(name = "creator_id", nullable = false)
	private Integer creatorId;
	@Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDate;
	//TODO Logo druzyny
	
	//TODO many to many adminow druzyny(druzyna moze miec wiele adminow a user moze byc adminem w wielu druzynach)
	
	//TODO jakies many to many z uzytkownikami przypisanymi do druzyny + nazwa z imienia i nazwiska
	@OneToMany(mappedBy = "team")
	private List<TeamPlayer> players;
}
