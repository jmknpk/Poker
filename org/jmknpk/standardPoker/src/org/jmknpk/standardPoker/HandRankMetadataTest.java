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
			assertEquals(HandRankMetadata.numberOfHands,numberOfHands+numberOfHandsBelow+numberOfHandsAbove);
		}
		assertEquals(HandRankMetadata.numberOfHands,recount);
		
		System.out.println("  Need to build another Junit test on Hand.java");
		System.out.println("  Need to assure that for each possible hand combination, the rank determines a rank such that");
		System.out.println("  The array of hands sorted by rank is the same as an array of hands sorted with the intuitive comparator.");
	}

}
