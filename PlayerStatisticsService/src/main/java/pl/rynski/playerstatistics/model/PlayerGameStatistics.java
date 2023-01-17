package pl.rynski.playerstatistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerGameStatistics {
	
	private Long gameId;
	private Integer attacksFinished;
	private Integer attacksBlocked;
	private Integer attacksError;
	private Integer attacksAll;
	private Integer blocks;
	private Integer servicePoints;
	private Integer serviceErrors;
	private Integer servicesAll;
	private Integer receivePositive;
	private Integer receivePerfect;
	private Integer receiveNegative;
	private Integer receiveErrors;
	private Integer digs;
}
