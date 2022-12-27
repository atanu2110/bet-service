package com.atanu.bet.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BetDto {

	private Status status;
	private BigDecimal amount;
}
