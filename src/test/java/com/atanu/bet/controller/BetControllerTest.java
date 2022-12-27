package com.atanu.bet.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.dto.Status;
import com.atanu.bet.model.Bet;
import com.atanu.bet.service.BetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BetController.class)

class BetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BetService betService;

	BetDto loseBet = BetDto.builder().status(Status.LOSE).amount(new BigDecimal(0)).build();
	BetDto winBet = BetDto.builder().status(Status.WIN).amount(new BigDecimal(80.19)).build();

	@Test
	void shouldReturnLoseBet() throws Exception {
		Bet bet = Bet.builder().number(50).bet(new BigDecimal(40.5)).build();

		Mockito.when(betService.placeBet(1, bet)).thenReturn(loseBet);

		// when
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bet/players/{playerId}", 1).content(asJsonString(bet))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Status.LOSE.toString()));
	}
	
	@Test
	void shouldReturnWinBet() throws Exception {
		Bet bet = Bet.builder().number(50).bet(new BigDecimal(40.5)).build();

		Mockito.when(betService.placeBet(1, bet)).thenReturn(winBet);

		// when
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bet/players/{playerId}", 1).content(asJsonString(bet))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Status.WIN.toString()));
	}
	
	@Test
	void shouldReturnBadRequest() throws Exception {
		Bet bet = Bet.builder().number(150).bet(new BigDecimal(40.5)).build();

		// when
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bet/players/{playerId}", 1).content(asJsonString(bet))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
