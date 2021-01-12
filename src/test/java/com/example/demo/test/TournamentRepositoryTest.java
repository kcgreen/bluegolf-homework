package com.example.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.tournament.entity.Tournament;
import com.example.demo.tournament.repository.TournamentRepository;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class TournamentRepositoryTest {
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Test
	public void testFindAll() {
		List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
		
		assertEquals(tournaments.size(), 15);
		
		tournaments.forEach(tournament -> System.out.println(tournament));
	}
	
	@Test
	public void testFindByName() {
		String name = "Local 8 Name";
		List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findByName(name);
		
		assertEquals(tournaments.size(), 1);
		assertEquals(tournaments.get(0).getName(), name);
		
		tournaments.forEach(tournament -> System.out.println(tournament));
	}
	
	@Test
	public void testFindAllWithStartDateAfter() {
		Date today = new Date();
		List<Tournament> tournaments = 
				(List<Tournament>) tournamentRepository.findAllWithStartDateAfter(today);
		
		assertEquals(tournaments.size(), 15);
		
		tournaments.forEach(tournament -> System.out.println(tournament));
		
		try {
			Date tourDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-07");
			tournaments = 
					(List<Tournament>) tournamentRepository.findAllWithStartDateAfter(tourDate);
			
			assertEquals(tournaments.size(), 7);
			
			tournaments.forEach(tournament -> System.out.println(tournament));
		} catch (ParseException pe) {
			pe.fillInStackTrace();
		}
	}
}
