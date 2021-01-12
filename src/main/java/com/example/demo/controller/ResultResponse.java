package com.example.demo.controller;

public class ResultResponse {
	
	private String tourName;
	private String tourPlace;
	private String tourStartDate;
	private String tourStage;
	private String tourStatus;
	
	// Constructors
	public ResultResponse () {
		;
	}
	
	public ResultResponse(String tourName, String tourPlace, String tourStartDate, String tourStage, String tourStatus) {
		this.tourName = tourName;
		this.tourPlace = tourPlace;
		this.tourStartDate = tourStartDate;
		this.tourStage = tourStage;
		this.tourStatus = tourStatus;
	}
	
	
	// Getters
	public String getTourName() {
		return this.tourName;
	}
	public String getTourPlace() {
		return this.tourPlace;
	}
	public String getTourStartDate() {
		return this.tourStartDate;
	}
	public String getTourStage() {
		return this.tourStage;
	}
	public String getTourStatus() {
		return this.tourStatus;
	}
	
	// Setters	
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public void setTourPlace(String tourPlace) {
		this.tourPlace = tourPlace;
	}
	public void setTourStartDate(String tourStartDate) {
		this.tourStartDate = tourStartDate;
	}
	public void setTourStage(String tourStage) {
		this.tourStage = tourStage;
	}
	public void setTourSatus(String tourStatus) {
		this.tourStatus = tourStatus;
	}
	
	@Override
	public String toString() {
		return "{\"tourName\":\"" + this.tourName + "\"," +
				"\"tourPlace\":\"" + this.tourPlace + "\"," +
				"\"tourStartDate\":\"" + this.tourStartDate + "\"," +
				"\"tourStage\":\"" + this.tourStage + "\"," +
				"\"tourStatus\":\"" + this.tourStatus + "\"}";
	}
	
}
