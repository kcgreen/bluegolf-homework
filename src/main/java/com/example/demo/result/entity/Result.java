package com.example.demo.result.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULTS")
public class Result {
	
	public enum Status {
		NOTREGISTERED,
		REGISTERED,
		DIDNOTQUALIFY,
		QUALIFIED
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "GOLFERID", nullable = false)
	private long golferId;
	@Column(name = "TOURID", nullable = false)
	private long tourId;
	@Column(name = "TOURSTATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status tourStatus;
	
	// Constructors
	public Result () {
		;
	}
	
	public Result (long golferId, long tourId, Status tourStatus) {
		this.golferId = golferId;
		this.tourId = tourId;
		this.tourStatus = tourStatus;
	}
	
	
	// Getters
	public long getId() {
		return this.id;
	}
	public long getGolferId() {
		return this.golferId;
	}
	public long getTourId() {
		return this.tourId;
	}
	public Status getTourStatus() {
		return this.tourStatus;
	}
	
	// Setters
	public void setId(long id) {
		this.id = id;
	}
	public void setGolferId(long golferId) {
		this.golferId = golferId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	public void setTourStatus(Status tourStatus) {
		this.tourStatus = tourStatus;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + Long.toString(this.id) + "\"," +
				"\"golferId\":\"" + Long.toString(this.golferId) + "\"," +
				"\"tourId\":\"" + Long.toString(this.tourId) + "\"," +
				"\"tourStatus\":\"" + this.tourStatus + "\"}";
	}

}
