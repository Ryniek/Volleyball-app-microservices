package pl.rynski.teammanagement.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team_invitations")
public class TeamInvitation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	@Column(name = "sending_time", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime sendingTime;
}
