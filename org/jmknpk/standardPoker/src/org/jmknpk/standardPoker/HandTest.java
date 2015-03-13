package org.jmknpk.standardPoker;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class HandTest {

	@Test
	public void test() {

		int z = 0;
		Hand[] hands = new Hand[31];
		
		hands[z] = new Hand();
		assertTrue(hands[z] != null);
		assertEquals(hands[z].getCard(0),new Card(PipName.SEVEN,SuitName.CLUB));
		assertEquals(hands[z].getCard(1),new Card(PipName.FIVE,SuitName.CLUB));
		assertEquals(hands[z].getCard(2),new Card(PipName.FOUR,SuitName.CLUB));
		assertEquals(hands[z].getCard(3),new Card(PipName.THREE,SuitName.CLUB));
		assertEquals(hands[z].getCard(4),new Card(PipName.TWO,SuitName.DIAMOND));
		assertEquals("Seven high with Five, Four, Three, and Two",hands[z].getShowdownDescription());
		assertEquals("High Card 75432",hands[z].getShortShowdownDescription());
		assertEquals("7c5c4c3c2d",hands[z].getAbbreviation());
		z++;
		
		CardSet cardSet = new CardSet(	new Card(PipName.TWO,SuitName.DIAMOND),
									new Card(PipName.THREE,SuitName.CLUB),
									new Card(PipName.FOUR,SuitName.CLUB),
									new Card(PipName.FIVE,SuitName.CLUB),
									new Card(PipName.SEVEN,SuitName.CLUB)
									);
		hands[z] = new Hand(cardSet);
		assertTrue(hands[z] != null);
		assertTrue(hands[z-1].equals(hands[z]));
		assertEquals(hands[z-1],hands[z]);
		assertEquals("Seven high with Five, Four, Three, and Two",hands[z].getShowdownDescription());
		assertEquals("High Card 75432",hands[z].getShortShowdownDescription());
		assertEquals("7c5c4c3c2d",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);
		assertEquals("Five high straight flush",hands[z].getShowdownDescription());
		assertEquals("Straight Flush 5",hands[z].getShortShowdownDescription());
		assertEquals("5c4c3c2cAc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHTFLUSH);
		assertEquals("Ace high straight flush",hands[z].getShowdownDescription());
		assertEquals("Straight Flush A",hands[z].getShortShowdownDescription());
		assertEquals("AcKcQcJcTc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.TEN,SuitName.HEART),
											new Card(PipName.TEN,SuitName.SPADE),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.FOUROFAKIND);
		assertEquals("Four Tens with an Ace",hands[z].getShowdownDescription());
		assertEquals("Four of a Kind T w/A",hands[z].getShortShowdownDescription());
		assertEquals("TsThTdTcAc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.TEN,SuitName.HEART),
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.FULLHOUSE);
		assertEquals("Tens full of Aces",hands[z].getShowdownDescription());
		assertEquals("Full House TA",hands[z].getShortShowdownDescription());
		assertEquals("ThTdTcAdAc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.FLUSH);
		assertEquals("Ace high flush with King, Queen, Ten, and Eight",hands[z].getShowdownDescription());
		assertEquals("Flush AKQT8",hands[z].getShortShowdownDescription());
		assertEquals("AcKcQcTc8c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals("Ace high straight",hands[z].getShowdownDescription());
		assertEquals("Straight A",hands[z].getShortShowdownDescription());
		assertEquals("AcKcQcJdTc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.TEN,SuitName.HEART),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.THREEOFAKIND);
		assertEquals("Three Tens with an Ace and King",hands[z].getShowdownDescription());
		assertEquals("Three of a Kind T w/AK",hands[z].getShortShowdownDescription());
		assertEquals("ThTdTcAcKc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.DIAMOND),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.TWOPAIR);
		assertEquals("Queens over Tens with an Ace",hands[z].getShowdownDescription());
		assertEquals("Two Pair QT w/A",hands[z].getShortShowdownDescription());
		assertEquals("QdQcTdTcAc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.ONEPAIR);
		assertEquals("Pair of Tens with Ace, King, and Queen",hands[z].getShowdownDescription());
		assertEquals("One Pair T w/AKQ",hands[z].getShortShowdownDescription());
		assertEquals("TdTcAcKcQc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.DIAMOND),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals("Ace high with King, Queen, Jack, and Six",hands[z].getShowdownDescription());
		assertEquals("High Card AKQJ6",hands[z].getShortShowdownDescription());
		assertEquals("AcKcQcJd6c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(12,hands[z].calculatePipCombinationsRank());
		assertEquals("Five high straight",hands[z].getShowdownDescription());
		assertEquals("Straight 5",hands[z].getShortShowdownDescription());
		assertEquals("5d4c3c2cAc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TWO,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(0,hands[z].calculatePipCombinationsRank());
		assertEquals("Six high straight",hands[z].getShowdownDescription());
		assertEquals("Straight 6",hands[z].getShortShowdownDescription());
		assertEquals("6d5c4c3c2c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(5,hands[z].calculatePipCombinationsRank());
		assertEquals("Seven high straight",hands[z].getShowdownDescription());
		assertEquals("Straight 7",hands[z].getShortShowdownDescription());
		assertEquals("7d6c5c4c3c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(20,hands[z].calculatePipCombinationsRank());
		assertEquals("Eight high straight",hands[z].getShowdownDescription());
		assertEquals("Straight 8",hands[z].getShortShowdownDescription());
		assertEquals("8d7c6c5c4c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.FIVE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(55,hands[z].calculatePipCombinationsRank());
		assertEquals("Nine high straight",hands[z].getShowdownDescription());
		assertEquals("Straight 9",hands[z].getShortShowdownDescription());
		assertEquals("9d8c7c6c5c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(125,hands[z].calculatePipCombinationsRank());
		assertEquals("Ten high straight",hands[z].getShowdownDescription());
		assertEquals("Straight T",hands[z].getShortShowdownDescription());
		assertEquals("Td9c8c7c6c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(251,hands[z].calculatePipCombinationsRank());
		assertEquals("Jack high straight",hands[z].getShowdownDescription());
		assertEquals("Straight J",hands[z].getShortShowdownDescription());
		assertEquals("JdTc9c8c7c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.EIGHT,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(461,hands[z].calculatePipCombinationsRank());
		assertEquals("Queen high straight",hands[z].getShowdownDescription());
		assertEquals("Straight Q",hands[z].getShortShowdownDescription());
		assertEquals("QdJcTc9c8c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(791,hands[z].calculatePipCombinationsRank());
		assertEquals("King high straight",hands[z].getShowdownDescription());
		assertEquals("Straight K",hands[z].getShortShowdownDescription());
		assertEquals("KdQcJcTc9c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.TEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.ACE,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.STRAIGHT);
		assertEquals(1286,hands[z].calculatePipCombinationsRank());
		assertEquals("Ace high straight",hands[z].getShowdownDescription());
		assertEquals("Straight A",hands[z].getShortShowdownDescription());
		assertEquals("AdKcQcJcTc",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.KING,SuitName.SPADE),
											new Card(PipName.QUEEN,SuitName.SPADE),
											new Card(PipName.JACK,SuitName.SPADE),
											new Card(PipName.NINE,SuitName.SPADE),
											new Card(PipName.SEVEN,SuitName.HEART)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(782,hands[z].calculatePipCombinationsRank());
		assertEquals("King high with Queen, Jack, Nine, and Seven",hands[z].getShowdownDescription());
		assertEquals("High Card KQJ97",hands[z].getShortShowdownDescription());
		assertEquals("KsQsJs9s7h",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.KING,SuitName.CLUB),
											new Card(PipName.QUEEN,SuitName.CLUB),
											new Card(PipName.JACK,SuitName.CLUB),
											new Card(PipName.NINE,SuitName.CLUB),
											new Card(PipName.EIGHT,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(783,hands[z].calculatePipCombinationsRank());
		assertEquals("King high with Queen, Jack, Nine, and Eight",hands[z].getShowdownDescription());
		assertEquals("High Card KQJ98",hands[z].getShortShowdownDescription());
		assertEquals("KcQcJc9c8d",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.KING,SuitName.SPADE),
											new Card(PipName.QUEEN,SuitName.SPADE),
											new Card(PipName.JACK,SuitName.SPADE),
											new Card(PipName.TEN,SuitName.SPADE),
											new Card(PipName.EIGHT,SuitName.HEART)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(790,hands[z].calculatePipCombinationsRank());
		assertEquals("King high with Queen, Jack, Ten, and Eight",hands[z].getShowdownDescription());
		assertEquals("High Card KQJT8",hands[z].getShortShowdownDescription());
		assertEquals("KsQsJsTs8h",hands[z].getAbbreviation());
		z++;
		
		hands[z] = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.CLUB),
											new Card(PipName.SIX,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.DIAMOND)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(793,hands[z].calculatePipCombinationsRank());
		assertEquals("Ace high with Six, Four, Three, and Two",hands[z].getShowdownDescription());
		assertEquals("High Card A6432",hands[z].getShortShowdownDescription());
		assertEquals("Ac6c4c3c2d",hands[z].getAbbreviation());
		z++;
		
		hands[z] = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.SPADE),
											new Card(PipName.KING,SuitName.SPADE),
											new Card(PipName.QUEEN,SuitName.SPADE),
											new Card(PipName.JACK,SuitName.SPADE),
											new Card(PipName.NINE,SuitName.HEART)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.HIGHCARD);
		assertEquals(1285,hands[z].calculatePipCombinationsRank());
		assertEquals("Ace high with King, Queen, Jack, and Nine",hands[z].getShowdownDescription());
		assertEquals("High Card AKQJ9",hands[z].getShortShowdownDescription());
		assertEquals("AsKsQsJs9h",hands[z].getAbbreviation());
		z++;
		
		hands[z] = new Hand( new CardSet(
											new Card(PipName.SIX,SuitName.SPADE),
											new Card(PipName.FIVE,SuitName.SPADE),
											new Card(PipName.FOUR,SuitName.SPADE),
											new Card(PipName.FOUR,SuitName.HEART),
											new Card(PipName.THREE,SuitName.SPADE)));

		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.ONEPAIR);
		assertEquals("Pair of Fours with Six, Five, and Three",hands[z].getShowdownDescription());
		assertEquals("One Pair 4 w/653",hands[z].getShortShowdownDescription());
		assertEquals("4s4h6s5s3s",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.SEVEN,SuitName.CLUB),
											new Card(PipName.FOUR,SuitName.DIAMOND),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.CLUB),
											new Card(PipName.TWO,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.ONEPAIR);
		assertEquals("Pair of Fours with Seven, Three, and Two",hands[z].getShowdownDescription());
		assertEquals("One Pair 4 w/732",hands[z].getShortShowdownDescription());
		assertEquals("4d4c7c3c2c",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.ACE,SuitName.SPADE),
											new Card(PipName.ACE,SuitName.HEART),
											new Card(PipName.TWO,SuitName.SPADE),
											new Card(PipName.TWO,SuitName.HEART),
											new Card(PipName.TWO,SuitName.DIAMOND)));

		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.FULLHOUSE);
		assertEquals("Twos full of Aces",hands[z].getShowdownDescription());
		assertEquals("Full House 2A",hands[z].getShortShowdownDescription());
		assertEquals("2s2h2dAsAh",hands[z].getAbbreviation());
		z++;

		hands[z] = new Hand( new CardSet(
											new Card(PipName.FOUR,SuitName.DIAMOND),
											new Card(PipName.FOUR,SuitName.CLUB),
											new Card(PipName.THREE,SuitName.HEART),
											new Card(PipName.THREE,SuitName.DIAMOND),
											new Card(PipName.THREE,SuitName.CLUB)));
		assertEquals(hands[z].getShowdownCategoryName(),ShowdownCategoryName.FULLHOUSE);
		assertEquals("Threes full of Fours",hands[z].getShowdownDescription());
		assertEquals("Full House 34",hands[z].getShortShowdownDescription());
		assertEquals("3h3d3c4d4c",hands[z].getAbbreviation());
		z++;



		Arrays.sort(hands);
		Arrays.sort(hands,Hand.RankThenValueComparator);
		for (int i = 0; i < hands.length; i++) {
//			System.out.println(hands[i].getAbbreviation()+" rank="+Integer.toString(hands[i].getHandRank())+" value="+Integer.toString(hands[i].getValue()));
		}
		
		
		
	}

}
