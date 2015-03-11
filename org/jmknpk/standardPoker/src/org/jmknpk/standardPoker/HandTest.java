package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class HandTest {

	@Test
	public void test() {
		
		Hand[] hands = new Hand[22];
		
		Hand hand1 = new Hand();
		hands[0] = hand1;
		assertTrue(hand1 != null);
		assertEquals(hand1.getCard(0),new Card(PipName.SEVEN,SuitName.CLUB));
		assertEquals(hand1.getCard(1),new Card(PipName.FIVE,SuitName.CLUB));
		assertEquals(hand1.getCard(2),new Card(PipName.FOUR,SuitName.CLUB));
		assertEquals(hand1.getCard(3),new Card(PipName.THREE,SuitName.CLUB));
		assertEquals(hand1.getCard(4),new Card(PipName.TWO,SuitName.DIAMOND));
		
		CardSet cardSet = new CardSet(	new Card(PipName.TWO,SuitName.DIAMOND),
									new Card(PipName.THREE,SuitName.CLUB),
									new Card(PipName.FOUR,SuitName.CLUB),
									new Card(PipName.FIVE,SuitName.CLUB),
									new Card(PipName.SEVEN,SuitName.CLUB)
									);
		Hand hand2 = new Hand(cardSet);
		hands[1] = hand2;
		assertTrue(hand2 != null);
		assertTrue(hand1.equals(hand2));
		assertEquals(hand1,hand2);

		Hand hand3 = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB)));
		hands[2] = hand3;
		assertEquals(hand3.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand4 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[3] = hand4;
		assertEquals(hand4.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand5 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.TEN,SuitName.HEART),
											new Card(PipName.TEN,SuitName.SPADE),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[4] = hand5;
		assertEquals(hand5.getShowdownCategoryName(),ShowdownCategoryName.FOUROFAKIND);

		Hand hand6 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.TEN,SuitName.HEART),
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.DIAMOND)));
		hands[5] = hand6;
		assertEquals(hand6.getShowdownCategoryName(),ShowdownCategoryName.FULLHOUSE);

		Hand hand7 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[6] = hand7;
		assertEquals(hand7.getShowdownCategoryName(),ShowdownCategoryName.FLUSH);

		Hand hand8 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[7] = hand8;
		assertEquals(hand8.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);

		Hand hand9 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.TEN,SuitName.HEART),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[8] = hand9;
		assertEquals(hand9.getShowdownCategoryName(),ShowdownCategoryName.THREEOFAKIND);

		Hand hand10 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.DIAMOND),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[9] = hand10;
		assertEquals(hand10.getShowdownCategoryName(),ShowdownCategoryName.TWOPAIR);

		Hand hand11 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[10] = hand11;
		assertEquals(hand11.getShowdownCategoryName(),ShowdownCategoryName.ONEPAIR);

		Hand hand12 = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[11] = hand12;
		assertEquals(hand12.getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);

		Hand hand13 = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB)));
		hands[12] = hand13;
		assertEquals(hand13.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand14 = new Hand( new CardSet(
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB)));
		hands[13] = hand14;
		assertEquals(hand14.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand15 = new Hand( new CardSet(
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB)));
		hands[14] = hand15;
		assertEquals(hand15.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand16 = new Hand( new CardSet(
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB)));
		hands[15] = hand16;
		assertEquals(hand16.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand17 = new Hand( new CardSet(
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB)));
		hands[16] = hand17;
		assertEquals(hand17.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand18 = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB)));
		hands[17] = hand18;
		assertEquals(hand18.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand19 = new Hand( new CardSet(
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB)));
		hands[18] = hand19;
		assertEquals(hand19.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand20 = new Hand( new CardSet(
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB)));
		hands[19] = hand20;
		assertEquals(hand20.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand21 = new Hand( new CardSet(
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB)));
		hands[20] = hand21;
		assertEquals(hand21.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Hand hand22 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		hands[21] = hand22;
		assertEquals(hand22.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);

		Arrays.sort(hands);
		Arrays.sort(hands,Hand.RankThenValueComparator);
		for (int i = 0; i < hands.length; i++) {
			System.out.println(hands[i].getAbbreviation()+" rank="+Integer.toString(hands[i].getHandRank())+" value="+Integer.toString(hands[i].getValue()));
		}
		
	}

}
