package com.twentysix20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

	private static final String SWAMP = "Swamp";
	private static final String RATS = "Rats";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int numberOfSwamps = 35;
		for (int i = 0; i < numberOfSwamps; i++)
			list.add(SWAMP);
		for (int i = numberOfSwamps; i < 99; i++)
			list.add(RATS);
		System.out.println(list.size());
		Collections.shuffle(list);
		int cardsDrawn = 0;
		int turn = 0;
		int ratsInHand = 0;
		int ratsOnField = 0;
		int landInHand = 0;
		int landOnField = 0;
		int firstRat = 0;
		for (String card : list) {
			if (card.equals(RATS))
				ratsInHand++;
			else if (card.equals(SWAMP))
				landInHand++;
			cardsDrawn++;
			if (cardsDrawn <= 7) {
				System.out.println("Card #"+cardsDrawn+": "+card);
				continue;
			}
			turn++;
			System.out.println("\nTurn #: "+turn);
			System.out.println("Card #"+cardsDrawn+": "+card);
			if (landInHand > 0) {
				landInHand--;
				landOnField++;
				System.out.println("Played a land: "+landOnField+" on field.");
			}
			if (landOnField >= 3) {
				int ratsToPlay = landOnField / 3;
				if (ratsInHand < ratsToPlay)
					ratsToPlay = ratsInHand;
				if (ratsToPlay > 0) {
					if (firstRat == 0)
						firstRat = turn;
					ratsInHand -= ratsToPlay;
					ratsOnField += ratsToPlay;
					int ratSize = ratsOnField + 1;
					System.out.println("Played a Rat: "+ratsOnField+" "+ratSize+"/"+ratSize+" rats on field. ("+(ratsOnField*ratSize)+" of power)");
				}
			}
			System.out.println("Hand: "+ratsInHand+" Rats; "+landInHand+" Swamps");
			if (landOnField == 8) {
				System.out.println("DONE: 8th land: "+turn);
				break;
			}
//			if (ratsInHand + landInHand == 0) {
//				System.out.println("DONE: first rat: "+firstRat+"; empty hand: "+turn);
//				break;
//			}
		}
	}

}
