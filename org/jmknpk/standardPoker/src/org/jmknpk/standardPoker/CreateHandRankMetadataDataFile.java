package org.jmknpk.standardPoker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/* This class creates the heart of source code that could become part of a HandRankMetaDataLoader method. */
/* The output from this class was never used due to size restrictions of the text files in Eclipse. */
/* Perhaps loading from a data file would be more efficient than the emperical method.*/

public class CreateHandRankMetadataDataFile {

	public static void main (String[] args) {
		int numberOfHands;
		int recount =0;
		int numberOfHandsBelow;
		int numberOfHandsAbove;
		int[] handValues;
		String[] handAbbreviations;
		boolean handsStored;
		FileOutputStream fos = null;
		DataOutputStream dos = null;

//FileWriter dbgfw = null; File dbgOut = null;
//String tempOut;
		try {
//dbgOut=new File("C:\\users\\JimPC\\Documents\\Poker\\HandRankMetadata.dbgOut");
//if (!dbgOut.exists()) {dbgOut.createNewFile();}
//dbgfw=new FileWriter(dbgOut.getAbsoluteFile());


			File file = new File("C:\\Users\\JimPC\\Documents\\Poker\\HandRankMetadata.dat");
			if (!file.exists()) {file.createNewFile();}
			fos = new FileOutputStream(file.getAbsoluteFile());
			dos = new DataOutputStream(fos);

			String outString;
			HandRankMetadata data = new HandRankMetadata(true);
			handsStored = data.getIndividualHandsStored();
			for (int i = 0; i < HandRankMetadata.numberOfHandRanks; i++) {
				numberOfHands = data.getNumberOfHands(i);
				recount = recount + numberOfHands;
				numberOfHandsBelow = data.getNumberOfHandsBelow(i);
				numberOfHandsAbove = data.getNumberOfHandsAbove(i);
				dos.writeInt(numberOfHands);
				dos.writeInt(numberOfHandsBelow);
				dos.writeInt(numberOfHandsAbove);

//tempOut = Integer.toString(numberOfHands)+",";dbgfw.write(tempOut,0,tempOut.length());
//tempOut = Integer.toString(numberOfHandsBelow)+",";dbgfw.write(tempOut,0,tempOut.length());
//tempOut = Integer.toString(numberOfHandsAbove)+",";dbgfw.write(tempOut,0,tempOut.length());
//tempOut = System.getProperty("line.separator");dbgfw.write(tempOut,0,tempOut.length());


				if (handsStored) {
					handValues = new int[data.getNumberOfHands(i)];
					handValues = data.getHandValues(i);
					handAbbreviations = new String[data.getNumberOfHands(i)];
					handAbbreviations = data.getHandAbbreviations(i);
					outString = "numberOfHandsPerRank["+Integer.toString(i)+"]="+Integer.toString(numberOfHands)+";"+System.getProperty("line.separator") +
								"numberOfHandsBelow["+Integer.toString(i)+"]="+Integer.toString(numberOfHandsBelow)+";"+System.getProperty("line.separator") +
								"numberOfHandsAbove["+Integer.toString(i)+"]="+Integer.toString(numberOfHandsAbove)+";"+System.getProperty("line.separator");
					if (handsStored) {
						for (int j = 0; j < numberOfHands; j++) {
							outString = outString +
										"handValuesPerRank["+Integer.toString(i)+"]["+Integer.toString(j)+"]="+Integer.toString(handValues[j])+";" +System.getProperty("line.separator") +
										"handAbbreviationsPerRank["+Integer.toString(i)+"]["+Integer.toString(j)+"]="+handAbbreviations[j]+";"+System.getProperty("line.separator");
					// No data is ever written here.  Must code if we ever set individualHandsStored.
						}
					}
				}
		}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
//dbgfw.close();
				if (dos != null) 
					dos.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {e.printStackTrace();}
		}

	}
	
}
