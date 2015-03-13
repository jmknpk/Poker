package org.jmknpk.standardPoker;

import java.util.Arrays;

/* HandRankMetadata class contains information about poker hand ranks which is meaningful in evaluating hand ranks within the realm of all possible hands and ranks */

public class HandRankMetadata {
	static final int numberOfHandRanks = 7462;
	static final int numberOfHands = 2598960;
	int[] numberOfHandsPerRank;  // a count of the number of hands evaluating to a specific rank.
	int[] numberOfHandsBelow; // a count of the number of hands evaluating to lesser ranks.
	int[] numberOfHandsAbove; // a count of the number of hands evaluating to higher ranks.
	int[][] handValuesPerRank; // keep track of the value of all hands evaluating to each rank.
	String[][] handAbbreviationsPerRank; // keep track of the abbreviations of hands evaluating to each rank.
	
	public HandRankMetadata () {
		numberOfHandsPerRank = new int[numberOfHandRanks];  // a count of the number of hands evaluating to a specific rank.
		numberOfHandsBelow = new int [numberOfHandRanks]; // a count of the number of hands evaluating to lesser ranks.
		numberOfHandsAbove = new int [numberOfHandRanks]; // a count of the number of hands evaluating to higher ranks.
		handValuesPerRank = new int[numberOfHandRanks][];
		determineHandRankMetaData();
	}
	
	public void determineHandRankMetaData() {
		empericalProcess();
	}
	
	public void empericalProcess() {

		Hand[] hands = new Hand[numberOfHands];
		int handIndex = 0;
		for (int i = 0; i < 52-4; i++) {
			for (int j = i+1; j < 52-3; j++) {
				for (int k = j+1; k < 52-2; k++) {
					for (int l = k+1; l < 52-1; l++) {
						for (int m = l+1; m < 52; m++) {
							hands[handIndex++] = new Hand ( new CardSet (new Card(i), new Card(j), new Card(k), new Card(l), new Card(m)));
						}
					}
				}
			}
		}
//System.out.println("dbg Hand.HandRankMetadata() handIndex="+Integer.toString(handIndex));
		
		Arrays.sort(hands,Hand.RankThenValueComparator);  // Sort by handRank, then by handValue
		
		int rank = 0;
		int numberBelowRank = 0;
		int numberBelowCurrentHand = 0;
		int countHandsPerRank = 0;
		int[] temp;
		String[] tempS;
		for (int i = 0; i < hands.length; i++) {
			if (hands[i].getHandRank() != rank) {
				// Have found a new rank value for the current hand.  Need to store the information for the prior rank.
				numberOfHandsPerRank[rank] = countHandsPerRank;
				numberOfHandsBelow[rank] = numberBelowRank;
				numberOfHandsAbove[rank] = numberOfHands - numberBelowRank - countHandsPerRank;
				temp = new int[countHandsPerRank];
				for (int j = 0; j < countHandsPerRank; j++) {
					temp[j] = hands[i-countHandsPerRank+j+1].getValue();
				}
				handValuesPerRank[rank] = temp;
/*
				tempS = new String[countHandsPerRank];
				for (int j = 0; j < countHandsPerRank; j++) {
					tempS[j] = hands[i-countHandsPerRank+j+1].getAbbreviation();
				}
				handAbbreviationsPerRank[rank] = tempS;
*/
				// Finished storing the information for the prior rank.  Now continue with next rank.
				rank++;
				numberBelowRank = numberBelowCurrentHand;
				countHandsPerRank = 1;
			} else {
				countHandsPerRank++;
			}
			numberBelowCurrentHand++;
		}
		// Need to store the information for the final rank.
		numberOfHandsPerRank[rank] = countHandsPerRank;
		numberOfHandsBelow[rank] = numberBelowRank;
		numberOfHandsAbove[rank] = numberOfHands - numberBelowRank - countHandsPerRank;
		temp = new int[countHandsPerRank];
		for (int j = 0; j < countHandsPerRank; j++) {
			temp[j] = hands[hands.length-countHandsPerRank+j].getValue();
		}
/*		
		handValuesPerRank[rank] = temp;
		tempS = new String[countHandsPerRank];
		for (int j = 0; j < countHandsPerRank; j++) {
			tempS[j] = hands[hands.length-countHandsPerRank+j].getAbbreviation();
		}
		handAbbreviationsPerRank[rank] = tempS;
*/

	}
	
	public int getNumberOfHands(int inRank) {
		if (inRank < 0 || inRank >= numberOfHandRanks) {
			throw new IllegalArgumentException();
		} else {
			return numberOfHandsPerRank[inRank];
		}
	}
	
	public int getNumberOfHandsBelow(int inRank) {
		if (inRank < 0 || inRank >= numberOfHandRanks) {
			throw new IllegalArgumentException();
		} else {
			return numberOfHandsBelow[inRank];
		}
	}
	
	public int getNumberOfHandsAbove(int inRank) {
		if (inRank < 0 || inRank >= numberOfHandRanks) {
			throw new IllegalArgumentException();
		} else {
			return numberOfHandsAbove[inRank];
		}
	}
	
	public int[] getHandValues(int inRank) {
		if (inRank < 0 || inRank >= numberOfHandRanks) {
			throw new IllegalArgumentException();
		} else {
			int[] temp = new int[handValuesPerRank[inRank].length];
			temp = handValuesPerRank[inRank];
			return temp;
		}
	}
	
	public String[] getHandAbbreviations(int inRank) {
		if (inRank < 0 || inRank >= numberOfHandRanks) {
			throw new IllegalArgumentException();
		} else {
			String[] temp = new String[handAbbreviationsPerRank[inRank].length];
			temp = handAbbreviationsPerRank[inRank];
			return temp;
		}
	}
	
}
