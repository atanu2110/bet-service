package com.atanu.bet.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.dto.Status;
import com.atanu.bet.model.Bet;
import com.atanu.bet.service.BetService;

class BetServiceImplTest {

	@Test
	void placeBetTest() {
		Bet bet = Bet.builder().number(50).bet(new BigDecimal(40.5)).build();
		BetService betService = new BetServiceImpl();

		BetDto betResponse = betService.placeBet(1, bet);
		assertTrue(betResponse.getStatus() == Status.WIN || betResponse.getStatus() == Status.LOSE);
	}

}
