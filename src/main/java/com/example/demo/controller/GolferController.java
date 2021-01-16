package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.golfer.entity.Golfer;
import com.example.demo.golfer.repository.GolferRepository;
import com.example.demo.result.entity.Result;
import com.example.demo.result.repository.ResultRepository;
import com.example.demo.tournament.entity.Tournament;
import com.example.demo.tournament.repository.TournamentRepository;

@RestController
@RequestMapping("/golfer/v1")
public class GolferController {
	
	@Autowired
	private GolferRepository golferRepository;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	// Get Golfer
	@GetMapping("/golfer/{username}")
	public GolferResponse getGolfer(@PathVariable String username) {
		GolferResponse golferResponse = new GolferResponse();
		List<Golfer> golfers = (List<Golfer>) golferRepository.findByUserName(username);
		// only one golfer with this username
		if (golfers.size() == 1) {
			List<Result> results = (List<Result>) resultRepository.findAll();
			List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
			// index of current result
			int resultId = (int)golfers.get(0).getResultId();
			// current result
			if (resultId > 0) {
				// get tour info
				int tourId = (int)results.get(resultId - 1).getTourId();
				// pack response
				golferResponse.setUserName(golfers.get(0).getUserName());
				golferResponse.setFirstName(golfers.get(0).getFirstName());
				golferResponse.setLastName(golfers.get(0).getLastName());
				golferResponse.setTourName((tourId > 0)?tournaments.get(tourId - 1).getName():"");
				golferResponse.setTourStage((tourId > 0)?tournaments.get(tourId - 1).getTourStage().toString():"");
				golferResponse.setTourStatus((resultId > 0)?results.get(resultId - 1).getTourStatus().toString():"");
			// no current result, no tour info
			} else {
				// pack response
				golferResponse.setUserName(golfers.get(0).getUserName());
				golferResponse.setFirstName(golfers.get(0).getFirstName());
				golferResponse.setLastName(golfers.get(0).getLastName());
				golferResponse.setTourName("");
				golferResponse.setTourStage("");
				golferResponse.setTourStatus("");
			}
			return golferResponse;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Golfer not found");
		}
	}
	
	// Get Golfer Results
	@GetMapping("/results/{username}")
	public List<ResultResponse> getGolferResults(@PathVariable String username) {
		List<ResultResponse> resultResponse = new ArrayList<ResultResponse>();
		Comparator<ResultResponse> compareByStartDate = (ResultResponse rr1, ResultResponse rr2) -> rr1.getTourStartDate().compareTo( rr2.getTourStartDate());
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Golfer> golfers = (List<Golfer>) golferRepository.findByUserName(username);
		// only one golfer with this username
		if (golfers.size() == 1) {
			List<Result> results = (List<Result>) resultRepository.findAllByGolferId(golfers.get(0).getId());
			List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
			results.forEach(result -> {
				// get tour info for each result
				int tourId = (int)result.getTourId();
				// pack response
				resultResponse.add(new ResultResponse(
						tournaments.get(tourId - 1).getName(),
						tournaments.get(tourId - 1).getPlace(), 
						dateFormatter.format(tournaments.get(tourId - 1).getStartDate()), 
						tournaments.get(tourId - 1).getTourStage().toString(),
						result.getTourStatus().toString()));
			});
			// Return results in reverse order so newest result is first
			resultResponse.sort(compareByStartDate.reversed());
			return resultResponse;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Golfer not found");
		}
	}
	
