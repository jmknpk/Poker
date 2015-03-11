package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardSetTest {

	@Test
	public void test() {
		Card[] valueCards = new Card[52];
		Card[] suitCards = new Card[52];
		int arrayCount;
		
		CardSet set = new CardSet();
		assertTrue(set != null);
		assertEquals(set.getSize(),0);
		assertTrue(set.equals(new CardSet())); // assert equality is based on content rather than object pointer

		for (int i = 0; i < 52; i++) {
			valueCards[i] = new Card(i);
		}
		
		CardSet set5 = new CardSet(valueCards[0],valueCards[10],valueCards[25],valueCards[40],valueCards[51]);
		assertTrue(set5 != null);
		assertEquals(set5.getSize(),5);
		assertEquals(set5.getCard(0),valueCards[0]);
		assertEquals(set5.getCard(1),valueCards[10]);
		assertEquals(set5.getCard(2),valueCards[25]);
		assertEquals(set5.getCard(3),valueCards[40]);
		assertEquals(set5.getCard(4),valueCards[51]);
		assertTrue(!set5.equals(set));

		CardSet newSet5 = new CardSet(set5);
		assertTrue(newSet5 != null);
		assertTrue(newSet5.equals(set5));

		CardSet set52 = new CardSet(valueCards);
		assertTrue(set52 != null);
		assertEquals(set52.getSize(),52);
		for (int i = 0; i < valueCards.length; i++) {
			assertEquals(set52.getCard(i),valueCards[i]);
		}

		arrayCount = 0;
		for ( SuitName suitName : SuitName.values()) {
			for (PipName pipName : PipName.values()) {
				if (pipName != PipName.UNDEFINED && suitName != SuitName.UNDEFINED) {
					suitCards[arrayCount++] = new Card(pipName,suitName);
				}
			}
		}
		CardSet suitSet = new CardSet(suitCards);
		assertEquals(suitSet,CardSet.standardDeck());
		CardSet valueSet = new CardSet(valueCards);
		suitSet.sort();
		assertEquals(suitSet,valueSet);
		valueSet.sort(false);
		for (int i = 0; i < suitSet.getSize(); i ++) {
			assertEquals(suitSet.getCard(i),valueSet.getCard(51-i));
		}
		suitSet.set(valueSet);
		assertTrue(valueSet.equals(suitSet));
		valueSet.shuffleAndCut();
		assertTrue(!valueSet.equals(suitSet));  // keep in mind the rare possibility that a random shuffle returns perfectly sorted
		
	}

}
