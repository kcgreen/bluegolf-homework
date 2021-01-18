package com.example.demo.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.TournamentResultRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class GolferControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetGolferForGolfer() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/golfer/TOMMY")).andDo(print()).andExpect(status().is(404));
		this.mockMvc.perform(get("/golfer/v1/golfer/LINUS")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetResultsForGolfer() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/results/TOMMY")).andDo(print()).andExpect(status().is(404));
		this.mockMvc.perform(get("/golfer/v1/results/LINUS")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetTournamentsForGolfer() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/tournaments/TOMMY")).andDo(print()).andExpect(status().is(404));
		this.mockMvc.perform(get("/golfer/v1/tournaments/LINUS")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetTournaments() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/tournaments")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testPostResultForGolfer() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		TournamentResultRequest trr = new TournamentResultRequest();
		
		trr.setTournamentIndex(4);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/TOMMY").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(404));
		
		trr.setTournamentIndex(4);
		trr.setTournamentResult("XYZ");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		trr.setTournamentIndex(0);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		trr.setTournamentIndex(9);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		
		trr.setTournamentIndex(4);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());

		trr.setTournamentIndex(4);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		
		trr.setTournamentIndex(4);
		trr.setTournamentResult("DIDNOTQUALIFY");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());
		
		trr.setTournamentIndex(4);
		trr.setTournamentResult("XYZ");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		trr.setTournamentIndex(0);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		trr.setTournamentIndex(9);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		trr.setTournamentIndex(4);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		
		trr.setTournamentIndex(5);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());
		trr.setTournamentIndex(5);
		trr.setTournamentResult("QUALIFIED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());
		trr.setTournamentIndex(11);
		trr.setTournamentResult("QUALIFIED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());
		trr.setTournamentIndex(14);
		trr.setTournamentResult("DIDNOTQUALIFY");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());

		trr.setTournamentIndex(14);
		trr.setTournamentResult("DIDNOTQUALIFY");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		
		trr.setTournamentIndex(13);
		trr.setTournamentResult("REGISTERED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());

		trr.setTournamentIndex(15);
		trr.setTournamentResult("XYZ");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().is(400));
		
		trr.setTournamentIndex(13);
		trr.setTournamentResult("QUALIFIED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());		
		trr.setTournamentIndex(15);
		trr.setTournamentResult("QUALIFIED");
		this.mockMvc.perform(post("/golfer/v1/result/LINUS").contentType("application/json").characterEncoding("UTF-8").content(ow.writeValueAsString(trr))).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void testGetGolferForGolfer2() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/golfer/LINUS")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetResultsForGolfer2() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/results/LINUS")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetTournamentsForGolfer2() throws Exception {
		this.mockMvc.perform(get("/golfer/v1/tournaments/LINUS")).andDo(print()).andExpect(status().isOk());
	}
	
}


