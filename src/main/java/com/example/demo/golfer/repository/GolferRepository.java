package com.example.demo.golfer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.golfer.entity.Golfer;

@Repository
public interface GolferRepository extends CrudRepository<Golfer,Long> {
	List<Golfer> findByUserName(@Param("username") String userName);
	
}
