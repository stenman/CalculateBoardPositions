package com.example.calculateboardpositions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Author: Roger Stenman
// This program calculates the number of position sets that x pieces can take on a y*y board
// The program is in need of both refactoring and probably debugging and correct functionality is not verified

public class Start {

	public static void main(String[] args) {
		calculateBoardPositions();
	}
	
	public static void calculateBoardPositions() {

		int numberOfListItemsAdded = 0;
		
		int tempPos;
		int numberOfPieces = 3;
		int numberOfFields = 9;
		int randomizations = 100;

		ArrayList<byte[]> list = new ArrayList<byte[]>();

		for (int i = 0; i < randomizations; i++) {

			// Create one position set
			byte[] positionSet = createPositionSet(numberOfPieces, numberOfFields);


			// Make sure that the pattern of the position set is unique relative
			// to the sets in the list (ex. [0,1] should be equal to [1,0])
			for (int j = 0; j < list.size(); j++) {
				int warning = 0;
				for (int k = 0; k < positionSet.length; k++) {
					for (int k2 = 0; k2 < list.get(j).length; k2++) {
						tempPos = j;
						if (list.get(tempPos)[k2] == positionSet[k]) {
							warning++;
							if(warning == positionSet.length){
								positionSet = createPositionSet(numberOfPieces, numberOfFields);
								j = -1;
								break;
							}
						}
					}
				}
			}
			
			//TODO: Clean up! Find out a way of knowing when no more list items can be added and stop the iterations!

			System.out.println("Added set item "+(numberOfListItemsAdded+1)+" to the list!");
			list.add(positionSet);
			System.out.println(Arrays.toString(list.get(numberOfListItemsAdded)));
			numberOfListItemsAdded++;
		}
	}

	private static byte[] createPositionSet(int numberOfPieces, int numberOfFields) {
		int tempPos;
		boolean restart = false;
		byte[] positionSet = new byte[numberOfPieces];

		// Create positions for one set
		for (int i = 0; i < positionSet.length; i++) {
			positionSet[i] = createRandomByte(numberOfFields);
		}
		// Make sure that all positions are unique inside this set
		for (int j = 0; j < positionSet.length; j++) {
			for (int k = j + 1; k < positionSet.length; k++) {
				tempPos = j;
				while (positionSet[tempPos] == positionSet[k]) {
					positionSet[k] = createRandomByte(numberOfFields);
					restart = true;
				}
				if(restart){
					j = -1;
					restart = false;
					break;
				}
			}
		}

		return positionSet;
	}

	private static byte createRandomByte(int numberOfFields) {
		Random rand = new Random();
		return (byte) rand.nextInt(numberOfFields);
	}

}
