package pl.rynski.playerstatistics.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("player_statistics")
public class PlayerStatistics {
	@Id
	private String statsId;
	@Field(name = "user_id")
	private Long userId;
	private List<PlayerGameStatistics> playerGameStatistics;
}
