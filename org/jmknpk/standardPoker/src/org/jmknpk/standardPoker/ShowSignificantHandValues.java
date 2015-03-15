package org.jmknpk.standardPoker;

import java.util.Arrays;

/* This class was intended to be used in determing how to convert a hand value to the hand cards.  It has not yet been made functional. */


public class ShowSignificantHandValues {


	public static void main( String[] args ) {

		Hand[] hands = new Hand[2598960];
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

		Arrays.sort(hands);

		CardSet cSet;
		boolean[] found = new boolean[13];
		for (int i=0; i < found.length; i++) {
			found[i] = false;
		}
		
		for (int i = 0; i < hands.length; i++) {
			cSet = hands[i].getCardSet();
			for (int j = 0; j < 13; j++) {
//Problem with this logic because cards within a hand are sorted by showdown sort order.
				if (cSet.getCard(0).getPip().getValue() == j && !found[j]) {
					if (j < 5) {
						String temp = " check lowballs ";
						for (int k = 1; k < 5; k++) {
							temp = temp + "" + Integer.toString(cSet.getCard(k).getPip().getValue());
						}
						System.out.println(temp);
					}
					found[j] = true;
					System.out.println(Integer.toString(j)+"="+Integer.toString(i));
				}
			}
			
		}
		
	}
		
}
