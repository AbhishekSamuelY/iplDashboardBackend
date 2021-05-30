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
import './matchPage.scss';
import {React,useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { YearSelector } from '../components/YearSelector';

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const {teamName, year} = useParams();
    useEffect(
        () => {
            const fetchMatches = async() => {
                const response = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
                console.log(year);
            };
            fetchMatches();
        },[teamName, year]
    );

  return (
    <div className="MatchPage">
     <div className="year-selector">
       <h4>Choose Season</h4>
      <YearSelector teamName={teamName}/>
     </div>
     <div>
     <h1 className="page-heading">{teamName} matches in {year}</h1>
     {matches.map(match => <MatchDetailCard key={match.id} teamName={teamName} match={match} />)}
     </div>
    </div>
  );
}