	// Post New Result
	@PostMapping("/result/{username}")
	public GolferResponse postGolferResult(@PathVariable String username, @RequestParam String tourindex, @RequestParam String tourresult) {
		GolferResponse golferResponse = new GolferResponse();
		List<Golfer> golfers = (List<Golfer>) golferRepository.findByUserName(username);
		// one golfer with this username
		if (golfers.size() == 1) {
			List<Result> results = (List<Result>) resultRepository.findAll();
			List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAllWithStartDateAfter(new Date());
			// index of current result
			int resultId = (int)golfers.get(0).getResultId();
			// current result
			if (resultId > 0) {
				// get tour info 
				int tourId = (int)results.get(resultId - 1).getTourId();
				// current tourindex same as current tour
				if (Integer.parseInt(tourindex) == tourId) {
					
					// current status registered can move to qualified or didnotqualify
					if (results.get(resultId - 1).getTourStatus().compareTo(Result.Status.REGISTERED)==0) {					
						// qualified at current stage
						if (tourresult.toUpperCase().equals(Result.Status.QUALIFIED.toString())) {
							// requested result for current tour info
							results.get(resultId - 1).setTourStatus(Result.Status.QUALIFIED);
							// save requested result
							Result resultSave = resultRepository.save(results.get(resultId - 1));
							
							// move to the next tour stage
							if (tournaments.get(tourId - 1).getTourStage().compareTo(Tournament.Stage.CHAMPIONSHIP)==0) {
								// this is the championship stage, no more stages
								
								// pack response
								golferResponse.setUserName(golfers.get(0).getUserName());
								golferResponse.setFirstName(golfers.get(0).getFirstName());
								golferResponse.setLastName(golfers.get(0).getLastName());
								golferResponse.setTourName(tournaments.get(tourId - 1).getName());
								golferResponse.setTourStage(tournaments.get(tourId - 1).getTourStage().toString());
								golferResponse.setTourStatus(resultSave.getTourStatus().toString());
							} else {
								// get tour info and automatically register for next result
								int tourNext = (int)tournaments.get(tourId - 1).getTourNext();
								// save info for next result
								Result resultNext = resultRepository.save(new Result(
																				golfers.get(0).getId(),
																				tourNext,
																				Result.Status.REGISTERED));
								
								// save next result index to golfer
								golfers.get(0).setResultId(resultNext.getId());
								golferRepository.save(golfers.get(0));
								
								// pack response
								golferResponse.setUserName(golfers.get(0).getUserName());
								golferResponse.setFirstName(golfers.get(0).getFirstName());
								golferResponse.setLastName(golfers.get(0).getLastName());
								golferResponse.setTourName(tournaments.get(tourNext - 1).getName());
								golferResponse.setTourStage(tournaments.get(tourNext - 1).getTourStage().toString());
								golferResponse.setTourStatus(resultNext.getTourStatus().toString());
							}
						// didnotqualify at current stage
						} else if (tourresult.toUpperCase().equals(Result.Status.DIDNOTQUALIFY.toString())) {
							// requested result for current tour info
							results.get(resultId - 1).setTourStatus(Result.Status.DIDNOTQUALIFY);
							// save requested result
							Result resultSave = resultRepository.save(results.get(resultId - 1));
							
							// pack response
							golferResponse.setUserName(golfers.get(0).getUserName());
							golferResponse.setFirstName(golfers.get(0).getFirstName());
							golferResponse.setLastName(golfers.get(0).getLastName());
							golferResponse.setTourName(tournaments.get(tourId - 1).getName());
							golferResponse.setTourStage(tournaments.get(tourId - 1).getTourStage().toString());
							golferResponse.setTourStatus(resultSave.getTourStatus().toString());
						// bad request
						} else {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - request result");
						}
					// bad request
					} else {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - current result");
					}
					
				// tourindex different from current tour
				} else {
					
					// current status didnotqualify can move to registered	
					if (results.get(resultId - 1).getTourStatus().compareTo(Result.Status.DIDNOTQUALIFY)==0) {
						if (tourresult.toUpperCase().equals(Result.Status.REGISTERED.toString())) {
							// valid tourindex
							if (Integer.parseInt(tourindex) > 0 && Integer.parseInt(tourindex) < tournaments.size() + 1) {
								// get tour info for requested result
								int tourNext = Integer.parseInt(tourindex);
								// can only register for same stage, different tour
								if (tournaments.get(tourNext - 1).getTourStage().compareTo(tournaments.get(tourId - 1).getTourStage())==0 &&
										results.stream().filter(o -> (((Result)o).getTourId()==tourNext &&
																	  ((Result)o).getGolferId()==golfers.get(0).getId())).toArray().length==0) {
									// save info for next result
									Result resultNext = resultRepository.save(new Result(
																					golfers.get(0).getId(),
																					tourNext,
																					Result.Status.REGISTERED));
									
									// save next result index to golfer
									golfers.get(0).setResultId(resultNext.getId());
									golferRepository.save(golfers.get(0));
									
									// pack response
									golferResponse.setUserName(golfers.get(0).getUserName());
									golferResponse.setFirstName(golfers.get(0).getFirstName());
									golferResponse.setLastName(golfers.get(0).getLastName());
									golferResponse.setTourName(tournaments.get(tourNext - 1).getName());
									golferResponse.setTourStage(tournaments.get(tourNext - 1).getTourStage().toString());
									golferResponse.setTourStatus(resultNext.getTourStatus().toString());
								// not same stage
								} else {
									throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - not same stage or not new request index");
								}
							// tourindex presented is not valid
							} else {
								throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - request index");
							}
						// bad request
						} else {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - request result");
						}
					// bad request
					} else {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - current result");
					}
				}
				
			// no current result, no tour info
			} else {
				
				// no current result can move to registered
				if (tourresult.toUpperCase().equals(Result.Status.REGISTERED.toString())) {
					// valid tourindex
					if (Integer.parseInt(tourindex) > 0 && Integer.parseInt(tourindex) < tournaments.size() + 1) {
						// get tour info for requested result
						int tourNext = Integer.parseInt(tourindex);
						// can only register for local stage
						if (tournaments.get(tourNext - 1).getTourStage().compareTo(Tournament.Stage.LOCAL)==0) {
							// save info for next result
							Result resultNext = resultRepository.save(new Result(
																			golfers.get(0).getId(),
																			tourNext,
																			Result.Status.REGISTERED));
							
							// save result index to golfer
							golfers.get(0).setResultId(resultNext.getId());
							golferRepository.save(golfers.get(0));
							
							// pack response
							golferResponse.setUserName(golfers.get(0).getUserName());
							golferResponse.setFirstName(golfers.get(0).getFirstName());
							golferResponse.setLastName(golfers.get(0).getLastName());
							golferResponse.setTourName(tournaments.get(tourNext - 1).getName());
							golferResponse.setTourStage(tournaments.get(tourNext - 1).getTourStage().toString());
							golferResponse.setTourStatus(resultNext.getTourStatus().toString());
							
						// not local stage
						} else {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - not local stage");
						}
					// tourindex presented is not valid
					} else {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - request index");
					}
				// bad request
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request - request result");
				}
			}
			
			return golferResponse;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Golfer not found");
		}
	}
	
