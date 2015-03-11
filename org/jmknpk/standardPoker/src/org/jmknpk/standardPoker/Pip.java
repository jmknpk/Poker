package org.jmknpk.standardPoker;


public class Pip implements Comparable<Pip> {

	private PipName pipName;
	
	public Pip () {
		this(PipName.UNDEFINED);
	}
	
	public Pip (PipName inPipName) {
		if (inPipName == null) {
			throw new NullPointerException();
		} else {
			pipName = inPipName;
		}
	}
	
	public Pip (Pip inPip) {
		if (inPip == null) {
			throw new NullPointerException();
		} else {
			pipName = inPip.pipName;
		}
	}
	
	public Pip (int invalue) {
		switch (invalue) {
			case 0:
				pipName = PipName.TWO;
				break;
			case 1:
				pipName = PipName.THREE;
				break;
			case 2:
				pipName = PipName.FOUR;
				break;
			case 3:
				pipName = PipName.FIVE;
				break;
			case 4:
				pipName = PipName.SIX;
				break;
			case 5:
				pipName = PipName.SEVEN;
				break;
			case 6:
				pipName = PipName.EIGHT;
				break;
			case 7:
				pipName = PipName.NINE;
				break;
			case 8:
				pipName = PipName.TEN;
				break;
			case 9:
				pipName = PipName.JACK;
				break;
			case 10:
				pipName = PipName.QUEEN;
				break;
			case 11:
				pipName = PipName.KING;
				break;
			case 12:
				pipName = PipName.ACE;
				break;
			default:
				pipName = PipName.UNDEFINED;
				break;
		}
	}
	
	public boolean equals(Pip inPip) {
		if (inPip == null) {
			return false;
		} else if (this.pipName.equals(inPip.pipName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (getClass().equals(o.getClass())) {
			return equals((Pip) o);
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getValue();
	}
	
	public int compareTo(Pip inPip) {
		if (inPip == null) {
			throw new NullPointerException();
		} else if (equals(inPip)) {
			return 0;
		} else if (getValue() > inPip.getValue()) {
			return 1;
		} else {
			return -1;
		}
	}

	public void setPipName(PipName inPipName) {
		if (inPipName == null) {
			throw new NullPointerException();
		} else {
			pipName = inPipName;
		}
	}
	
	public int getValue() {
		switch (pipName) {
			case TWO:
				return 0;
			case THREE:
				return 1;
			case FOUR:
				return 2;
			case FIVE:
				return 3;
			case SIX:
				return 4;
			case SEVEN:
				return 5;
			case EIGHT:
				return 6;
			case NINE:
				return 7;
			case TEN:
				return 8;
			case JACK:
				return 9;
			case QUEEN:
				return 10;
			case KING:
				return 11;
			case ACE:
				return 12;
			case UNDEFINED:
				return -1;
			default:
				return -2;
		}
	}

	
	public String getName() {
		switch (pipName) {
			case TWO:
				return "Two";
			case THREE:
				return "Three";
			case FOUR:
				return "Four";
			case FIVE:
				return "Five";
			case SIX:
				return "Six";
			case SEVEN:
				return "Seven";
			case EIGHT:
				return "Eight";
			case NINE:
				return "Nine";
			case TEN:
				return "Ten";
			case JACK:
				return "Jack";
			case QUEEN:
				return "Queen";
			case KING:
				return "King";
			case ACE:
				return "Ace";
			case UNDEFINED:
				return "Undefined";
			default:
				return "?";
		}
	}
	
	
	public String getInitial() {
		switch (pipName) {
			case TWO:
				return "2";
			case THREE:
				return "3";
			case FOUR:
				return "4";
			case FIVE:
				return "5";
			case SIX:
				return "6";
			case SEVEN:
				return "7";
			case EIGHT:
				return "8";
			case NINE:
				return "9";
			case TEN:
				return "T";
			case JACK:
				return "J";
			case QUEEN:
				return "Q";
			case KING:
				return "K";
			case ACE:
				return "A";
			case UNDEFINED:
				return "u";
			default:
				return "?";
		}
	}
	
	public PipName getPipName() {
		return pipName;
	}
	
}
