package pl.rynski.gameservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_statistics")
public class GameStatistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Byte allySets;
	private Byte opponentSets;
	private Byte firstAllySet;
	private Byte firstOpponentSet;
	private Byte secondAllySet;
	private Byte secondOpponentSet;
	private Byte thirdAllySet;
	private Byte thirdOpponentSet;
	private Byte fourthAllySet;
	private Byte fourthOpponentSet;
	private Byte fifthAllySet;
	private Byte fifthOpponentSet;
	private Integer blockPoints;
	private Integer servePoints;
	private Integer attackPoints;
	private Integer faults;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;
}
