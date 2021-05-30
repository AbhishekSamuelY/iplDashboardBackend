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
import './teamPage.scss';
import {React,useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';
import {Link} from 'react-router-dom';

export const TeamPage = () => {
    const [team, setTeam] = useState({matches:[]});
    const {teamName} = useParams();
    useEffect(
        ()=>{
            const fetchTeam = async() =>{
                const response = await fetch(`http://localhost:8080/teams/${teamName}`);
                const data = await response.json();
                setTeam(data);
            };
            fetchTeam();
        },[teamName]
    );
    if(!team || !team.teamName){
        return <h1>404 Team not found!</h1>
    }
  return (
    <div className="TeamPage">
        <div className="team-name-section"> <h1 className="team-name">{team.teamName}</h1></div>
        <div className="win-loss-section">
        <PieChart
  data={[
    { title: 'Wins', value: team.totalWins, color: '#4da375' },
    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
  ]}
/>
        </div>
      <div className="match-details-section">
      <h3>Latest Match: </h3>
          <MatchDetailCard teamName={team.teamName} match={team.matches[0]}/></div>
      {team.matches.slice(1).map(match => <MatchSmallCard key={match.id} teamName={team.teamName} match={match}/>)}
        <div className="more-link">
        <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More ></Link>
        </div>
    </div>
  );
}
