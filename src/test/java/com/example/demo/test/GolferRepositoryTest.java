package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.golfer.entity.Golfer;
import com.example.demo.golfer.repository.GolferRepository;

@DataJpaTest
public class GolferRepositoryTest {

	@Autowired
	private GolferRepository golferRepository;
	
	@Test
	public void testFindAll() {
		List<Golfer> golfers = (List<Golfer>) golferRepository.findAll();
		
		assertEquals(golfers.size(), 8);
		
		golfers.forEach(golfer -> System.out.println(golfer));
	}
	
	@Test
	public void testFindByUserName() {
		String userName = "CHARLIE";
		List<Golfer> golfers = (List<Golfer>) golferRepository.findByUserName(userName);
		
		assertEquals(golfers.size(), 1);
		assertEquals(golfers.get(0).getUserName(), userName);
		
		golfers.forEach(golfer -> System.out.println(golfer));
	}
}
