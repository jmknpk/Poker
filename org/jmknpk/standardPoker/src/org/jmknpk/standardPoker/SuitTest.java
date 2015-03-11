package org.jmknpk.standardPoker;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuitTest {

	@Test
	public void test() {

		Suit suit;
		Suit suitNameSuit;
		Suit[] suitNameArray = new Suit[5];
		Suit suitSuit;
		Suit valueSuit;
		Suit[] valueArray = new Suit[5];
		int[] hashCodes = new int[5];
		int arrayCounter = 0;
		
		suit = new Suit();
		assertTrue(suit != null); // assert constructor returns non-null
		assertEquals(suit.getSuitName(),SuitName.UNDEFINED); // assert constructor returns appropriate object
		Suit nullSuit = null;
		Object badObject = new String();
		assertTrue(!suit.equals(nullSuit));
		assertTrue(!suit.equals(badObject));
		
		for (SuitName suitName : SuitName.values()) {
			suitNameSuit = new Suit(suitName);
			assertTrue(suitNameSuit != null); // assert constructor returns non-null
			assertEquals(suitNameSuit.getSuitName(),suitName); // assert constructor returns appropriate object
			suitNameArray[arrayCounter] = suitNameSuit;
			hashCodes[arrayCounter++] = suitNameSuit.hashCode();
			
			switch (suitName) {
				case CLUB:
					assertEquals(suitNameSuit.getSuitName(),SuitName.CLUB);
					assertEquals(suitNameSuit.getName(),"Clubs");
					assertEquals(suitNameSuit.getInitial(),"c");
					assertEquals(suitNameSuit.getValue(),0);
					break;
				case DIAMOND:
					assertEquals(suitNameSuit.getSuitName(),SuitName.DIAMOND);
					assertEquals(suitNameSuit.getName(),"Diamonds");
					assertEquals(suitNameSuit.getInitial(),"d");
					assertEquals(suitNameSuit.getValue(),1);
					break;
				case HEART:
					assertEquals(suitNameSuit.getSuitName(),SuitName.HEART);
					assertEquals(suitNameSuit.getName(),"Hearts");
					assertEquals(suitNameSuit.getInitial(),"h");
					assertEquals(suitNameSuit.getValue(),2);
					break;
				case SPADE:
					assertEquals(suitNameSuit.getSuitName(),SuitName.SPADE);
					assertEquals(suitNameSuit.getName(),"Spades");
					assertEquals(suitNameSuit.getInitial(),"s");
					assertEquals(suitNameSuit.getValue(),3);
					break;
				case UNDEFINED:
					assertEquals(suitNameSuit.getSuitName(),SuitName.UNDEFINED);
					assertEquals(suitNameSuit.getName(),"Undefined");
					assertEquals(suitNameSuit.getInitial(),"u");
					assertEquals(suitNameSuit.getValue(),-1);
					break;
				default :
					fail ("Unexpected SuitName");
					break;
			}
			
			suitSuit = new Suit(suitNameSuit);
			assertTrue(suitSuit != null); // assert constructor returns non-null
			assertEquals(suitSuit.getSuitName(),suitNameSuit.getSuitName()); // assert constructor returns appropriate object
			assertTrue(suitSuit.equals(suitNameSuit)); // assure equality is not based upon object pointer
			assertTrue(!suitSuit.equals(null)); // assure non-equality with null pointer
			assertTrue(!suitSuit.equals(new String())); //check non-equality with non-class object
			
		}
		
		for (int i = -1; i < 5; i++) {
			valueSuit = new Suit(i);
			assertTrue(valueSuit != null); //assert constructor returns non-null
			if (i >= -1 && i <= 3) {
				assertEquals(valueSuit.getValue(),i); //assert constructor returns appropriate object
				valueArray[i+1] = valueSuit;
			} else {
				assertEquals(valueSuit.getValue(),-1); // assert constructor returns appropriate object
			}
		}
		
		Arrays.sort(suitNameArray);
		Arrays.sort(valueArray);
		Arrays.sort(hashCodes);
		
		for (int i = 0; i < 5; i++) {
			assertEquals(suitNameArray[i].hashCode(),hashCodes[i]);
			assertEquals(suitNameArray[i].hashCode(),valueArray[i].hashCode());
			assertTrue(suitNameArray[i].equals(valueArray[i]));
			assertTrue(valueArray[i].equals(suitNameArray[i]));
		}
		
		
// Catch NullPointerException on all methods accepting object(s).		
/*
  		try { ; fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
*/				

		SuitName nullSuitName = null;
  		try { new Suit(nullSuitName); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

  		try { new Suit(nullSuit); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
  		
  		try { suit.compareTo(nullSuit); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

  		try { suit.setSuitName(nullSuitName); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

  		try { suit.setSuitName(nullSuit); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
		
	}

}
