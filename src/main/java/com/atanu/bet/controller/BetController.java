package com.atanu.bet.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.model.Bet;
import com.atanu.bet.service.BetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bet")
public class BetController {

	private final BetService betService;

	/**
	 * Service to place player bet
	 * 
	 * @param playerId
	 * @param bet
	 * @return win status
	 */
	@PostMapping("/players/{playerId}")
	public ResponseEntity<BetDto> placeBet(@PathVariable("playerId") int playerId, @Valid @RequestBody Bet bet) {
		return new ResponseEntity<>(betService.placeBet(playerId, bet), HttpStatus.OK);
	}
}
