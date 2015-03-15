package org.jmknpk.standardPoker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* This class creates the heart of source code that could become part of a HandRankMetaDataLoader method. */
/* The output from this class was never used due to size restrictions of the text files in Eclipse. */
/* Perhaps loading from a data file would be more efficient than the emperical method.*/

public class GenerateCodeHandRankMetadataLoader {

	public static void main (String[] args) {
		int numberOfHands;
		int recount =0;
		int numberOfHandsBelow;
		int numberOfHandsAbove;
		int[] handValues;
		String[] handAbbreviations;
		boolean individualHandsStored;

		String outString;
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Users\\JimPC\\Documents\\Poker\\HandRankMetadataLoader.txt");
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
			individualHandsStored = data.getIndividualHandsStored();
			if (individualHandsStored) {
				handValues = new int[data.getNumberOfHands(i)];
				handValues = data.getHandValues(i);
				handAbbreviations = new String[data.getNumberOfHands(i)];
				handAbbreviations = data.getHandAbbreviations(i);
				outString = "numberOfHandsPerRank["+Integer.toString(i)+"]="+Integer.toString(numberOfHands)+";"+System.getProperty("line.separator") +
							"numberOfHandsBelow["+Integer.toString(i)+"]="+Integer.toString(numberOfHandsBelow)+";"+System.getProperty("line.separator") +
							"numberOfHandsAbove["+Integer.toString(i)+"]="+Integer.toString(numberOfHandsAbove)+";"+System.getProperty("line.separator");
				for (int j = 0; j < numberOfHands; j++) {
					outString = outString +
								"handValuesPerRank["+Integer.toString(i)+"]["+Integer.toString(j)+"]="+Integer.toString(handValues[j])+";" +System.getProperty("line.separator") +
								"handAbbreviationsPerRank["+Integer.toString(i)+"]["+Integer.toString(j)+"]="+handAbbreviations[j]+";"+System.getProperty("line.separator");
				}
				try {
					bw.write(outString,0,outString.length());
				} catch (IOException e) {}
			}
		}

		try {
			bw.close();
		} catch (IOException e) {}

	}
	
}
