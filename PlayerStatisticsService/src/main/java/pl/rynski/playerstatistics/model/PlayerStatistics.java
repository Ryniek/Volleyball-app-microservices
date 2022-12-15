package pl.rynski.playerstatistics.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "player_statistics")
public class PlayerStatistics {
	@Id
	private String statsId;
	private Long userId;
	private List<GameStatistics> gameStatistics;
}
