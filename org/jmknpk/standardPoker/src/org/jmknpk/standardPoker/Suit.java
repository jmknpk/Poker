package org.jmknpk.standardPoker;

public class Suit implements Comparable<Suit> {
	private SuitName suitName;
	
	public Suit () {
		this(SuitName.UNDEFINED);
	}
	
	public Suit (SuitName inSuitName) {
		if (inSuitName == null) {
			throw new NullPointerException();
		} else {
			suitName = inSuitName;
		}
	}
	
	public Suit(Suit inSuit) {
		if (inSuit == null) {
			throw new NullPointerException();
		} else {
			this.suitName = inSuit.suitName;
		}
	}
	
	public Suit(int value) {
		switch(value) {
			case 0:
				suitName = SuitName.CLUB;
				break;
			case 1:
				suitName = SuitName.DIAMOND;
				break;
			case 2:
				suitName = SuitName.HEART;
				break;
			case 3:
				suitName = SuitName.SPADE;
				break;
			default:
				suitName = SuitName.UNDEFINED;
				break;
		}
	}

	public boolean equals(Suit inSuit) {
		if (inSuit == null) {
			return false;
		} else if (this.suitName.equals(inSuit.suitName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (getClass().equals(o.getClass())) {
			return this.equals((Suit) o);
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getValue();
	}

	public int compareTo(Suit inSuit) {
		if (inSuit == null) {
			throw new NullPointerException();
		} else {
			if (this.equals(inSuit)) {
				return 0;
			} else if (getValue() > inSuit.getValue()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
	public void setSuitName(SuitName inSuitName) {
		if (inSuitName == null) {
			throw new NullPointerException();
		} else {
			suitName = inSuitName;
		}
	}
	
	public void setSuitName(Suit inSuit) {
		if (inSuit == null) {
			throw new NullPointerException();
		} else {
			suitName = inSuit.getSuitName();
		}
	}

	public String getName() {
		switch (suitName) {
			case CLUB:
				return "Clubs";
			case DIAMOND:
				return "Diamonds";
			case HEART:
				return "Hearts";
			case SPADE:
				return "Spades";
			case UNDEFINED:
				return "Undefined";
			default:
				return "?";
		}
	}
	
	public int getValue() {
		switch(suitName) {
			case CLUB:
				return 0;
			case DIAMOND:
				return 1;
			case HEART:
				return 2;
			case SPADE:
				return 3;
			case UNDEFINED:
				return -1;
			default:
				return -2;
		}
	}
	
	public String getInitial() {
		switch (suitName) {
			case CLUB:
				return "c";
			case DIAMOND:
				return "d";
			case HEART:
				return "h";
			case SPADE:
				return "s";
			case UNDEFINED:
				return "u";
			default:
				return "?";
		}
	}

	public SuitName getSuitName() {
		return suitName;
	}
	
}