	// Get Golfer Eligible Tournaments
	@GetMapping("/tournaments/{username}")
	public List<TournamentResponse> getGolferTournaments(@PathVariable String username) {
		List<TournamentResponse> tournamentResponse = new ArrayList<TournamentResponse>();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Golfer> golfers = (List<Golfer>) golferRepository.findByUserName(username);
		// only one golfer with this username
		if (golfers.size() == 1) {
			List<Result> results = (List<Result>) resultRepository.findAll();
			List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAllWithStartDateAfter(new Date());
			// index of current result
			int resultId = (int)golfers.get(0).getResultId();
			// current result
			if (resultId > 0) {
				// get tour info
				int tourId = (int)results.get(resultId - 1).getTourId();
				tournaments.forEach(tournament -> {
					// get tour info for each future tour >= current stage, and exclude tours where golfer didnotqualify
					if (tournament.getTourStage().compareTo(tournaments.get(tourId - 1).getTourStage())>=0 &&
							results.stream().filter(o -> (((Result)o).getTourId()==tournament.getId() &&
							                              ((Result)o).getGolferId()==golfers.get(0).getId() &&
									                      ((Result)o).getTourStatus()==Result.Status.DIDNOTQUALIFY)).toArray().length==0) {
						tournamentResponse.add(new TournamentResponse(
								Long.toString(tournament.getId()),
								tournament.getName(),
								tournament.getPlace(),
								dateFormatter.format(tournament.getStartDate()),
								tournament.getTourStage().toString(),
								Long.toString(tournament.getTourNext())));
					}
				});
			// no current result, return all future tours
			} else {
				tournaments.forEach(tournament -> {
					tournamentResponse.add(new TournamentResponse(
							Long.toString(tournament.getId()),
							tournament.getName(),
							tournament.getPlace(),
							dateFormatter.format(tournament.getStartDate()),
							tournament.getTourStage().toString(),
							Long.toString(tournament.getTourNext())));
				});
			}
			return tournamentResponse;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Golfer not found");
		}
	}
	
	// Get All Tournaments
	@GetMapping("/tournaments")
	public List<TournamentResponse> getTournament() {
		List<TournamentResponse> tournamentResponse = new ArrayList<TournamentResponse>();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
		// no golfer, return all tours
		tournaments.forEach(tournament -> {
			tournamentResponse.add(new TournamentResponse(
					Long.toString(tournament.getId()),
					tournament.getName(),
					tournament.getPlace(),
					dateFormatter.format(tournament.getStartDate()),
					tournament.getTourStage().toString(),
					Long.toString(tournament.getTourNext())));
		});
		return tournamentResponse;
	}

}
