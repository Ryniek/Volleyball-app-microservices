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
@Document("unknown_player_statistics")
public class UnknownPlayerStatistics {
	
	@Id
	private String statsId;
	private String firstName;
	private String lastName;
	private String position;
	private List<PlayerGameStatistics> playerGameStatistics;
}
