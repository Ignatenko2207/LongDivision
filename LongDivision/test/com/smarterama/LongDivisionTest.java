package com.smarterama;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class LongDivisionTest{

	public static void showDivision(int dividend, int divider) {
		if (divider == 0) {
			System.out.print("Divider cannot be \"0\"!");
			return;
		}
		if (dividend > Integer.MAX_VALUE || divider > Integer.MAX_VALUE) {
			System.out.print("Dividend or divider cannot be larger than 2 147 483 647.  Input other numbers!");
			return;
		}
		if (divider < 0 || dividend < 0) {
			System.out.print("Dividend or divider less \"0\"!  Input other numbers!");
			return;
		}
		if (divider > dividend) {
			System.out.print("Divider is larger than dividend. Input other numbers!");
			return;
		}
		int finalResult = getDivisionResult(dividend, divider);
		System.out.println(dividend + "|" + divider);
		char [] numbersOfDividend = Integer.toString(dividend).toCharArray();
		char [] numbersOfFinalRes = Integer.toString(finalResult).toCharArray();
		String partOfDividendAsString = "";
		int tempDividend;
		int tempResult;
		int spacesBefore;
		int spacesAfter;
		int quantityOfCycles = 0;
		boolean firstRow = true;
		boolean secondRow = false;
		for (int i = 0; i < numbersOfDividend.length; i++) {
			quantityOfCycles += 1;
			partOfDividendAsString = partOfDividendAsString + Integer.toString(Character.getNumericValue(numbersOfDividend[i]));
			tempDividend = Integer.parseInt(partOfDividendAsString);
			if(tempDividend<divider){
				continue;				
			}
			tempResult = divider*getDivisionResult(tempDividend, divider);
			char [] symbolsInRes = Integer.toString(tempResult).toCharArray();
			spacesAfter = numbersOfDividend.length - i - 1;
			spacesBefore = numbersOfDividend.length - spacesAfter - symbolsInRes.length;
			if(firstRow){
				System.out.print(setSpaceInString(spacesBefore) + tempResult + setSpaceInString(spacesAfter));
				String minuses = "";
				int minusesLength;
				char [] symbolsInDvivder = Integer.toString(divider).toCharArray();
				if (numbersOfFinalRes.length > symbolsInDvivder.length) {
					minusesLength = numbersOfFinalRes.length;
				} 
				else{
					minusesLength = symbolsInDvivder.length;
				}
				for (int j = 0; j < minusesLength; j++){
					minuses += "-";
				}
				System.out.println("+" + minuses);
				System.out.println(setSpaceInString(spacesBefore) + setUnderlineInString(symbolsInRes.length) + setSpaceInString(spacesAfter) + "|" + finalResult);
				secondRow = true;
			}
			if(secondRow){
				secondRow = false;
				partOfDividendAsString = String.valueOf(getRemainOfDivision(tempDividend, divider));
				if (getRemainOfDivision(tempDividend, divider) == 0){
					partOfDividendAsString = "";
				}
				firstRow = false;
				continue;
			}
				
			System.out.println(setSpaceInString(spacesBefore) + partOfDividendAsString);
			System.out.println(setSpaceInString(spacesBefore) + tempResult);
			System.out.println(setSpaceInString(spacesBefore) + setUnderlineInString(symbolsInRes.length));
			
			partOfDividendAsString = String.valueOf(getRemainOfDivision(tempDividend, divider));
			if (getRemainOfDivision(tempDividend, divider) == 0){
				partOfDividendAsString = "";
			}
			firstRow = false;
			if(i == (numbersOfDividend.length-1)){
				char [] numbersOfRemainOfDivision = Integer.toString(getRemainOfDivision(dividend, divider)).toCharArray();	
				spacesBefore = numbersOfDividend.length - numbersOfRemainOfDivision.length;
				System.out.println(setSpaceInString(spacesBefore) + getRemainOfDivision(dividend, divider));
			}
		}
		if(quantityOfCycles == 1){
			System.out.print(getRemainOfDivision(dividend, divider));
		}
	}

	private static int getDivisionResult(int dividend, int divider) {
		int result = (Integer)dividend/divider;
		return result;
	}
	
	private static int getRemainOfDivision(int dividend, int divider) {
		int result = (Integer)dividend%divider;
		return result;
	}

	private static String setSpaceInString(int quantityOfSpaces) {
		String spaces = "";
		for (int i = 0; i < quantityOfSpaces; i++){
			spaces += " ";
		}
		return spaces;
	}
	
	private static String setUnderlineInString(int quantityOfUnderlines) {
		String underline = "";
		for (int k = 0; k < quantityOfUnderlines; k++){
			underline += "_";
		}
		return underline;
	}
	
	//TestCase
	private final ByteArrayOutputStream outString = new ByteArrayOutputStream();
		
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outString));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void testDividerIsZero() {
		LongDivisionTest.showDivision(12345, 0);
		Assert.assertEquals("Divider cannot be \"0\"!", outString.toString());
	}
	
	@Test
	public void testDividerLessDivider() {
		LongDivisionTest.showDivision(12, 20);
		Assert.assertEquals("Divider is larger than dividend. Input other numbers!", outString.toString());
	}

	@Test
	public void testDividendOrDividerLessZerro() {
		LongDivisionTest.showDivision(-8, 5);
		Assert.assertEquals("Dividend or divider less \"0\"!  Input other numbers!", outString.toString());
	}
	
	// method try convert number into Integer, but the result of such convertation is a negative number
	@Test
	public void testDividendOrDividerLargerInteger() {
		LongDivisionTest.showDivision((Integer.MAX_VALUE + 1), 5);
		Assert.assertEquals("Dividend or divider less \"0\"!  Input other numbers!", outString.toString());
	}
	
	@Test
	public void testRightDivision() {
		LongDivisionTest.showDivision(12345, 7);
		String expectedString = outString.toString();
		Assert.assertTrue(expectedString.contains("1763"));
	}
	
	@Test
	public void testShowRightMinuses() {
		LongDivisionTest.showDivision(1234567, 77777);
		String expectedString = outString.toString();
		Assert.assertTrue(expectedString.contains("-----"));
	}
	
	@Test
	public void testSpaces() {
		String expectedString = setSpaceInString(5);
		Assert.assertEquals("     ", expectedString);
	}
	
	@Test
	public void testUnderline() {
		String expectedString = setUnderlineInString(5);
		Assert.assertEquals("_____", expectedString);
	}
	
}
