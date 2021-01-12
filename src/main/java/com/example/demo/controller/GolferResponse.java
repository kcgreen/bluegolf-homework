package com.example.demo.controller;

public class GolferResponse {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String tourName;
	private String tourStage;
	private String tourStatus;

	// Constructors
	public GolferResponse () {
		;
	}
	
	public GolferResponse(String userName, String firstName, String lastName, String tourName, String tourStage, String tourStatus) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tourName = tourName;
		this.tourStage = tourStage;
		this.tourStatus = tourStatus;
	}
	
	
	// Getters
	public String getUserName() {
		return this.userName;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getTourName() {
		return this.tourName;
	}
	public String getTourStage() {
		return this.tourStage;
	}
	public String getTourStatus() {
		return this.tourStatus;
	}
	
	// Setters
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public void setTourStage(String tourStage) {
		this.tourStage = tourStage;
	}
	public void setTourStatus(String tourStatus) {
		this.tourStatus = tourStatus;
	}
	
	@Override
	public String toString() {
		return "{\"userName\":\"" + this.userName + "\"," +
				"\"firstName\":\"" + this.firstName + "\"," +
				"\"lastName\":\"" + this.lastName + "\"," +
				"\"tourName\":\"" + this.tourName + "\"," +
				"\"tourStage\":\"" + this.tourStage + "\"," +
				"\"tourStatus\":\"" + this.tourStatus + "\"}";
	}

}
