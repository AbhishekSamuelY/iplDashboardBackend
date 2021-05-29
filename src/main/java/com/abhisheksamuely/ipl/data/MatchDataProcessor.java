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
package com.abhisheksamuely.ipl.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.abhisheksamuely.ipl.models.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
		Match match = new Match();
	    match.setId(Long.parseLong(matchInput.getId()));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = sdf.parse(matchInput.getDate());
	    match.setDate(date.getTime());
	    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
	    match.setVenue(matchInput.getVenue());
	    //set toss winner and choose to bat as team 1
	    String firstInningsTeam, secondInningsTeam;
	    if(matchInput.getToss_decision().equalsIgnoreCase("bat")) {
	    	firstInningsTeam = matchInput.getToss_winner();
	    	secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
	    } else {
	    	secondInningsTeam = matchInput.getToss_winner();
	    	firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
	    }
	    match.setTeam1(firstInningsTeam);
	    match.setTeam2(secondInningsTeam);
	    match.setTossWinner(matchInput.getToss_winner());
	    match.setTossDecision(matchInput.getToss_decision());
	    match.setMatchWinner(matchInput.getWinner());
	    match.setResult(matchInput.getResult());
	    match.setResultMargin(matchInput.getResult_margin());
	    match.setUmpire1(matchInput.getUmpire1());
	    match.setUmpire2(matchInput.getUmpire2());
	    
	    log.info("Converting (" + matchInput + ") into (" + match + ")");

	    return match;
	  }
}