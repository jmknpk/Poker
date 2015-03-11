package org.jmknpk.standardPoker;

import java.util.Random;

/* class CardSet represents any ordered subset of the 52 Cards in a deck */

public class CardSet {
	private Card[] cards;
	private static Random randNum = new Random();
	
	public CardSet() {
		this( new Card[0]);
	}
	
	public CardSet(Card card1, Card card2, Card card3, Card card4, Card card5) {
		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			throw new NullPointerException();
		} else {
			cards = new Card[5];
			cards[0] = card1;
			cards[1] = card2;
			cards[2] = card3;
			cards[3] = card4;
			cards[4] = card5;
		}
	}
	
	public CardSet(Card[] inCards) {
		if (inCards == null) {
			throw new NullPointerException();
		} else {
			cards = new Card[inCards.length];
			for (int i = 0; i < inCards.length; i++) {
				cards[i] = inCards[i];
			}
		}
	}

	public CardSet(CardSet inSet) {
		if (inSet == null) {
			throw new NullPointerException();
		} else {
			cards = new Card[inSet.getSize()];
			for (int i = 0; i < inSet.getSize(); i++) {
				cards[i] = inSet.getCard(i);
			}
		}
	}
	
	public boolean equals(CardSet inSet) {
		if (inSet == null) {
			throw new NullPointerException();
		} else {
			if (cards.length == inSet.getSize()) {
				boolean equal = true;
				for (int i = 0;  i < cards.length && equal; i++) {
					if (! cards[i].equals(inSet.getCard(i))) {
						equal = false;
					}
				}
				return equal;
			} else {
				return false; // sets of differing sizes
			}
		}
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			throw new NullPointerException();
		} else {
			if (o instanceof CardSet) {
				return equals((CardSet) o);
			} else {
				return false;
			}
		}
	}
	
	public Card getCard(int i) {
		if (i < 0 || i >= cards.length) {
			throw new IllegalArgumentException();
		} else {
			return cards[i];
		}
	}

	public void set(CardSet inSet) {
		if (inSet == null) {
			throw new NullPointerException();
		} else if (cards.length != inSet.getSize()) {
			throw new IllegalArgumentException("CardSet.set() parameter size differs from object");
		} else {
			for (int i = 0; i < cards.length; i++) {
				cards[i] = inSet.getCard(i);
			}
		}
	}
	
	public int getSize() {
		return cards.length;
	}
	
	public void sort(boolean ascending) {
		Card temp;
		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i+1; j < cards.length; j++) {
				if (ascending && cards[i].compareTo(cards[j]) > 0 ||
					!ascending && cards[i].compareTo(cards[j]) < 0) {
						temp = cards[i];
						cards[i] = cards[j];
						cards[j] = temp;
				}
			}
		}
	}

	public void shuffle() {
		Card temp;
		for (int i = 0; i < cards.length; i++) {
			int j = randNum.nextInt(cards.length);
			temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}
	
	public void cut() {
		int cutPoint = randNum.nextInt(cards.length);
		Card[] temp = new Card[cards.length];
		for (int i = 0; i < cards.length; i++) {
				temp[i] = cards[i];
		}
		int j = 0;
		for (int i = cutPoint; i < cards.length; i++) {
			cards[j++] = temp[i];
		}
		for (int i = 0; i < cutPoint; i++) {
			cards[j++] = temp[i];
		}
	}

	public void shuffleAndCut() {
		shuffle();
		cut();
	}
	
	public void sort() {
		sort(true);
	}
	
	public static CardSet standardDeck() {
		Card[] suitPipCards = new Card[52];
		int arrayCount = 0;
		for ( SuitName suitName : SuitName.values()) {
			for (PipName pipName : PipName.values()) {
				if (pipName != PipName.UNDEFINED && suitName != SuitName.UNDEFINED) {
					suitPipCards[arrayCount++] = new Card(pipName,suitName);
				}
			}
		}
		return new CardSet(suitPipCards);
	}
	
	public String getAbbreviation() {
		String temp = "";
		for (int i = 0; i < cards.length; i++) {
			temp = temp + cards[i].getAbbreviation();
		}
		return temp;
	}

}
