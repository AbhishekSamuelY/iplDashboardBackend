package com.abhisheksamuely.ipl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abhisheksamuely.ipl.models.Team;
import com.abhisheksamuely.ipl.repositories.MatchRepository;
import com.abhisheksamuely.ipl.repositories.TeamRepository;

@RestController
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		team.setLatestMatches(matchRepository.findLatestMatchesByTeam(teamName, 5));

		return team;
	}
}
