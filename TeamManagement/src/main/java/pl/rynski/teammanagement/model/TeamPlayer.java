package pl.rynski.teammanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "teams_players")
public class TeamPlayer {
	
	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	private Team team;
	@Column(name = "user_id", nullable = false)
	private Integer userId;
}
