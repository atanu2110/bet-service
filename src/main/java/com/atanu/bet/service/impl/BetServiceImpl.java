package com.atanu.bet.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.dto.Status;
import com.atanu.bet.model.Bet;
import com.atanu.bet.service.BetService;
import com.atanu.bet.util.BetUtil;

@Service
public class BetServiceImpl implements BetService {

	private static final Logger LOG = LoggerFactory.getLogger(BetServiceImpl.class);

	@Override
	public BetDto placeBet(int playerId, Bet bet) {
		LOG.info("Placing bet for player : {} bet : {} number : {}", playerId, bet.getBet(), bet.getNumber());

		if (BetUtil.generateRandom() < bet.getNumber())
			return BetDto.builder().status(Status.WIN).amount(getOdds(bet.getNumber()).multiply(bet.getBet())).build();

		return BetDto.builder().status(Status.LOSE).amount(new BigDecimal(0)).build();
	}

	private BigDecimal getOdds(Integer number) {
		return new BigDecimal(99).divide(new BigDecimal(100 - number), 2, RoundingMode.HALF_DOWN);
	}

}
