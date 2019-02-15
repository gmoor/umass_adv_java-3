package edu.garyMooradian.advancedJava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StockQuoteApplication {
	
	
	
	public static void main(String[] args) throws Exception {

		/*
		 * Get stock symbol, start date, and end date via command line; i.e.
		 * args[0], args[1], args[2]
		 * Create two Calendar instances/objects; one for start date and one for end date
		 * The Calendar objects will be set to the Dates passed in via args[1] and args[2]
		 * Call getStockService from StockServiceFactory class. It will return BasicStockService
		 * object.
		 */
		
		
		String stockSymbol = args[0];
		
		/*
		 * Getting BasicStockService object from StockServiceFactory
		 */
		StockService basicStockService = new StockServiceFactory().getStockService();
		
		//Create Calendar object for start date
		Calendar calendarStartDate = Calendar.getInstance();
		//parse the start date String to a Date object and set Calendar object with that Date object
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(args[1]));
		
		//Create Calendar object for end date
		Calendar calendarEndDate = Calendar.getInstance();
		//parse the end date String to a Date object and set Calendar object with that Date object
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(args[2]));

		
		//Create a BasicStockService object and call the getQuote method
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory(stockSymbol, calendarStartDate, calendarEndDate);

		//Print the date recorded for each day, for the stock
		for(StockQuote stockQuote : listOfQuotes) {
			System.out.println(stockQuote.getDateRecorded());
		}
	}

}
