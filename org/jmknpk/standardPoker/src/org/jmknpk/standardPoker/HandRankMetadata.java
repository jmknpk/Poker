package org.jmknpk.standardPoker;

import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* HandRankMetadata class contains information about poker hand ranks which is meaningful in evaluating hand ranks within the realm of all possible hands and ranks */

public class HandRankMetadata {
	static final int numberOfHandRanks = 12048;
	static final int numberOfHands = 2598960;
	int[] numberOfHandsPerRank;  // a count of the number of hands evaluating to a specific rank.
	int[] numberOfHandsBelow; // a count of the number of hands evaluating to lesser ranks.
	int[] numberOfHandsAbove; // a count of the number of hands evaluating to higher ranks.
	int[][] handValuesPerRank; // keep track of the value of all hands evaluating to each rank.
	
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

		String outString;
		BufferedWriter bw = null;
		try {
			File file = new File("HandRankMetadata.txt");
			if (!file.exists()) {file.createNewFile();}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) { };
		
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
		
		Arrays.sort(hands,Hand.RankThenValueComparator);  // Sort by handValue
		Hand[] intuitiveHands = new Hand[hands.length];
		for (int i = 0; i < hands.length; i++) {
			intuitiveHands[i] = hands[i];
		}
		Arrays.sort(intuitiveHands,Hand.IntuitiveComparator);
		for (int i = 0; i < hands.length; i++) {
			if (!hands[i].equals(intuitiveHands[i])) {
				outString = "i="+Integer.toString(i)+" hands[]="+hands[i].getAbbreviation()+" rank="+Integer.toString(hands[i].getHandRank())+" intuitiveHands[]="+intuitiveHands[i].getAbbreviation()+" rank="+Integer.toString(intuitiveHands[i].getHandRank());
				try {
					bw.write(outString,0,outString.length());
					bw.newLine();
				} catch (IOException e) {}
				
			}
		}
		
		int rank = 0;
		int numberBelowRank = 0;
		int numberBelowCurrentHand = 0;
		int countHandsPerRank = 1;
		int[] temp;
		for (int i = 0; i < hands.length; i++) {
/*
			outString = Integer.toString(i)+" "+Integer.toString(hands[i].getHandRank())+" "+Integer.toString(hands[i].getValue())+" "+hands[i].getAbbreviation()+" category="+hands[i].getCategoryName();
			try {
				bw.write(outString,0,outString.length());
				bw.newLine();
			} catch (IOException e) {}
*/
			if (hands[i].getHandRank() != rank) {
				numberOfHandsPerRank[rank] = countHandsPerRank;
				numberOfHandsBelow[rank] = numberBelowRank;
				numberOfHandsAbove[rank] = numberOfHands - numberBelowCurrentHand - countHandsPerRank;
//System.out.println(" rank="+Integer.toString(rank)+" countHandsPerRank="+Integer.toString(countHandsPerRank));
				temp = new int[countHandsPerRank];
				for (int j = 0; j < countHandsPerRank; j++) {
					temp[j] = hands[i-countHandsPerRank+j+1].getValue();
				}
//System.out.println(" rank="+Integer.toString(rank)+" temp.length="+temp.length);
				handValuesPerRank[rank] = temp;
				rank++;
				numberBelowRank = numberBelowCurrentHand;
				countHandsPerRank = 1;
/*
				outString = "=====Updated rank="+Integer.toString(rank-1)+" HandsPer="+Integer.toString(numberOfHandsPerRank[rank-1])+ " HandsBelow="+Integer.toString(numberOfHandsBelow[rank-1]);
				try {
					bw.write(outString,0,outString.length());
					bw.newLine();
				} catch (IOException e) {}
*/
			} else {
				countHandsPerRank++;
			}
			numberBelowCurrentHand++;
/*
			outString = "numberBelowCurrentHand="+Integer.toString(numberBelowCurrentHand)+"countHandsPerRank="+Integer.toString(countHandsPerRank);
 
			try {
				bw.write(outString,0,outString.length());
				bw.newLine();
			} catch (IOException e) {}
*/			
			
		}

		try {
			bw.close();
		} catch (IOException e) {}

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
			return temp;
		}
	}
	
}
