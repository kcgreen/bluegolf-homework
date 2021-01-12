package com.example.demo.golfer.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GOLFERS")
public class Golfer {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "RESULTID", nullable = false)
	private long resultId;
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String userName;
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;
	
	// Constructors
	public Golfer () {
		;
	}
	
	public Golfer (long resultId, String userName, String firstName, String lastName) {
		this.resultId = resultId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	// Getters
	public long getId() {
		return this.id;
	}
	public long getResultId() {
		return this.resultId;
	}
	public String getUserName() {
		return this.userName;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	// Setters
	public void setId(long id) {
		this.id = id;
	}
	public void setResultId(long resultId) {
		this.resultId = resultId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + Long.toString(this.id) + "\"," +
				"\"resultId\":\"" + Long.toString(this.resultId) + "\"," +
				"\"userName\":\"" + this.userName + "\"," +
				"\"firstName\":\"" + this.firstName + "\"," +
				"\"lastName\":\"" + this.lastName + "\"}";
	}

}
