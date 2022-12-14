package pl.rynski.teammanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.rynski.teammanagement.request.CreateTeamRequest;
import pl.rynski.teammanagement.service.TeamService;

@RestController
@RequestMapping("/teams")
public record TeamController(TeamService teamService) {
	
	@PostMapping
	public ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest createTeamRequest, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(createTeamRequest, request));
	}
}
