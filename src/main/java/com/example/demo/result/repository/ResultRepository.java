package com.example.demo.result.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.result.entity.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result,Long> {
	List<Result> findAllByGolferId(@Param("golferId") long golferId);
	
	List<Result> findAllByTourId(@Param("tourId") long tourId);

}
