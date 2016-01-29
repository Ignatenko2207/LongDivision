package com.smarterama;

import org.junit.Assert;
import org.junit.Test;

public class LongDivisionTest {
	
	@Test
	public void testShowRightDivision() {
		String expectedString = ShowLongDivision.longDivisionAsString(12345, 7);
		Assert.assertEquals("12345|7\n 7   +----\n _   |1763\n 53\n 49\n __\n  44\n  42\n  __\n   25\n   21\n   __\n    4\n", expectedString);
	}
	
	@Test
	public void testShowRightDivisionIfOneCycle() {
		String expectedString = ShowLongDivision.longDivisionAsString(12, 7);
		Assert.assertEquals("12|7\n 7+-\n _|1\n 5", expectedString);
	}
	
	@Test
	public void testDividerIsZero() {
		String expectedString = ShowLongDivision.longDivisionAsString(12345, 0);
		Assert.assertEquals("Divider cannot be \"0\"!", expectedString);
	}
	
	@Test
	public void testDividendLessDivider() {
		String expectedString = ShowLongDivision.longDivisionAsString(12, 20);
		Assert.assertEquals("Divider is larger than dividend. Input other numbers!", expectedString);
	}

	@Test
	public void testDividendOrDividerLessZerro() {
		String expectedString = ShowLongDivision.longDivisionAsString(-8, 5);
		Assert.assertEquals("Dividend or divider less \"0\"!  Input other numbers!", expectedString);
	}
	
	// method try convert number into Integer, but the result of such convertation is a negative number
	@Test
	public void testDividendOrDividerLargerInteger() {
		String expectedString = ShowLongDivision.longDivisionAsString((Integer.MAX_VALUE + 1), 5);
		Assert.assertEquals("Dividend or divider less \"0\"!  Input other numbers!", expectedString);
	}
	
	@Test
	public void testRightDivision() {
		String expectedString = ShowLongDivision.longDivisionAsString(1234567, 77);
		Assert.assertTrue(expectedString.contains("16033"));
	}
	
	@Test
	public void testShowRightMinuses() {
		String expectedString = ShowLongDivision.longDivisionAsString(123456, 77777);
		Assert.assertTrue(expectedString.contains("-----"));
	}
	
	@Test
	public void testSpaces() {
		String expectedString = ShowLongDivision.setSpaceInString(5);
		Assert.assertEquals("     ", expectedString);
	}
	
	@Test
	public void testUnderline() {
		String expectedString = ShowLongDivision.setUnderlineInString(5);
		Assert.assertEquals("_____", expectedString);
	}
	
}

	