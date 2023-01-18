package pl.rynski.teammanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.rynski.teammanagement.request.CreateTeamRequest;
import pl.rynski.teammanagement.response.TeamResponse;
import pl.rynski.teammanagement.service.TeamService;

@Slf4j
@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {
	
	private final TeamService teamService;
	
	@GetMapping("/{teamId}")
	public ResponseEntity<TeamResponse> getTeamById(@PathVariable final Integer teamId) {
		return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeamById(teamId));
	}
	
	@PostMapping
	public ResponseEntity<TeamResponse> createTeam(@RequestBody final CreateTeamRequest createTeamRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(createTeamRequest));
	}
	
	//TODO zaprosic tylko tych ktorzy nie sa w teamie, tylko przez creatora/tworce danej druzyny
	@PostMapping("/{teamId}/invite/{userId}")
	public ResponseEntity<?> inviteUserToTheTeam(@PathVariable final Integer teamId, @PathVariable final Integer userId) {
		teamService.inviteUser(teamId, userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
