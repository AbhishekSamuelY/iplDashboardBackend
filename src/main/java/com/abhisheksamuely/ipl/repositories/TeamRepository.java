package com.abhisheksamuely.ipl.repositories;

import org.springframework.data.repository.CrudRepository;
import com.abhisheksamuely.ipl.models.Team;

public interface TeamRepository extends CrudRepository<Team,Long>{
	Team findByTeamName(String teamName);
}
