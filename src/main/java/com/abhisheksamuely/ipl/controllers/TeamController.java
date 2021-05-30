/*
 
       d8888 888888b.   888    888 8888888 .d8888b.  888    888 8888888888 888    d8P   .d8888b.        d8888 888b     d888 888     888 8888888888 888    Y88b   d88P 
      d88888 888  "88b  888    888   888  d88P  Y88b 888    888 888        888   d8P   d88P  Y88b      d88888 8888b   d8888 888     888 888        888     Y88b d88P  
     d88P888 888  .88P  888    888   888  Y88b.      888    888 888        888  d8P    Y88b.          d88P888 88888b.d88888 888     888 888        888      Y88o88P   
    d88P 888 8888888K.  8888888888   888   "Y888b.   8888888888 8888888    888d88K      "Y888b.      d88P 888 888Y88888P888 888     888 8888888    888       Y888P    
   d88P  888 888  "Y88b 888    888   888      "Y88b. 888    888 888        8888888b        "Y88b.   d88P  888 888 Y888P 888 888     888 888        888        888     
  d88P   888 888    888 888    888   888        "888 888    888 888        888  Y88b         "888  d88P   888 888  Y8P  888 888     888 888        888        888     
 d8888888888 888   d88P 888    888   888  Y88b  d88P 888    888 888        888   Y88b  Y88b  d88P d8888888888 888   "   888 Y88b. .d88P 888        888        888     
d88P     888 8888888P"  888    888 8888888 "Y8888P"  888    888 8888888888 888    Y88b  "Y8888P" d88P     888 888       888  "Y88888P"  8888888888 88888888   888     
======================================================================================================================================================================
email: abhisheksamuel.y@gmail.com
github: abhisheksamuely@github
                                                                                                                                                                                                                                                                              
*/
package com.abhisheksamuely.ipl.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhisheksamuely.ipl.models.Match;
import com.abhisheksamuely.ipl.models.Team;
import com.abhisheksamuely.ipl.repositories.MatchRepository;
import com.abhisheksamuely.ipl.repositories.TeamRepository;

@CrossOrigin
@RestController
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/teams")
    public Iterable<Team> getAllTeam() {
        return teamRepository.findAll();
    }

	
	@GetMapping("/teams/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));

		return team;
	}
	
	@GetMapping("/teams/{teamName}/matches")
	public List<Match>getMatchesForTeam(@PathVariable String teamName, @RequestParam(value = "year") int year){
		try {
			long startDate = new java.text.SimpleDateFormat("MM/dd/yyyy").parse("01/01/"+year).getTime();
			long endDate = new java.text.SimpleDateFormat("MM/dd/yyyy").parse("01/01/"+(year+1)).getTime();
			return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
