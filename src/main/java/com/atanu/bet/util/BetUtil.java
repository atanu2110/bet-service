package com.atanu.bet.util;

import java.util.Random;

public class BetUtil {

	/**
	 * generate random number between 1-100
	 * @return random number
	 */
	public static int generateRandom() {
		Random random = new Random();
		return random.nextInt(100);
	}

}
