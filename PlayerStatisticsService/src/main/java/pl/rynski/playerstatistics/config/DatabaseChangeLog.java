package pl.rynski.playerstatistics.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import pl.rynski.playerstatistics.repository.PlayerStatisticsRepository;

@ChangeLog
public class DatabaseChangeLog {

	@ChangeSet(order = "001", id = "seedDatabase", author = "mrynski")
	public void seedDatabase(PlayerStatisticsRepository playerStatisticsRepository) {
		//to fill
	}
}
