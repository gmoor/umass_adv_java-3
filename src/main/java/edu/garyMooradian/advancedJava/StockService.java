package edu.garyMooradian.advancedJava;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface StockService {
	
	/*
	 * returns a stock quote, based on a stockSymbol and Date
	 */
	public StockQuote getQuote(String symbol, Date date);
	
	
	/**
	 * Get a historical list of stock quotes for the provide symbol
	 * @param the stock symbol to search for
	 * @param from the date of the first stock quote
	 * @param until the date of the last stock quote
	 * @return a list of StockQuote instances. One for each day in the range specified.*
	 */
	public List<StockQuote> getQuoteHistory(String symbol, Calendar from, Calendar until);
	

}
