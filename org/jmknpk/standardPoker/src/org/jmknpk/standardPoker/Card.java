package org.jmknpk.standardPoker;

import java.util.Comparator;

public class Card implements Comparable<Card>, Comparator<Card> {
	private Pip pip;
	private Suit suit;

	public static final Comparator<Card> SuitFirstComparator = new Comparator<Card>() {
		public int compare(Card c1, Card c2) {
			if (c1 == null || c2 == null) {
				throw new NullPointerException();
			} else if (c1.getSuit().equals(c2.getSuit())) {
				if (c1.getPip().equals(c2.getPip())) {
					return 0;
				} else if (c1.getPipValue() > c2.getPipValue()) {
					return 1;
				} else {
					return -1;
				}
			} else if (c1.getSuitValue() > c2.getSuitValue()) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	public int compare(Card c1, Card c2) {
		if (c1 == null || c2 == null) {
			throw new NullPointerException();
		} else {
			if (c1.equals(c2)) {
				return 0;
			} else if (c1.getValue() > c2.getValue()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
	public Card() {
		this(PipName.UNDEFINED, SuitName.UNDEFINED);
	}
	
	public Card(Pip inPip, Suit inSuit) {
		if (inSuit == null || inPip == null) {
			throw new NullPointerException();
		}
		if (inSuit.getSuitName().equals(SuitName.UNDEFINED)) {
			pip = new Pip(PipName.UNDEFINED);
		} else {
			pip = inPip;
		}
		if (pip.getPipName().equals(PipName.UNDEFINED)) {
			suit = new Suit(SuitName.UNDEFINED);
		} else {
			suit = inSuit;
		}
	}
	
	public Card(PipName inPipName, SuitName inSuitName) {
		if (inPipName == null || inSuitName == null) {
			throw new NullPointerException();
		}
		if (inSuitName == SuitName.UNDEFINED) {
			pip = new Pip(PipName.UNDEFINED);
		} else {
			pip = new Pip(inPipName);
		}
		if (inPipName == PipName.UNDEFINED) {
			suit = new Suit(SuitName.UNDEFINED);
		} else {
			suit = new Suit(inSuitName);
		}
	}

	public Card(Card inCard) {
		if (inCard == null) {
			throw new NullPointerException();
		} else {
			pip = inCard.getPip();
			suit = inCard.getSuit();
		}
	}
	
	public Card(int value) {
		int pipInt;
		int suitInt;
		if (value < 0 || value > 51) {
			pip = new Pip(PipName.UNDEFINED);
			suit = new Suit(SuitName.UNDEFINED);
		} else {
			suitInt = value % 4;
			pipInt = (value - suitInt) / 4;
			pip = new Pip(pipInt);
			suit = new Suit(suitInt);
		}
	}

	public boolean equals(Card inCard) {
		if (inCard == null) {
			return false;
		} else if (suit.equals(inCard.suit) && pip.equals(inCard.pip)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (getClass().equals(o.getClass())) {
			return equals((Card) o);
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getValue();
	}

	public int compareTo(Card inCard) {
		if (inCard == null) {
			throw new NullPointerException();
		} else if (equals(inCard)) {
			return 0;
		} else if (getValue() > inCard.getValue()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public void setPip(Pip inPip) {
		if (inPip == null) {
			throw new NullPointerException();
		} else {
			pip = inPip;
		}
	}

	public void setPip(PipName inPipName) {
		if (inPipName == null) {
			throw new NullPointerException();
		} else {
			pip = new Pip(inPipName);
		}
	}
	
	public void setSuit(Suit inSuit) {
		if (inSuit == null) {
			throw new NullPointerException();
		} else {
			suit = inSuit;
		}
	}

	public void setSuit(SuitName inSuitName) {
		suit = new Suit(inSuitName);
	}

	public void setCard(Pip inPip, Suit inSuit) {
		if (inPip == null || inSuit == null) {
			throw new NullPointerException();
		} else {
			pip = inPip;
			suit = inSuit;
		}
	}

	public void setCard(PipName inPipName, SuitName inSuitName) {
		pip = new Pip(inPipName);
		suit = new Suit(inSuitName);
	}

	public void setCard(Card inCard) {
		if (inCard == null) {
			throw new NullPointerException();
		} else {
			pip = inCard.pip;
			suit = inCard.suit;
		}
	}

	public void setCard(int value) {
		int pipInt;
		int suitInt;
		if (value < 0 || value > 51) {
			pip = new Pip(PipName.UNDEFINED);
			suit = new Suit(SuitName.UNDEFINED);
		}
		suitInt = value % 4;
		pipInt = (value - suitInt) / 4;
		pip = new Pip(pipInt);
		suit = new Suit(suitInt);
		
	}
	
	public Pip getPip() {
		return pip;
	}

	public PipName getPipName() {
		return pip.getPipName();
	}
	
	public Suit getSuit() {
		return suit;
	}

	public SuitName getSuitName() {
		return suit.getSuitName();
	}
	
	public int getPipValue() {
		return pip.getValue();
	}
	
	public int getSuitValue() {
		return suit.getValue();
	}
	
	public int getValue() {
		if (pip.getValue() < 0 || suit.getValue() < 0) {
			return -1;
		} else {
			return pip.getValue()*4 + suit.getValue();
		}
	}
	
	public String getName() {
		if (pip.getPipName() == PipName.UNDEFINED || suit.getSuitName() == SuitName.UNDEFINED) {
			return "Joker";
		} else {
			return pip.getName() + " of " + suit.getName();
		}
	}
	
	public String getAbbreviation() {
		if (pip.getPipName() == PipName.UNDEFINED || suit.getSuitName() == SuitName.UNDEFINED) {
			return "*j";
		} else {
			return pip.getInitial() + suit.getInitial();
		}
	}
	
}
