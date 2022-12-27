package com.atanu.bet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.atanu.bet.dto.BetDto;
import com.atanu.bet.dto.Status;
import com.atanu.bet.model.Bet;

@SpringBootTest(classes = BetServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BetServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldPlaceBet() {
		Bet bet = Bet.builder().number(50).bet(new BigDecimal(40.5)).build();
		ResponseEntity<BetDto> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/v1/bet/players/1", bet, BetDto.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertTrue(responseEntity.getBody().getStatus() == Status.WIN
				|| responseEntity.getBody().getStatus() == Status.LOSE);
	}

}
