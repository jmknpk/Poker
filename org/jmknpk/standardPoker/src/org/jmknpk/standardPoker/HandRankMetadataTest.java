package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandRankMetadataTest {

	@Test
	public void test() {
		int numberOfHands;
		int numberOfHandsBelow;
		int numberOfHandsAbove;
		int[] handValues;
		String[] handAbbreviations;
		int recount = 0;
		
		HandRankMetadata data = new HandRankMetadata();
		assertTrue(data != null);
		for (int i = 0; i < HandRankMetadata.numberOfHandRanks; i++) {
			numberOfHands = data.getNumberOfHands(i);
			recount = recount + numberOfHands;
			numberOfHandsBelow = data.getNumberOfHandsBelow(i);
			numberOfHandsAbove = data.getNumberOfHandsAbove(i);
			handValues = new int[data.getNumberOfHands(i)];
			handValues = data.getHandValues(i);
			handAbbreviations = new String[data.getNumberOfHands(i)];
			handAbbreviations = data.getHandAbbreviations(i);

			assertEquals(HandRankMetadata.numberOfHands,numberOfHands+numberOfHandsBelow+numberOfHandsAbove);

			for (int j = 0; j < data.getNumberOfHands(i); j++) {
				System.out.println(Integer.toString(handValues[j])+" "+handAbbreviations[j]);
			}
			
		}
		assertEquals(HandRankMetadata.numberOfHands,recount);
		
	}

}
