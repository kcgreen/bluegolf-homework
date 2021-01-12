package com.example.demo.tournament.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TOURNAMENTS")
public class Tournament {

	public enum Stage {
		LOCAL,
		REGIONAL,
		NATIONAL,
		CHAMPIONSHIP
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	@Column(name = "PLACE", nullable = false)
	private String place;
	@Column(name = "STARTDATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@Column(name = "TOURSTAGE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Stage tourStage;
	@Column(name = "TOURNEXT", nullable = false)
	private long tourNext;
	
	// Constructors
	public Tournament () {
		;
	}
	
	public Tournament (String name, String place, Date startDate, Stage tourStage, long tourNext) {
		this.name = name;
		this.place = place;
		this.startDate = startDate;
		this.tourStage = tourStage;
		this.tourNext = tourNext;
	}
	
	
	// Getters
	public long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getPlace() {
		return this.place;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public Stage getTourStage() {
		return this.tourStage;
	}
	public long getTourNext() {
		return this.tourNext;
	}
	
	// Setters
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setTourStage(Stage tourStage) {
		this.tourStage = tourStage;
	}
	public void getTourNext(long tourNext) {
		this.tourNext = tourNext;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + Long.toString(this.id) + "\"," +
				"\"name\":\"" + this.name + "\"," +
				"\"place\":\"" + this.place + "\"," +
				"\"startDate\":\"" + this.startDate + "\"," +
				"\"tourStage\":\"" + this.tourStage + "\"," +
				"\"tourNext\":\"" + this.tourNext + "\"}";
	}

}
