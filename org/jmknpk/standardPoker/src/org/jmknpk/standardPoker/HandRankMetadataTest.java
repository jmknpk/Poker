package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
		boolean individualHandsStored;
		Instant before;
		Instant after;
		long diff;
		
		before = Instant.now();
		HandRankMetadata data = new HandRankMetadata(true);
		after = Instant.now();
		diff = ChronoUnit.SECONDS.between(before,after);
		System.out.println(" emperical approach took "+Long.toString(diff)+" seconds.");
		assertTrue(data != null);
		for (int i = 0; i < HandRankMetadata.numberOfHandRanks; i++) {
			numberOfHands = data.getNumberOfHands(i);
			recount = recount + numberOfHands;
			numberOfHandsBelow = data.getNumberOfHandsBelow(i);
			numberOfHandsAbove = data.getNumberOfHandsAbove(i);
			individualHandsStored = data.getIndividualHandsStored();
			if (individualHandsStored) {
				handValues = new int[data.getNumberOfHands(i)];
				handValues = data.getHandValues(i);
				handAbbreviations = new String[data.getNumberOfHands(i)];
				handAbbreviations = data.getHandAbbreviations(i);
			} else {
				handValues = null;
				handAbbreviations = null;
			}

			
			assertEquals(HandRankMetadata.numberOfHands,numberOfHands+numberOfHandsBelow+numberOfHandsAbove);

			if (individualHandsStored) {
				for (int j = 0; j < data.getNumberOfHands(i); j++) {
					Hand hand = Hand.getHandFromAbbreviation(handAbbreviations[j]);
	//if (i != hand.getHandRank()) {System.out.println(Integer.toString(i)+" "+Integer.toString(j)+" "+hand.getAbbreviation()+" "+Integer.toString(hand.getHandRank()));}
					assertEquals(i,hand.getHandRank());
	//System.out.println(Integer.toString(handValues[j])+" "+handAbbreviations[j]);
				}
			}
			
		}
		
		assertEquals(HandRankMetadata.numberOfHands,recount);

		before = Instant.now();
		HandRankMetadata dataFromFile = new HandRankMetadata(false);
		after = Instant.now();
		diff = ChronoUnit.SECONDS.between(before,after);
		System.out.println(" data file approach took "+Long.toString(diff)+" seconds.");
		assertTrue(data.equals(dataFromFile));
		
	}

}
