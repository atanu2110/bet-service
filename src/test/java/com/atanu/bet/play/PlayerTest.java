package com.atanu.bet.play;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.model.Bet;
import com.atanu.bet.service.BetService;

@SpringBootTest
@ActiveProfiles("player")
class PlayerTest {

	@Autowired
	BetService betService;

	@Test
	void playMillionRounds() {
		int size = 1000000;
		int min = 1;
		int max = 100;

		DoubleAdder amountWon = new DoubleAdder();
		ExecutorService executor = Executors.newFixedThreadPool(24);

		List<Integer> randList = new Random().ints(size, min, max).boxed().collect(Collectors.toList());
		double amountPlayed = randList.stream().mapToDouble(Integer::doubleValue).sum();


		randList.forEach(x -> {
			executor.submit(() -> {
				BetDto betResponse = betService.placeBet(1, Bet.builder().number(x).bet(generateBet()).build());
				// System.out.println(betResponse.toString());
				amountWon.add(betResponse.getAmount().doubleValue());
			});
		});

		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
		}
		System.out.println("Amount Played : " + amountPlayed);
		System.out.println("Amount Won is : " + amountWon);
		
		// double rtp = (amountWon.doubleValue() / amountPlayed) * 100;

		assertEquals(1000000, randList.size());
	}

	public BigDecimal generateBet() {
		double leftLimit = 1D;
		double rightLimit = 100D;
		return new BigDecimal(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit)).setScale(2,
				RoundingMode.HALF_UP);
	}
}
