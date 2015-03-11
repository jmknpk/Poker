package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CardTest {

	@Test
	public void test() {
		Card card;
		Card typeCard;
		Card enumCard;
		Card cardCard;
		Card valueCard;
		int[] hashCodes = new int[53];
		Card[] enumArray = new Card[53];
		Card[] valueArray = new Card[53];
		int arrayCount = 0;
		
		card = new Card();
		assertTrue(card != null); // assert constructor returns non-null
		assertEquals(card.getPipName(),PipName.UNDEFINED); // assert constructor returns appropriate object
		assertEquals(card.getSuitName(),SuitName.UNDEFINED);
		Card nullCard = null;
		Object badObject = new String(); 
		assertTrue(!card.equals(nullCard));
		assertTrue(!card.equals(badObject));
		
		typeCard = new Card(new Pip(PipName.TWO), new Suit(SuitName.CLUB));
		assertTrue(typeCard != null); // assert constructor returns non-null
		assertEquals(typeCard.getPipName(),PipName.TWO);
		assertEquals(typeCard.getSuitName(),SuitName.CLUB);
		
		
		for (SuitName suitName : SuitName.values()) {
			for (PipName pipName : PipName.values()) {
				enumCard = new Card (pipName,suitName);
				assertTrue(enumCard != null);
				if (pipName != PipName.UNDEFINED && suitName != SuitName.UNDEFINED) {
					assertEquals(enumCard.getPipName(),pipName);
					assertEquals(enumCard.getSuitName(),suitName);
					enumArray[arrayCount] = enumCard;
					hashCodes[arrayCount++] = enumCard.hashCode();
				}
				
			}
			
		}
		enumCard = new Card(PipName.UNDEFINED,SuitName.UNDEFINED);  // only one joker, not suited or ranked
		assertEquals(enumCard.getPipName(),PipName.UNDEFINED);
		assertEquals(enumCard.getSuitName(),SuitName.UNDEFINED);

		enumCard = new Card(PipName.UNDEFINED,SuitName.UNDEFINED);
		enumArray[arrayCount] = enumCard;
		hashCodes[arrayCount++] = enumCard.hashCode();
		
		cardCard = new Card(typeCard);
		assertTrue(cardCard != null);
		assertEquals(cardCard.getPipName(),PipName.TWO);
		assertEquals(cardCard.getSuitName(),SuitName.CLUB);
		assertTrue(!cardCard.equals(new Card())); // assure equality is not based on Object pointer
		
		for (int i = -1; i < 52; i++) {

			valueCard = new Card(i);
			assertTrue(valueCard != null);
			assertEquals(valueCard.getValue(),i);
			
			valueArray[i+1] = valueCard;
			
			switch (i) {
				case -1:
					assertEquals(valueCard.getPipName(),PipName.UNDEFINED);
					assertEquals(valueCard.getSuitName(),SuitName.UNDEFINED);
					assertEquals(valueCard.getName(),"Joker");
					assertEquals(valueCard.getAbbreviation(),"*j");
					break;
				case 0:
					assertEquals(valueCard.getPipName(),PipName.TWO);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Two of Clubs");
					assertEquals(valueCard.getAbbreviation(),"2c");
					break;
				case 1:
					assertEquals(valueCard.getPipName(),PipName.TWO);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Two of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"2d");
					break;
				case 2:
					assertEquals(valueCard.getPipName(),PipName.TWO);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Two of Hearts");
					assertEquals(valueCard.getAbbreviation(),"2h");
					break;
				case 3:
					assertEquals(valueCard.getPipName(),PipName.TWO);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Two of Spades");
					assertEquals(valueCard.getAbbreviation(),"2s");
					break;
				case 4:
					assertEquals(valueCard.getPipName(),PipName.THREE);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Three of Clubs");
					assertEquals(valueCard.getAbbreviation(),"3c");
					break;
				case 5:
					assertEquals(valueCard.getPipName(),PipName.THREE);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Three of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"3d");
					break;
				case 6:
					assertEquals(valueCard.getPipName(),PipName.THREE);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Three of Hearts");
					assertEquals(valueCard.getAbbreviation(),"3h");
					break;
				case 7:
					assertEquals(valueCard.getPipName(),PipName.THREE);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Three of Spades");
					assertEquals(valueCard.getAbbreviation(),"3s");
					break;
				case 8:
					assertEquals(valueCard.getPipName(),PipName.FOUR);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Four of Clubs");
					assertEquals(valueCard.getAbbreviation(),"4c");
					break;
				case 9:
					assertEquals(valueCard.getPipName(),PipName.FOUR);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Four of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"4d");
					break;
				case 10:
					assertEquals(valueCard.getPipName(),PipName.FOUR);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Four of Hearts");
					assertEquals(valueCard.getAbbreviation(),"4h");
					break;
				case 11:
					assertEquals(valueCard.getPipName(),PipName.FOUR);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Four of Spades");
					assertEquals(valueCard.getAbbreviation(),"4s");
					break;
				case 12:
					assertEquals(valueCard.getPipName(),PipName.FIVE);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Five of Clubs");
					assertEquals(valueCard.getAbbreviation(),"5c");
					break;
				case 13:
					assertEquals(valueCard.getPipName(),PipName.FIVE);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Five of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"5d");
					break;
				case 14:
					assertEquals(valueCard.getPipName(),PipName.FIVE);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Five of Hearts");
					assertEquals(valueCard.getAbbreviation(),"5h");
					break;
				case 15:
					assertEquals(valueCard.getPipName(),PipName.FIVE);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Five of Spades");
					assertEquals(valueCard.getAbbreviation(),"5s");
					break;
				case 16:
					assertEquals(valueCard.getPipName(),PipName.SIX);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Six of Clubs");
					assertEquals(valueCard.getAbbreviation(),"6c");
					break;
				case 17:
					assertEquals(valueCard.getPipName(),PipName.SIX);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Six of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"6d");
					break;
				case 18:
					assertEquals(valueCard.getPipName(),PipName.SIX);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Six of Hearts");
					assertEquals(valueCard.getAbbreviation(),"6h");
					break;
				case 19:
					assertEquals(valueCard.getPipName(),PipName.SIX);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Six of Spades");
					assertEquals(valueCard.getAbbreviation(),"6s");
					break;
				case 20:
					assertEquals(valueCard.getPipName(),PipName.SEVEN);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Seven of Clubs");
					assertEquals(valueCard.getAbbreviation(),"7c");
					break;
				case 21:
					assertEquals(valueCard.getPipName(),PipName.SEVEN);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Seven of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"7d");
					break;
				case 22:
					assertEquals(valueCard.getPipName(),PipName.SEVEN);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Seven of Hearts");
					assertEquals(valueCard.getAbbreviation(),"7h");
					break;
				case 23:
					assertEquals(valueCard.getPipName(),PipName.SEVEN);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Seven of Spades");
					assertEquals(valueCard.getAbbreviation(),"7s");
					break;
				case 24:
					assertEquals(valueCard.getPipName(),PipName.EIGHT);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Eight of Clubs");
					assertEquals(valueCard.getAbbreviation(),"8c");
					break;
				case 25:
					assertEquals(valueCard.getPipName(),PipName.EIGHT);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Eight of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"8d");
					break;
				case 26:
					assertEquals(valueCard.getPipName(),PipName.EIGHT);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Eight of Hearts");
					assertEquals(valueCard.getAbbreviation(),"8h");
					break;
				case 27:
					assertEquals(valueCard.getPipName(),PipName.EIGHT);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Eight of Spades");
					assertEquals(valueCard.getAbbreviation(),"8s");
					break;
				case 28:
					assertEquals(valueCard.getPipName(),PipName.NINE);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Nine of Clubs");
					assertEquals(valueCard.getAbbreviation(),"9c");
					break;
				case 29:
					assertEquals(valueCard.getPipName(),PipName.NINE);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Nine of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"9d");
					break;
				case 30:
					assertEquals(valueCard.getPipName(),PipName.NINE);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Nine of Hearts");
					assertEquals(valueCard.getAbbreviation(),"9h");
					break;
				case 31:
					assertEquals(valueCard.getPipName(),PipName.NINE);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Nine of Spades");
					assertEquals(valueCard.getAbbreviation(),"9s");
					break;
				case 32:
					assertEquals(valueCard.getPipName(),PipName.TEN);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Ten of Clubs");
					assertEquals(valueCard.getAbbreviation(),"Tc");
					break;
				case 33:
					assertEquals(valueCard.getPipName(),PipName.TEN);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Ten of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"Td");
					break;
				case 34:
					assertEquals(valueCard.getPipName(),PipName.TEN);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Ten of Hearts");
					assertEquals(valueCard.getAbbreviation(),"Th");
					break;
				case 35:
					assertEquals(valueCard.getPipName(),PipName.TEN);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Ten of Spades");
					assertEquals(valueCard.getAbbreviation(),"Ts");
					break;
				case 36:
					assertEquals(valueCard.getPipName(),PipName.JACK);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Jack of Clubs");
					assertEquals(valueCard.getAbbreviation(),"Jc");
					break;
				case 37:
					assertEquals(valueCard.getPipName(),PipName.JACK);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Jack of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"Jd");
					break;
				case 38:
					assertEquals(valueCard.getPipName(),PipName.JACK);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Jack of Hearts");
					assertEquals(valueCard.getAbbreviation(),"Jh");
					break;
				case 39:
					assertEquals(valueCard.getPipName(),PipName.JACK);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Jack of Spades");
					assertEquals(valueCard.getAbbreviation(),"Js");
					break;
				case 40:
					assertEquals(valueCard.getPipName(),PipName.QUEEN);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Queen of Clubs");
					assertEquals(valueCard.getAbbreviation(),"Qc");
					break;
				case 41:
					assertEquals(valueCard.getPipName(),PipName.QUEEN);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Queen of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"Qd");
					break;
				case 42:
					assertEquals(valueCard.getPipName(),PipName.QUEEN);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Queen of Hearts");
					assertEquals(valueCard.getAbbreviation(),"Qh");
					break;
				case 43:
					assertEquals(valueCard.getPipName(),PipName.QUEEN);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Queen of Spades");
					assertEquals(valueCard.getAbbreviation(),"Qs");
					break;
				case 44:
					assertEquals(valueCard.getPipName(),PipName.KING);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"King of Clubs");
					assertEquals(valueCard.getAbbreviation(),"Kc");
					break;
				case 45:
					assertEquals(valueCard.getPipName(),PipName.KING);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"King of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"Kd");
					break;
				case 46:
					assertEquals(valueCard.getPipName(),PipName.KING);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"King of Hearts");
					assertEquals(valueCard.getAbbreviation(),"Kh");
					break;
				case 47:
					assertEquals(valueCard.getPipName(),PipName.KING);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"King of Spades");
					assertEquals(valueCard.getAbbreviation(),"Ks");
					break;
				case 48:
					assertEquals(valueCard.getPipName(),PipName.ACE);
					assertEquals(valueCard.getSuitName(),SuitName.CLUB);
					assertEquals(valueCard.getName(),"Ace of Clubs");
					assertEquals(valueCard.getAbbreviation(),"Ac");
					break;
				case 49:
					assertEquals(valueCard.getPipName(),PipName.ACE);
					assertEquals(valueCard.getSuitName(),SuitName.DIAMOND);
					assertEquals(valueCard.getName(),"Ace of Diamonds");
					assertEquals(valueCard.getAbbreviation(),"Ad");
					break;
				case 50:
					assertEquals(valueCard.getPipName(),PipName.ACE);
					assertEquals(valueCard.getSuitName(),SuitName.HEART);
					assertEquals(valueCard.getName(),"Ace of Hearts");
					assertEquals(valueCard.getAbbreviation(),"Ah");
					break;
				case 51:
					assertEquals(valueCard.getPipName(),PipName.ACE);
					assertEquals(valueCard.getSuitName(),SuitName.SPADE);
					assertEquals(valueCard.getName(),"Ace of Spades");
					assertEquals(valueCard.getAbbreviation(),"As");
					break;
				default:
					fail("Unexpected Card value");
					break;
			}
		}

		Card[] newDeck = new Card[52];
		arrayCount = 0;
		for (SuitName suit : SuitName.values()) {
			for (PipName pip : PipName.values()) {
				if (suit == SuitName.UNDEFINED || pip == PipName.UNDEFINED) {
					// do nothing
				} else {
					newDeck[arrayCount++] = new Card(pip, suit);
				}
			}
		}
		Card[] suitSortArray = new Card[52];
 		for (int i = 0; i < 52; i++) {
 			suitSortArray[i] = new Card(i);
 		}
 		Arrays.sort(suitSortArray, Card.SuitFirstComparator);
 		for (int i = 0; i < suitSortArray.length; i++) {
System.out.println("newDeck[]="+newDeck[i].getAbbreviation()+" suitSortArray[]="+suitSortArray[i].getAbbreviation());
//			assertEquals(newDeck[i],suitSortArray[i]);
 		}
 		
 		Arrays.sort(hashCodes);
		Arrays.sort(valueArray);
 		Arrays.sort(enumArray);

		for (int i = 0; i < 53; i++) {
			assertEquals(enumArray[i].getValue(),hashCodes[i]);
			assertTrue(enumArray[i].equals(valueArray[i]));
			assertTrue(valueArray[i].equals(enumArray[i]));
		}

		// Catch NullPointerException on all methods accepting object(s).		
/*
  		try { ; fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
*/				
		Pip nullPip = null;
		Suit nullSuit = null;
		Suit suit = new Suit();
		Pip pip = new Pip();

		try {new Card(nullPip,suit) ; fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
		
		try {new Card(pip,nullSuit) ; fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

		try { new Card(nullCard); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

		try { card.compareTo(nullCard); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

  		try { card.setPip(nullPip); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

  		try { card.setSuit(nullSuit); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

  		try { card.setCard(nullCard); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
	
		
	}
}
