package org.jmknpk.standardPoker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ShowAllHandCombinations {

	public static void main( String[] args ) {

		Hand[] hands = new Hand[2598960];
		int handIndex = 0;
		int maxRank = 0;
		for (int i = 0; i < 52-4; i++) {
			for (int j = i+1; j < 52-3; j++) {
				for (int k = j+1; k < 52-2; k++) {
					for (int l = k+1; l < 52-1; l++) {
						for (int m = l+1; m < 52; m++) {
							hands[handIndex++] = new Hand ( new CardSet (new Card(i), new Card(j), new Card(k), new Card(l), new Card(m)));
							if (hands[handIndex-1].getHandRank() > maxRank) maxRank = hands[handIndex-1].getHandRank();
						}
					}
				}
			}
		}

		Arrays.sort(hands,Hand.RankThenValueComparator);  // Sort by handValue

		String outString;
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Users\\JimPC\\Documents\\Poker\\ShowAllHandCombinations.txt");
			if (!file.exists()) {file.createNewFile();}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) { };
		

		for (int i = 0; i < hands.length; i++) {

			int[] d = hands[i].getDistinguishers();
			
			String dString = "";
			for (int j = 0; j < d.length; j++) {
				if (j > 0) {
					dString = dString + ",";
				}
				dString = dString + Integer.toString(d[j]);
			}
			outString = "i="+Integer.toString(i)+
						", rank="+Integer.toString(hands[i].getHandRank())+
						", value="+Integer.toString(hands[i].getValue())+
						", distinguishers="+	dString +
						", "+hands[i].getAbbreviation()+
						", "+hands[i].getShortShowdownDescription();
			try {
				bw.write(outString,0,outString.length());
				bw.newLine();
			} catch (IOException e) {}
		}


		try {
			bw.close();
		} catch (IOException e) {}

	}
}
