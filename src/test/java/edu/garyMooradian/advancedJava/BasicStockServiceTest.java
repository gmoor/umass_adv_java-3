package edu.garyMooradian.advancedJava;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class BasicStockServiceTest {
	
	/*
	 * Tests the getQuote method in BasicStockService
	 */
	@Test
	public void testGetQuote() {
		//Create a Date
		Date date = new Date(2019,05,05);
		//Pass stock symbol and date.
		StockQuote quote = new BasicStockService().getQuote("OLED", date);
		/*
		 * Provide same price as we hard coded in BasicStockService's getQuote method
		 */
		BigDecimal price = new BigDecimal(5.00);
		
		/*
		 * Verify that dateRecorded is correct for the StockQuote object
		 * Note: Just trying assertTrue for the hell of it; i.e. instead of assertEquals
		 */
		assertTrue("The dateRecorded is not correct", date == quote.getDateRecorded());
		
		/*
		 * Verify that the stockPrice is correct for the StockQuote object
		 */
		assertTrue("The stockPrice is not correct", price.equals(quote.getStockPrice()));
		
		/*
		 * Verify the stockSymbol is correct for the stockQuote object
		 */
		assertEquals("The stockSymbol is not correct", "OLED", quote.getStockSymbol());		
	}
	

	/*
	 * Testing the GetQuoteHistory method; i.e.
	 * Testing that the List is populated with two StockQuote objects
	 * We test for two quotes, not just one, because of the OR condition
	 * in the while loop; i.e. while(date.before(endDate) || date.equals(endDate))
	 */
	@Test
	public void testGetQuoteHistoryPositive() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/21/2019"));
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate);
	
		/*
		 * Testing the StockQuote for only the stock symbol is good enough since
		 * we just want to make sure the StockQuote object was created. How well that
		 * object is created is tested elsewhere; i.e. tested when we tested the getQuote method
		 */
		assertEquals("The first stock quote in the list does not contain the proper stock symbol", "OLED", listOfQuotes.get(0).getStockSymbol());
		assertEquals("The second stock quote in the list does not contain the proper stock symbol", "OLED", listOfQuotes.get(1).getStockSymbol());
	}
	
	/*
	 * This test passes an end date that is earlier than the start date.
	 * This should result in no StockQuotes being created, thus the List returned
	 * should be empty. We test that the List is indeed empty
	 */
	@Test
	public void testGetQuoteHistoryNegative() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/19/2019"));
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate);
	
		/*
		 * Verifying that the List of StockQutoes is empty, as expected
		 */
		assertTrue("No StockQuote objects should have been created; i.e. List of StockQuotes should be empty but was not", listOfQuotes.isEmpty());
	}
	
	
}
