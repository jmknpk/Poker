package org.jmknpk.standardPoker;

import java.util.Comparator;
import java.lang.Comparable;

/* This class represents Showdown Hands of five poker cards */

public class Hand implements Comparable<Hand>, Comparator<Hand> {
	private CardSet cardSet;
	private Card[] cards = new Card[5];
	private ShowdownCategoryName categoryName;
	private boolean isFlush;
	private int[] distinguishers;
	private int handValue; // handValue ranges from 0 to 2,598,959 for 2,598,960 different hands.  
// handValue is unique for all Non-ordered combinations of a set of 52 distinct cards.  
// AcAdAh2c2d has a different handValue from AcAdAh2c2s.  But 2dAcAdAh2c has the same handValue as AcAdAh2c2d (i.e. order is ignored).
	private int handRank;  // handRank of hands ranges from 0 to 7461.  
// handRank is the relative rank of showdown hands.  AcAdAh2c2d has the same handRank as AcAdAs2c2d, because it is equivalent at showdwon.

	public static final Comparator<Hand> RankComparator = new Comparator<Hand>() {
		public int compare(Hand h1, Hand h2) {
			if (h1 == null || h2 == null) {
				throw new NullPointerException();
			} else if (h1.getHandRank() == h2.getHandRank()) {
				return 0;
			} else if (h1.getHandRank() > h2.getHandRank()) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	public static final Comparator<Hand> RankThenValueComparator = new Comparator<Hand>() {
		public int compare(Hand h1, Hand h2) {
			if (h1 == null || h2 == null) {
				throw new NullPointerException();
			} else if (h1.getHandRank() == h2.getHandRank()) {
				if (h1.getValue() == h2.getValue()) {
					return 0;
				} else if (h1.getValue() > h2.getValue()) {
					return 1;
				} else {
					return -1;
				}
			} else if (h1.getHandRank() > h2.getHandRank()) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	public static final Comparator<Hand> IntuitiveComparator = new Comparator<Hand>() {
		public int compare(Hand h1, Hand h2) {
			int[] d1;
			int[] d2;
			if (h1 == null || h2 == null) {
				throw new NullPointerException();
			} else {
				d1 = h1.getDistinguishers();
				d2 = h2.getDistinguishers();
				int comparison = 0;
				for (int i = 0; i < d1.length && i < d2.length && comparison == 0; i++) {
					if (d1[i] > d2[i]) {
						comparison = 1;
					} else if (d1[i] < d2[i]) {
						comparison = -1;
					}
				if (comparison == 0) {
					if (h1.getValue() >h2.getValue()) {
						comparison = 1;
					} else if (h1.getValue() < h2.getValue()) {
						comparison = -1;
					}
				}
				}
				return comparison;
			}
		}
	};
	
	
	public Hand() {
		this (new CardSet(	new Card(PipName.TWO,SuitName.DIAMOND),
								new Card(PipName.THREE,SuitName.CLUB),
								new Card(PipName.FOUR,SuitName.CLUB),
								new Card(PipName.FIVE,SuitName.CLUB),
								new Card(PipName.SEVEN,SuitName.CLUB))
						);
	}
	
	public Hand(CardSet inSet) {
		if (inSet == null) {
			throw new NullPointerException();
		} else {
			
			cardSet = new CardSet(inSet);

			cardSet.sort(false);

			for (int i = 0; i < 5; i++) {
				cards[i] = cardSet.getCard(i);
			}
			
			evaluate();
			
		}
	}
	
	public boolean equals(Hand inHand) {
		if (inHand == null) {
			throw new NullPointerException();
		} else {
			return cardSet.equals(inHand.getCardSet());
		}
	}
	
	@Override public boolean equals(Object o) {
		if (o == null) {
			throw new NullPointerException();
		} else if (getClass().equals(o.getClass())) {
				return equals((Hand) o);
		} else {
			return false;
		}
	}

	public int compareTo(Hand inHand) {
		if (inHand == null) {
			throw new NullPointerException();
		} else {
			if (equals(inHand)) {
				return 0;
			} else if (handRank > inHand.getHandRank()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
	@Override public int hashCode() {
		return handValue;
	}

	@Override public int compare(Hand h1, Hand h2) {
		if (h1 == null || h2 == null) {
			throw new NullPointerException();
		} else if (h1.equals(h2)) {
			return 0;
		} else if (h1.getValue() > h2.getValue()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public Card getCard(int i) {
		if (i < 0 || i >= cardSet.getSize()) {
			throw new IllegalArgumentException();
		} else {
			return cardSet.getCard(i);
		}
	}
	
	public CardSet getCardSet() {
		return cardSet;
	}

// ***method evaluate**
	public void evaluate() {
//		System.out.println("dbg Hand.evaluation() entering method with: "+cardSet.getAbbreviation());
		int[] matches = new int[13];  // number of matches per Pip
		int matchCount = 0;
		int matchMax = 0;
		int matchMaxPip = -1;
		boolean keepSearching;
		boolean foundFirst;
		int numberFound;
		Suit suit = cards[0].getSuit();
//System.out.println("dbg Hand.evaluate(): "+cardSet.getAbbreviation()+" "+Integer.toString(cards[0].getPipValue())+","+Integer.toString(cards[1].getPipValue())+","+Integer.toString(cards[2].getPipValue())+","+Integer.toString(cards[3].getPipValue())+","+Integer.toString(cards[4].getPipValue()));

// ***method evaluate** preliminary flush determination
		
		
		if (	suit.equals(cards[1].getSuit()) &&
				suit.equals(cards[2].getSuit()) &&
				suit.equals(cards[3].getSuit()) &&
				suit.equals(cards[4].getSuit())) {
			isFlush = true;
		} else {
			isFlush = false;
		}

//System.out.println("dbg Hand.evaluation() isFlush="+Boolean.toString(isFlush));
		

//***method evaluate** Main decision Tree

		if (	(	cards[0].getPip().getValue() == cards[1].getPip().getValue() + 1 &&
					cards[1].getPip().getValue() == cards[2].getPip().getValue() + 1 &&
					cards[2].getPip().getValue() == cards[3].getPip().getValue() + 1 &&
					cards[3].getPip().getValue() == cards[4].getPip().getValue() + 1) 
					|| 
				(	cards[0].getPip().getPipName() == PipName.ACE &&
					cards[1].getPip().getPipName() == PipName.FIVE &&
					cards[2].getPip().getPipName() == PipName.FOUR &&
					cards[3].getPip().getPipName() == PipName.THREE &&
					cards[4].getPip().getPipName() == PipName.TWO)) {


//***method evaluate** Main decision Tree > Logic for Straight
//System.out.println("dbg Hand.evaluation() entering Straight Logic.");			
			
			
			if (isFlush) {
				categoryName = ShowdownCategoryName.STRAIGHTFLUSH;
				distinguishers = new int[2];
				distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.STRAIGHTFLUSH);
			} else {
				categoryName = ShowdownCategoryName.STRAIGHT;
				distinguishers = new int[2];
				distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.STRAIGHT);
			}
			
			if (cards[0].getPip().getPipName() == PipName.ACE &&
				cards[4].getPip().getPipName() == PipName.TWO) {
				distinguishers[1] = cards[1].getPip().getValue();  // 5 card is high
			} else {
				distinguishers[1] = cards[0].getPip().getValue();  // high card of the straight is the next distinguisher
			}
			
		} else if (isFlush) {

			//***method evaluate** Main decision Tree > Logic for non-straight Flush.
			
//System.out.println("dbg Hand.evaluation() entering non-straight flush logic.");
			categoryName = ShowdownCategoryName.FLUSH;
			distinguishers = new int[6];
			distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.FLUSH);
			for (int i = 0; i < 5; i++) {
				distinguishers[i+1] = cards[i].getPipValue();
			}
		} else {

//***method evaluate** Main decision Tree > Logic for non-straight non-flush (pip matching) hands.

//System.out.println("dbg Hand.evaluation() entering non-Straight & non-Flush logic.");
			

// seek info about matching pips
			
			for (int i = 0; i < 13; i++) {
				matches[i] = -1;
			}
			for (int i = 0; i < 4; i++) {
//System.out.println("dgb Hand.evaluation() i="+Integer.toString(i));
				if (matches[cards[i].getPipValue()] == -1) {
					matches[cards[i].getPipValue()] = 0;  // indicates that the card was found within the hand regardless of matching
				}
				if ( cards[i].getPip().equals(cards[i+1].getPip())) {
//System.out.println("dbg Hand.evaluation() found match a "+cards[i].getAbbreviation()+" "+cards[i].getAbbreviation());
//System.out.println("dbg Hand.evaluation() found match b cards["+Integer.toString(i)+"].getPipValue()="+Integer.toString(cards[i].getPipValue()));
//System.out.println("dbg Hand.evaluation() found match c matches["+Integer.toString(cards[i].getPipValue())+"]="+Integer.toString(matches[cards[i].getPipValue()]));
//System.out.println("dbg Hand.evaluation() found match d matches["+Integer.toString(cards[i].getPipValue())+"]="+Integer.toString(matches[cards[i].getPipValue()]));
					if (matches[cards[i].getPipValue()] == 0) {
						matchCount++;  // This is a new set of matching pips
					}
					matches[cards[i].getPipValue()]++; // count the number of matching pips
					if (matches[cards[i].getPipValue()] > matchMax) {
						matchMax++; // keep track of the maximum number of matching pips for all pips
						matchMaxPip = cards[i].getPipValue(); // keep track of the pip going with the maximum value
					}
				}
			}

//System.out.println("Hand.evaluate() matches[]="+Integer.toString(matches[0])+","+Integer.toString(matches[1])+","+Integer.toString(matches[2])+","+Integer.toString(matches[3])+","+Integer.toString(matches[4])+","+Integer.toString(matches[5])+","+Integer.toString(matches[6])+","+Integer.toString(matches[7])+","+Integer.toString(matches[8])+","+Integer.toString(matches[9])+","+Integer.toString(matches[10])+","+Integer.toString(matches[11])+","+Integer.toString(matches[12])+",");
//System.out.println("dbg Hand.evaluate() matchMax="+Integer.toString(matchMax)+" matchCount="+Integer.toString(matchCount)+" matchMaxPip="+Integer.toString(matchMaxPip));
			
			if (matchMax == 3) {

//***method evaluate** Main decision Tree > Pip Matching Hands > Four of a Kind

				categoryName = ShowdownCategoryName.FOUROFAKIND;
				distinguishers = new int[3];
				distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.FOUROFAKIND);
				distinguishers[1] = matchMaxPip;  // Can only have three matches for one of the pips.  This must be it.
				keepSearching = true;
				for (int i = 0; i < 13 && keepSearching; i++) {
					if (matches[i] == 0) {
						distinguishers[2] = i;
						keepSearching = false;
					}
				}
			} else if (matchMax == 2) {
				if (matchCount > 1) {

//***method evaluate** Main decision Tree > Pip Matching Hands > Full House

					categoryName = ShowdownCategoryName.FULLHOUSE;
					distinguishers = new int[3];
					distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.FULLHOUSE);
					distinguishers[1] = matchMaxPip; // Can only have two matches for one of the pips.  This must be it.
					keepSearching = true;
					for (int i = 0; i < 5 && keepSearching; i++) {
						if (cards[i].getPipValue() != distinguishers[1]) {
							distinguishers[2] = i;
							keepSearching = false;
						}
					}
				} else {

//***method evaluate** Main decision Tree > Pip Matching Hands > Three of a Kind

					categoryName = ShowdownCategoryName.THREEOFAKIND;
					distinguishers = new int[4];
					distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.THREEOFAKIND);
					distinguishers[1] = matchMaxPip;
					keepSearching = true;
					foundFirst = false;
					for (int i = 0; i < 5 && keepSearching; i++) {
						if (cards[i].getPipValue() != matchMaxPip) {
							if (foundFirst) {
								distinguishers[3] = cards[i].getPipValue();
								keepSearching = false;
							} else {
								distinguishers[2] = cards[i].getPipValue();
								foundFirst = true;
							}
						}
					}
				}
			} else if (matchMax == 1) {
				if (matchCount > 1) {

//***method evaluate** Main decision Tree > Pip Matching Hands > Two Pair

					categoryName = ShowdownCategoryName.TWOPAIR;
					distinguishers = new int[4];
					distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.TWOPAIR);
					numberFound = 0;
					foundFirst = false;
					for (int i = 12; i >= 0 && numberFound < 3; i--) {
						if (matches[i] > 0) {
							if (foundFirst) {
								distinguishers[2] = i;
								numberFound++;
							} else {
								distinguishers[1] = i;
								foundFirst = true;
								numberFound++;
							}
						} else if (matches[i] == 0) {
							distinguishers[3] = i;
							numberFound++;
						}
					}
				} else {

//***method evaluate** Main decision Tree > Pip Matching Hands > One Pair

					categoryName = ShowdownCategoryName.ONEPAIR;
					distinguishers = new int[5];
					distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.ONEPAIR);
					numberFound = 0;
					int secondaryIndex = 2;
					for (int i = 12; i >= 0 && numberFound < 4; i--) {
						if (matches[i] > 0) {
							distinguishers[1] = i;
//System.out.println("Hand.evaluate() One Pair distinguishers["+Integer.toString(1)+"]= "+Integer.toString(i));
							numberFound++;
						} else if (matches[i] == 0) {
							distinguishers[secondaryIndex++] = i;
//System.out.println("Hand.evaluate() One Pair distinguishers["+Integer.toString(secondaryIndex-1)+"]= "+Integer.toString(i));
							numberFound++;
						}
					}
				}
			} else {

//***method evaluate** Main decision Tree > Pip Matching Hands > High Card

				categoryName = ShowdownCategoryName.HIGHCARD;
				distinguishers = new int[6];
				distinguishers[0] = getShowdownCategoryValue(ShowdownCategoryName.HIGHCARD);
				distinguishers[1] = cards[0].getPipValue();
				distinguishers[2] = cards[1].getPipValue();
				distinguishers[3] = cards[2].getPipValue();
				distinguishers[4] = cards[3].getPipValue();
				distinguishers[5] = cards[4].getPipValue();
			}
			
		}
		
		calculateHandRank();
		handValue = calculateValue();
		
	}

	public void calculateHandRank() {
		switch (categoryName) {
			case STRAIGHTFLUSH:
				handRank = calculateStraightFlushRank();
				break;
			case FOUROFAKIND:
				handRank = calculateFourOfAKindRank();
				break;
			case FULLHOUSE:
				handRank = calculateFullHouseRank();
				break;
			case FLUSH:
				handRank = calculateFlushRank();
				break;
			case STRAIGHT:
				handRank = calculateStraightRank();
				break;
			case THREEOFAKIND:
				handRank = calculateThreeOfAKindRank();
				break;
			case TWOPAIR:
				handRank = calculateTwoPairRank();
				break;
			case ONEPAIR:
				handRank = calculateOnePairRank();
				break;
			case HIGHCARD:
				handRank = calculateNonStraightPipCombinationsRank();
				break;
			default:
				handRank = -1;
				break;
		}
	}

	public int calculateValue() {
		int[] vals = new int[5];
		for (int i = 0; i < 5; i++) {
			vals[i] = cards[i].getValue(); // value of card ranges from 0 to 51
		}
// calculations are based on non-ordered combination formula (n!) / ((n-r)!*(r!), n = # of items, r = # of selections
// Reformatted for our purposes to: ( (n!) / (n-r)! ) / (r!)
// n = 52, r = 5: ( 52! / 47!) / 5! = 52*51*50*49*48/5*4*3*2*1 = 2598960 number of unique hand combinations (some with identical rank).
// pip5 * pip4 * pip3 * pip2 * pip1 / 5*4*3*2*1 +
// pip4 * pip3 * pip2 * pip1 / 4*3*2*1 +
// pip3 * pip2 * pip1 / 3*2*1 +
// pip2 * pip1 / 2*1 +
// pip1 / 1
// Note that the combinations counted here include straights.
		return (	vals[0] * (vals[0]-1) * (vals[0]-2) * (vals[0]-3) * (vals[0]-4) ) / 120 +
					vals[1] * (vals[1]-1) * (vals[1]-2) * (vals[1]-3) / 24 +
					vals[2] * (vals[2]-1) * (vals[2]-2) / 6 +
					vals[3] * (vals[3]-1) / 2 +
					vals[4];
	}

	public int calculatePipCombinationsRank() {
		int[] vals = new int[5];
		for (int i = 0; i < 5; i++) {
			vals[i] = cards[i].getPipValue(); // value of pip ranges from 0 to 12
		}
// calculations are based on non-ordered combination formula (n!) / ((n-r)!*(r!), n = # of items, r = # of selections
// Reformatted for our purposes to: ( (n!) / (n-r)! ) / (r!)
// pip5 * pip4 * pip3 * pip2 * pip1 / 5*4*3*2*1 +
// pip4 * pip3 * pip2 * pip1 / 4*3*2*1 +
// pip3 * pip2 * pip1 / 3*2*1 +
// pip2 * pip1 / 2*1 +
// pip1 / 1
// Note that the combinations counted here include straights.
		return (	vals[0] * (vals[0]-1) * (vals[0]-2) * (vals[0]-3) * (vals[0]-4) ) / 120 +
					vals[1] * (vals[1]-1) * (vals[1]-2) * (vals[1]-3) / 24 +
					vals[2] * (vals[2]-1) * (vals[2]-2) / 6 +
					vals[3] * (vals[3]-1) / 2 +
					vals[4];
	}
	
	public int calculateNonStraightPipCombinationsRank() {
		int temp = calculatePipCombinationsRank();
// Decrement straight-included rank for each straight combination included in that rank
		if (temp >= 1286) temp--; // TJQKA
		if (temp >= 791) temp--; // 9TJQK
		if (temp >= 461) temp--; // 89TJQ
		if (temp >=251) temp--; // 789TJ
		if (temp >=125) temp--; // 6789T
		if (temp >=55) temp--; // 56789
		if (temp >=20) temp--; // 45678
		if (temp >=5) temp--; // 34567
		if (temp >= 0) temp--; // 23456
		// no decrement necessary for A2345 because it is already rank 0.
		
		return temp;

	}
	
	public int calculateOnePairRank() {
		final int maxHighCardRank = 1277; // maximum rank of the next lower showdown category
		final int maxKickerRank = 880; // maximum rank of 3 card kicker combinations = 12*11*10/3*2*1
		int[] vals = new int[3];
		// Calculate the rank of the three kicker cards
		// calculations are based on non-ordered combination formula (n!) / ((n-r)!*(r!), n = # of items, r = # of selections
		// Reformatted for our purposes to: ( (n!) / (n-r)! ) / (r!)
		// pip3 * pip2 * pip1 / 3*2*1 +
		// pip2 * pip1 / 2*1 +
		// pip1 / 1
		for (int i = 2; i < 5; i++) {
//System.out.println("dbg Hand.CalculateOnePairRank(): i="+Integer.toString(i)+" vals:"+Integer.toString(vals.length)+" distinguishers:"+Integer.toString(distinguishers.length));
			vals[i-2] = distinguishers[i];
		}
		int kickerRank =	vals[0] * (vals[0]-1) * (vals[0]-2) / 6 +
							vals[1] * (vals[1]-1) / 2 +
							vals[2];

		return 	maxHighCardRank + 1 + // start at the rank just higher than lower showdown category rank
				distinguishers[1] * maxKickerRank + // rank floor for this pair accumulates combinations of kickers for lower pair pips
				kickerRank;  // add the rank of this specific set of kickers with this pair
	}

	
	public int calculateTwoPairRank() {
		final int maxOnePairRank = 4137; // maximum rank of the next lower showdown category
		final int maxKickerRank = 11; // sum of kicker combinations for each pair set = 13-2 = 11
		
		// Calculate the rank of the combination of the two pairs (alone) within the set of pairs
		// calculations are based on non-ordered combination formula (n!) / ((n-r)!*(r!), n = # of items, r = # of selections
		// Reformatted for our purposes to: ( (n!) / (n-r)! ) / (r!)
		// pip2 * pip1 / 2*1 +
		// pip1 / 1
		int pairSetRank =	distinguishers[1] * (distinguishers[1]-1) / 2 +
							distinguishers[2];
		int temp = distinguishers[3]; // for rank of the kicker
		if (distinguishers[3] > distinguishers[1]) {
			temp--; // Decrement if the kicker is greater than the greater of the pairs
		}
		if (distinguishers[3] > distinguishers[2]) {
			temp--; // Decrement if the kicker is greater than the lesser of the pairs
		}
		return 	maxOnePairRank + 1 + //start at the rank just higher than lower showdown category
				pairSetRank * maxKickerRank + //pair set rank * accumulation of kicker card possibilities for all lower pair sets
				temp; // add the rank of the kicker
	}

	public int calculateThreeOfAKindRank() {
		final int maxTwoPairRank = 4995; // maximum rank of the next lower showdown category
		final int maxKickerRank = 66;  // sum of kicker combinations for each set of trips = 12*11/2
		int kickerRank =	distinguishers[1] * (distinguishers[1]-1) /2 +
							distinguishers[2];
		return maxTwoPairRank + 1 + //start at the rank just higher than lower showdown category
				distinguishers[1] * maxKickerRank +
				kickerRank; // add the rank of this specific set of kickers
	}
	
	public int calculateStraightRank() {
		final int maxThreeOfAKindRank = 5853;
		return 	maxThreeOfAKindRank + 1 + // maximum rank of the next lower showdown category
				distinguishers[1] - 4; // high card determines remainder of rank, starting with high card 5 as the lowest straight
	}

	public int calculateFlushRank() {
		final int maxStraightRank = 5862;
		return	maxStraightRank + 1 + // maximum rank of the next lower showdown category
				calculateNonStraightPipCombinationsRank(); // specific rank of the kickers is the same as high card rank
	}
	
	public int calculateFullHouseRank() {
		final int maxFlushRank = 7140; // maximum rank of the next lower showdown category
		final int maxKickerRank = 12; // 12 combinations of single card = 13 - 1
		int temp = distinguishers[2];
		if (distinguishers[2] > distinguishers[1]) {
			temp--; // decrement for the case where the pair of the full house has higher pip than the trips
		}
		return maxFlushRank + 1 + // start at the rank just higher than the lower showdown category
				distinguishers[1] * maxKickerRank + // Trips portion rank includes accumulation of remaining card combinations
				temp; // rank of the pair portion of the full house
	}
	
	public int calculateFourOfAKindRank() {
		final int maxFullHouseRank = 7296; // maximum rank of the next lower showdown category
		final int maxKickerRank = 12; // 12 combinations of remaining kicker;
		int temp = distinguishers[2];
		if (distinguishers[2] > distinguishers[1]) {
			temp--; // decrement for the case where the kicker has higher pip than the four mathcing cards
		}
		return	maxFullHouseRank + 1 + // start at the rank just higher than the lower showdown category
				distinguishers[1] * maxKickerRank + // rank within matching four sets
				temp;
	}
	
	public int calculateStraightFlushRank() {
		final int maxFourOfAKindRank = 7452; // maximum rank of the next lower showdown category
		return	maxFourOfAKindRank + 1 + // start at the rank just higher than the lower showdown category
				distinguishers[1] - 4; // rank within set of straights is based on high card, starting with 5
	}
	
	public ShowdownCategoryName getShowdownCategoryName() {
		return categoryName;
	}
	
	public static int getShowdownCategoryValue(ShowdownCategoryName inCategory) {
		if (inCategory == null) {
			throw new NullPointerException();
		} else {
			switch (inCategory) {
			case STRAIGHTFLUSH:
				return 8;
			case FOUROFAKIND:
				return 7;
			case FULLHOUSE:
				return 6;
			case FLUSH:
				return 5;
			case STRAIGHT:
				return 4;
			case THREEOFAKIND:
				return 3;
			case TWOPAIR:
				return 2;
			case ONEPAIR:
				return 1;
			case HIGHCARD:
				return 0;
			default:
				return -1;
			}
		}
	}
	
	public int getHandRank() {
		return handRank;
	}
	
	public int getValue() {
		return handValue;
	}
	
	public String getAbbreviation() {
		return cardSet.getAbbreviation();
	}
	
	public ShowdownCategoryName getCategoryName() {
		return categoryName;
	}
	
	public int[] getDistinguishers() {
		int[] temp = new int[distinguishers.length];
		for (int i = 0; i < distinguishers.length; i++) {
			temp[i] = distinguishers[i];
		}
		return temp;
	}

/* 	public static Comparator HandRankComparator = new Comparator<Hand>() {
		public int compare() {
			return 0;
		}	    	 
	}
*/
	
}
