package com.atanu.bet.model;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Bet {

	@NotNull(message = "bet cannot be empty")
	private BigDecimal bet;

	@Min(value = 1, message = "Number cannot be less than 1")
	@Max(value = 100, message = "Number cannot be greater than 100")
	@NotNull(message = "number cannot be empty")
	private Integer number;
}
