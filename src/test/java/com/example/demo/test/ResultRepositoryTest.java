package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.result.entity.Result;
import com.example.demo.result.repository.ResultRepository;

@DataJpaTest
public class ResultRepositoryTest {

	@Autowired
	private ResultRepository resultRepository;
	
	@Test
	public void testFindAll() {
		List<Result> results = (List<Result>) resultRepository.findAll();
		
		assertEquals(results.size(), 1);
		
		results.forEach(result -> System.out.println(result));
	}
	
	@Test
	public void testFindAllByGolferId() {
		long golferId = 2;
		List<Result> results = (List<Result>) resultRepository.findAllByGolferId(golferId);
		
		assertEquals(results.size(), 1);
		
		results.forEach(result -> System.out.println(result));
	}
	
	@Test
	public void testFindAllByTourId() {
		long tourId = 1;
		List<Result> results = (List<Result>) resultRepository.findAllByTourId(tourId);
		
		assertEquals(results.size(), 1);
		
		results.forEach(result -> System.out.println(result));
	}
}
