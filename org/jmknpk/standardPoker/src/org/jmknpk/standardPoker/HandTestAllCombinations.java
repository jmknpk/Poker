package org.jmknpk.standardPoker;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

public class HandTestAllCombinations {

	@Test
	public void test() {

		Hand[] valueHands = new Hand[2598960];
		int handIndex = 0;
		int maxRank = 0;
		for (int i = 0; i < 52-4; i++) {
			for (int j = i+1; j < 52-3; j++) {
				for (int k = j+1; k < 52-2; k++) {
					for (int l = k+1; l < 52-1; l++) {
						for (int m = l+1; m < 52; m++) {
							valueHands[handIndex++] = new Hand ( new CardSet (new Card(i), new Card(j), new Card(k), new Card(l), new Card(m)));
							if (valueHands[handIndex-1].getHandRank() > maxRank) maxRank = valueHands[handIndex-1].getHandRank();
						}
					}
				}
			}
		}
		assertEquals(maxRank,7461);
		assertEquals(handIndex,2598960);

		Arrays.sort(valueHands,Hand.RankThenValueComparator);  // Sort by handValue
		
		for (int i = 0; i < valueHands.length; i++) {
			System.out.println(Integer.toString(valueHands[i].getHandRank())+","+Integer.toString(valueHands[i].getValue())+","+valueHands[i].getCardSet().getAbbreviation());
		}

	}
}
