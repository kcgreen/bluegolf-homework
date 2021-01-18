package com.example.demo.controller;

public class TournamentResultRequest {
	
	private int tournamentIndex;
	private String tournamentResult;
	
	// Constructors
	public TournamentResultRequest () {
		;
	}
	
	public TournamentResultRequest(int tournamentIndex, String tournamentResult) {
		this.tournamentIndex = tournamentIndex;
		this.tournamentResult = tournamentResult;
	}
	
	
	// Getters
	public int getTournamentIndex() {
		return this.tournamentIndex;
	}
	public String getTournamentResult() {
		return this.tournamentResult;
	}
	
	// Setters
	public void setTournamentIndex(int tournamentIndex) {
		this.tournamentIndex = tournamentIndex;
	}
	public void setTournamentResult(String tournamentResult) {
		this.tournamentResult = tournamentResult;
	}
	
	@Override
	public String toString() {
		return "{\"tournamentIndex\":\"" + Integer.toString(this.tournamentIndex) + "\"," +
				"\"tournamentResult\":\"" + this.tournamentResult + "\"}";
	}

}
