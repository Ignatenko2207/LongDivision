package com.smarterama;

public class ShowLongDivision {

	public static void main(String[] args) {
		
		int dividend = 12345;
		int divider = 7;
		String outputString = longDivisionAsString(dividend, divider);
		System.out.println(outputString);
	}

	public static String longDivisionAsString(int dividend, int divider) {
		String outputString = "";
		if (divider == 0) {
			outputString = "Divider cannot be \"0\"!";
			return outputString;
		}
		if (divider < 0 || dividend < 0) {
			outputString = "Dividend or divider less \"0\"!  Input other numbers!";
			return outputString;
		}
		if (divider > Integer.MAX_VALUE || dividend > Integer.MAX_VALUE) {
			outputString = "Dividend or divider cannot be larger than 2 147 483 647.  Input other numbers!";
			return outputString;
		}
		if (divider > dividend) {
			outputString = "Divider is larger than dividend. Input other numbers!";
			return outputString;
		}
		int finalResult = getDivisionResult(dividend, divider);
		outputString += dividend + "|" + divider + "\n";
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
			quantityOfCycles++;
			partOfDividendAsString = partOfDividendAsString + Integer.toString(Character.getNumericValue(numbersOfDividend[i]));
			tempDividend = Integer.parseInt(partOfDividendAsString);
			if(tempDividend<divider){
				continue;				
			}
			tempResult = divider*getDivisionResult(tempDividend, divider);
			char [] symbolsInRes = Integer.toString(tempResult).toCharArray();
			spacesAfter = numbersOfDividend.length - i - 1;
			spacesBefore = numbersOfDividend.length - spacesAfter - symbolsInRes.length;
			//it shows correct picture in first row after "dividend|divider"
			if(firstRow){
				
				outputString += setSpaceInString(spacesBefore) + tempResult + setSpaceInString(spacesAfter);
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
				outputString += "+" + minuses + "\n";
				outputString += setSpaceInString(spacesBefore) + setUnderlineInString(symbolsInRes.length) + setSpaceInString(spacesAfter) + "|" + finalResult + "\n";
				secondRow = true;
			}
			// it skips existing first row
			if(secondRow){
				secondRow = false;
				partOfDividendAsString = String.valueOf(getRemainOfDivision(tempDividend, divider));
				if (getRemainOfDivision(tempDividend, divider) == 0){
					partOfDividendAsString = "";
				}
				firstRow = false;
				continue;
			}
				
			outputString += setSpaceInString(spacesBefore) + partOfDividendAsString + "\n";
			outputString += setSpaceInString(spacesBefore) + tempResult + "\n";
			outputString += setSpaceInString(spacesBefore) + setUnderlineInString(symbolsInRes.length) + "\n";
			
			partOfDividendAsString = String.valueOf(getRemainOfDivision(tempDividend, divider));
			// it remooves "0" in the begining of strings if remain of division equals 0.
			if (getRemainOfDivision(tempDividend, divider) == 0){
				partOfDividendAsString = "";
			}
			firstRow = false;
			// it shows remain in the end (if it isn't 0)
			if(i == (numbersOfDividend.length-1)){
				char [] numbersOfRemainOfDivision = Integer.toString(getRemainOfDivision(dividend, divider)).toCharArray();	
				spacesBefore = numbersOfDividend.length - numbersOfRemainOfDivision.length;
				outputString += setSpaceInString(spacesBefore) + getRemainOfDivision(dividend, divider) + "\n";
			}
		}
		//it shows correct picture if there is only one cycle
		if(quantityOfCycles == 2){
			char [] numbersOfRemainOfDivision = Integer.toString(getRemainOfDivision(dividend, divider)).toCharArray();
			spacesBefore = numbersOfDividend.length - numbersOfRemainOfDivision.length;
			outputString += setSpaceInString(spacesBefore) + getRemainOfDivision(dividend, divider);
		}
		return outputString;
	}

	public static int getDivisionResult(int dividend, int divider) {
		int result = (Integer)dividend/divider;
		return result;
	}
	
	public static int getRemainOfDivision(int dividend, int divider) {
		int result = (Integer)dividend%divider;
		return result;
	}

	public static String setSpaceInString(int quantityOfSpaces) {
		String spaces = "";
		for (int i = 0; i < quantityOfSpaces; i++){
			spaces += " ";
		}
		return spaces;
	}
	
	// as I remember, my teacher in school said, that underline shouldn't be larger, than quantity of numbers upper it.
	// so I made such method to output different underlines, think, it looks better
	
	public static String setUnderlineInString(int quantityOfUnderlines) {
		String underline = "";
		for (int k = 0; k < quantityOfUnderlines; k++){
			underline += "_";
		}
		return underline;
	}
}