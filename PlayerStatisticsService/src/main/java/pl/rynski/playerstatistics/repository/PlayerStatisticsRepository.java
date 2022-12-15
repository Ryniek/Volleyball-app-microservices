package pl.rynski.playerstatistics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pl.rynski.playerstatistics.model.PlayerStatistics;

@Repository
public interface PlayerStatisticsRepository extends MongoRepository<PlayerStatistics, String> {

}
