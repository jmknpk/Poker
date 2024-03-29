package org.jmknpk.standardPoker;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShowAllHandRanks {

	public static void main (String[] args) {
		int numberOfHands;
		int recount =0;
		int numberOfHandsBelow;
		int numberOfHandsAbove;
		int[] handValues;
		String[] handAbbreviations;

		String outString;
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Users\\JimPC\\Documents\\Poker\\ShowAllHandRanks.txt");
			if (!file.exists()) {file.createNewFile();}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) { };

		
		HandRankMetadata data = new HandRankMetadata(true);
		for (int i = 0; i < HandRankMetadata.numberOfHandRanks; i++) {
			numberOfHands = data.getNumberOfHands(i);
			recount = recount + numberOfHands;
			numberOfHandsBelow = data.getNumberOfHandsBelow(i);
			numberOfHandsAbove = data.getNumberOfHandsAbove(i);
			handAbbreviations = new String[data.getNumberOfHands(i)];
			handAbbreviations = data.getHandAbbreviations(i);
			Hand oneHand = Hand.getHandFromAbbreviation(handAbbreviations[0]);
			outString =	"rank="+Integer.toString(i)+
						" #="+Integer.toString(numberOfHands)+
						" below="+Integer.toString(numberOfHandsBelow)+
						" above="+Integer.toString(numberOfHandsAbove)+
						" Percentage="+
						String.format("%.4f%%",((double) 100) *(((double) numberOfHandsBelow) / (((double) numberOfHandsBelow) + ((double) numberOfHandsAbove))))+
						" " + oneHand.getShowdownDescription();
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
