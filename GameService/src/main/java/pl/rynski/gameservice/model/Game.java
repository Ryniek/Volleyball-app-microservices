package pl.rynski.gameservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "games")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String opponentsName;
	private String videoUrl;
	private Boolean isStarted;
	private LocalDateTime gameDate;
	@OneToMany(mappedBy = "game", orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<GameStatistics> gameStatistics;
	@OneToMany(mappedBy = "game", orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<GameDetails> gameDetails;
}
