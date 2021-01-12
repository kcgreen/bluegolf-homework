package com.example.demo.tournament.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.tournament.entity.Tournament;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament,Long> {
	List<Tournament> findByName(@Param("name") String name);
	
	@Query("SELECT t FROM Tournament t WHERE t.startDate > :startdate")
	List<Tournament> findAllWithStartDateAfter(@Param("startdate") Date startDate);
	
}
