package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		assertEquals(12048,maxRank);
		assertEquals(2598960,handIndex);

		Hand[] intuitiveHands = new Hand[2598960];
		for (int i = 0; i < valueHands.length; i++) {
			intuitiveHands[i] = valueHands[i];
		}
		Arrays.sort(intuitiveHands,Hand.IntuitiveComparator); // Sort by intuitive method

		String outString;
		BufferedWriter bw = null;
		try {
			File file = new File("HandTestAllCombinations.txt");
			if (!file.exists()) {file.createNewFile();}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) { };
		
		int[] priorD = new int[1];
		priorD[0] = -1;
		int priorRank=-1;
		for (int i = 0; i < intuitiveHands.length; i++) {
			String temp;
			int[] d;
			boolean distinguishersChanged;
			boolean ranksChanged;
			d = intuitiveHands[i].getDistinguishers();
			distinguishersChanged = false;
			if (d.length == priorD.length) {
				for (int j = 0; j < d.length; j++) {
					if (d[j] != priorD[j]) {
						distinguishersChanged = true;
					}
				}
			} else {
				distinguishersChanged = true;
			}
			if (priorRank == intuitiveHands[i].getHandRank()) {
				ranksChanged = false;
			} else {
				ranksChanged = true;
			}
			assertEquals(ranksChanged,distinguishersChanged);
			if (ranksChanged == distinguishersChanged) {
				temp = "";
			} else {
				if (ranksChanged) {
					temp = "PROBLEM: Rank Changed without Distinguishers Change.";
				} else {
					temp = "PROBLEM: Distinguishers changed without Rank change.";
				}
			}

/*			
			String dString = "";
			for (int j = 0; j < d.length; j++) {
				if (j > 0) {
					dString = dString + ",";
				}
				dString = dString + Integer.toString(d[j]);
			}
			outString = "i="+Integer.toString(i)+
						" rank="+Integer.toString(intuitiveHands[i].getHandRank())+
						" distinguishers="+	dString +
						" "+intuitiveHands[i].getAbbreviation()+
						" "+intuitiveHands[i].getCategoryName()+
						temp;
			try {
				bw.write(outString,0,outString.length());
				bw.newLine();
			} catch (IOException e) {}
*/

			priorD = d;
			priorRank = intuitiveHands[i].getHandRank();
		}

		
		Arrays.sort(valueHands,Hand.RankThenValueComparator);  // Sort by handValue
		
		for (int i = 0; i < valueHands.length; i++) {
			
//			assertEquals(valueHands[i].getHandRank(),intuitiveHands[i].getHandRank());
			outString = "i="+Integer.toString(i)+
					" "+valueHands[i].getAbbreviation()+
					" "+intuitiveHands[i].getAbbreviation();
			try {
				bw.write(outString,0,outString.length());
				bw.newLine();
			} catch (IOException e) {}
		}
		

/*
		for (int i = 0; i < valueHands.length; i++) {
			System.out.println(Integer.toString(valueHands[i].getHandRank())+","+Integer.toString(valueHands[i].getValue())+","+valueHands[i].getCardSet().getAbbreviation());
		}
*/
		try {
			bw.close();
		} catch (IOException e) {}

	}
}
