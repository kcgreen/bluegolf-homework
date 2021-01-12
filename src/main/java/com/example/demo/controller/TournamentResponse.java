package com.example.demo.controller;

public class TournamentResponse {
	
	private String index;
	private String name;
	private String place;
	private String startDate;
	private String tourStage;
	private String next;

	// Constructors
	public TournamentResponse () {
		;
	}
	
	public TournamentResponse(String index, String name, String place, String startDate, String tourStage, String next) {
		this.index = index;
		this.name = name;
		this.place = place;
		this.startDate = startDate;
		this.tourStage = tourStage;
		this.next = next;
	}
	
	
	// Getters
	public String getIndex() {
		return this.index;
	}
	public String getName() {
		return this.name;
	}
	public String getPlace() {
		return this.place;
	}
	public String getStartDate() {
		return this.startDate;
	}
	public String getTourStage() {
		return this.tourStage;
	}
	public String getNext() {
		return this.next;
	}
	
	// Setters	
	public void setIndex(String index) {
		this.index = index;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setTourStage(String tourStage) {
		this.tourStage = tourStage;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "{\"index\":\"" + this.index + "\"," +
				"\"name\":\"" + this.name + "\"," +
				"\"place\":\"" + this.place + "\"," +
				"\"starDate\":\"" + this.startDate + "\"," +
				"\"tourStage\":\"" + this.tourStage + "\"," +
				"\"next\":\"" + this.next + "\"}";
	}
		
}
