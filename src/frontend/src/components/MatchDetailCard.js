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
import './matchDetailCard.scss';
import {React} from 'react';
import {Link} from 'react-router-dom';

export const MatchDetailCard = ({teamName, match}) => {
  if(!match) return null;
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isMatchWon = teamName === match.matchWinner;
    return (
      <div className={isMatchWon ? 'MatchDetailCard won-card' : 'MatchDetailCard lost-card'}>
      <div className="main-details">
      <span className="vs">vs</span><h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
      <h2 className="match-date">{match.date}</h2>
      <h3 className="match-venue">at {match.venue}</h3>
      <h3 className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
      </div>
      <div className="additional-details">
      <h3>First Innings</h3>
      <p>{match.team1}</p>
      <h3>Second Innings</h3>
      <p>{match.team2}</p>
      <h3>Toss winner</h3>
      <p>{match.tossWinner} and chose to {match.tossDecision}</p>
      <h3>Man of the match</h3>
      <p>{match.playerOfMatch}</p>
      <h3>Umpires</h3>
      <p>{match.umpire1}<br/>{match.umpire2}</p>
      </div>
    </div>
  );
}
