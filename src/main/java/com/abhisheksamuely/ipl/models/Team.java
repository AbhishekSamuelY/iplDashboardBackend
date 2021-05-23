package com.abhisheksamuely.ipl.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String teamName;
	private long totalMatches;
	private long totalWins;
	@Transient
	private List<Match> latestMatches;
	
	public Team() {
	}

	public Team(String teamName, long totalMatches) {
		super();
		this.teamName = teamName;
		this.totalMatches = totalMatches;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(long totalMatches) {
		this.totalMatches = totalMatches;
	}

	public long getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(long totalWins) {
		this.totalWins = totalWins;
	}

	public List<Match> getLatestMatches() {
		return latestMatches;
	}

	public void setLatestMatches(List<Match> latestMatches) {
		this.latestMatches = latestMatches;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", totalMatches=" + totalMatches + "]";
	}
	
}