package com.example.demo.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
		this.mockMvc.perform(post("/golfer/v1/result/TOMMY?tourindex=4&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(404));
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=4&tourresult=XYZ")).andDo(print()).andExpect(status().is(400));
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=0&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(400));
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=9&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(400));
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=4&tourresult=REGISTERED")).andDo(print()).andExpect(status().isOk());

		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=4&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(400));
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=4&tourresult=DIDNOTQUALIFY")).andDo(print()).andExpect(status().isOk());
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=4&tourresult=XYZ")).andDo(print()).andExpect(status().is(400));
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=0&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(400));
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=9&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(400));
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=4&tourresult=REGISTERED")).andDo(print()).andExpect(status().is(400));
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=5&tourresult=REGISTERED")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=5&tourresult=QUALIFIED")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=11&tourresult=QUALIFIED")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=14&tourresult=DIDNOTQUALIFY")).andDo(print()).andExpect(status().isOk());

		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=14&tourresult=DIDNOTQUALIFY")).andDo(print()).andExpect(status().is(400));
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=13&tourresult=REGISTERED")).andDo(print()).andExpect(status().isOk());

		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=15&tourresult=XYZ")).andDo(print()).andExpect(status().is(400));
		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=13&tourresult=QUALIFIED")).andDo(print()).andExpect(status().isOk());		
		this.mockMvc.perform(post("/golfer/v1/result/LINUS?tourindex=15&tourresult=QUALIFIED")).andDo(print()).andExpect(status().isOk());
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


