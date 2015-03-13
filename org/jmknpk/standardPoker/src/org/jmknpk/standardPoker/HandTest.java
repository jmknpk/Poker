package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class HandTest {

	@Test
	public void test() {
		
		Hand[] hands = new Hand[31];
		
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
											new Card(PipName.FIVE,SuitName.DIAMOND)));
		hands[12] = hand13;
		assertEquals(hand13.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(792,hand13.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand13.calculatePipCombinationsRank())+" "+hand13.getAbbreviation());

		Hand hand14 = new Hand( new CardSet(
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.DIAMOND)));
		hands[13] = hand14;
		assertEquals(hand14.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(0,hand14.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand14.calculatePipCombinationsRank())+" "+hand14.getAbbreviation());

		Hand hand15 = new Hand( new CardSet(
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.DIAMOND)));
		hands[14] = hand15;
		assertEquals(hand15.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(5,hand15.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand15.calculatePipCombinationsRank())+" "+hand15.getAbbreviation());

		Hand hand16 = new Hand( new CardSet(
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.DIAMOND)));
		hands[15] = hand16;
		assertEquals(hand16.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(20,hand16.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand16.calculatePipCombinationsRank())+" "+hand16.getAbbreviation());

		Hand hand17 = new Hand( new CardSet(
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.DIAMOND)));
		hands[16] = hand17;
		assertEquals(hand17.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(55,hand17.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand17.calculatePipCombinationsRank())+" "+hand17.getAbbreviation());

		Hand hand18 = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND)));
		hands[17] = hand18;
		assertEquals(hand18.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(125,hand18.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand18.calculatePipCombinationsRank())+" "+hand18.getAbbreviation());

		Hand hand19 = new Hand( new CardSet(
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.DIAMOND)));
		hands[18] = hand19;
		assertEquals(hand19.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(251,hand19.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand19.calculatePipCombinationsRank())+" "+hand19.getAbbreviation());

		Hand hand20 = new Hand( new CardSet(
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.DIAMOND)));
		hands[19] = hand20;
		assertEquals(hand20.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(461,hand20.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand20.calculatePipCombinationsRank())+" "+hand20.getAbbreviation());

		Hand hand21 = new Hand( new CardSet(
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.DIAMOND)));
		hands[20] = hand21;
		assertEquals(hand21.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(791,hand21.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand21.calculatePipCombinationsRank())+" "+hand21.getAbbreviation());

		Hand hand22 = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.DIAMOND)));
		hands[21] = hand22;
		assertEquals(hand22.getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(1286,hand22.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand22.calculatePipCombinationsRank())+" "+hand22.getAbbreviation());

		Hand hand23 = new Hand( new CardSet(
											new Card(PipName.KING,SuitName.SPADE),
											new Card(PipName.QUEEN,SuitName.SPADE),
											new Card(PipName.JACK,SuitName.SPADE),
											new Card(PipName.NINE,SuitName.SPADE),
											new Card(PipName.SEVEN,SuitName.HEART)));
		hands[22] = hand23;
		assertEquals(hand23.getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(782,hand23.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand23.calculatePipCombinationsRank())+" "+hand23.getAbbreviation());

		Hand hand24 = new Hand( new CardSet(
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.DIAMOND)));
		hands[23] = hand24;
		assertEquals(hand24.getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(783,hand24.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand24.calculatePipCombinationsRank())+" "+hand24.getAbbreviation());

		Hand hand25 = new Hand( new CardSet(
											new Card(PipName.KING,SuitName.SPADE),
											new Card(PipName.QUEEN,SuitName.SPADE),
											new Card(PipName.JACK,SuitName.SPADE),
											new Card(PipName.TEN,SuitName.SPADE),
											new Card(PipName.EIGHT,SuitName.HEART)));
		hands[24] = hand25;
		assertEquals(hand25.getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(790,hand25.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand25.calculatePipCombinationsRank())+" "+Integer.toString(hand25.calculateNonStraightPipCombinationsRank())+" "+hand25.getAbbreviation());
		
		Hand hand26 = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.DIAMOND)));
		hands[25] = hand26;
		assertEquals(hand26.getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(793,hand26.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand26.calculatePipCombinationsRank())+" "+Integer.toString(hand26.calculateNonStraightPipCombinationsRank())+" "+hand26.getAbbreviation());
		
		Hand hand27 = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.SPADE),
											new Card(PipName.KING,SuitName.SPADE),
											new Card(PipName.QUEEN,SuitName.SPADE),
											new Card(PipName.JACK,SuitName.SPADE),
											new Card(PipName.NINE,SuitName.HEART)));
		hands[26] = hand27;
		assertEquals(hand27.getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(1285,hand27.calculatePipCombinationsRank());
//System.out.println(Integer.toString(hand27.calculatePipCombinationsRank())+" "+Integer.toString(hand27.calculateNonStraightPipCombinationsRank())+" "+hand27.getAbbreviation());
		
		Hand hand28 = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.SPADE),
											new Card(PipName.FIVE,SuitName.SPADE),
											new Card(PipName.FOUR,SuitName.SPADE),
											new Card(PipName.FOUR,SuitName.HEART),
											new Card(PipName.THREE,SuitName.SPADE)));

		hands[27] = hand28;
		assertEquals(hand28.getShowdownCategoryName(),ShowdownCategoryName.ONEPAIR);
//		System.out.println(Integer.toString(hand28.calculateOnePairKickerRank())+" "+Integer.toString(hand28.calculateOnePairRank())+" "+hand28.getAbbreviation());

		Hand hand29 = new Hand( new CardSet(
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.DIAMOND),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.CLUB)));
		hands[28] = hand29;
		assertEquals(hand29.getShowdownCategoryName(),ShowdownCategoryName.ONEPAIR);
//		System.out.println(Integer.toString(hand29.calculateOnePairKickerRank())+" "+Integer.toString(hand29.calculateOnePairRank())+" "+hand29.getAbbreviation());

		Hand hand30 = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.SPADE),
											new Card(PipName.ACE,SuitName.HEART),
											new Card(PipName.TWO,SuitName.SPADE),
											new Card(PipName.TWO,SuitName.HEART),
											new Card(PipName.TWO,SuitName.DIAMOND)));

		hands[29] = hand30;
		assertEquals(hand30.getShowdownCategoryName(),ShowdownCategoryName.FULLHOUSE);
//		System.out.println(Integer.toString(hand30.calculateOnePairKickerRank())+" "+Integer.toString(hand30.calculateOnePairRank())+" "+hand30.getAbbreviation());

		Hand hand31 = new Hand( new CardSet(
											new Card(PipName.FOUR,SuitName.DIAMOND),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.HEART),
											new Card(PipName.THREE,SuitName.DIAMOND),
											new Card(PipName.THREE,SuitName.CLUB)));
		hands[30] = hand31;
		assertEquals(hand31.getShowdownCategoryName(),ShowdownCategoryName.FULLHOUSE);
//		System.out.println(Integer.toString(hand31.calculateOnePairKickerRank())+" "+Integer.toString(hand31.calculateOnePairRank())+" "+hand31.getAbbreviation());



		Arrays.sort(hands);
		Arrays.sort(hands,Hand.RankThenValueComparator);
		for (int i = 0; i < hands.length; i++) {
//			System.out.println(hands[i].getAbbreviation()+" rank="+Integer.toString(hands[i].getHandRank())+" value="+Integer.toString(hands[i].getValue()));
		}
		
		
		
	}

}
