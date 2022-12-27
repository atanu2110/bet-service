package com.atanu.bet.service;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.model.Bet;

public interface BetService {

	BetDto placeBet(int playerId, Bet bet);
	
}
