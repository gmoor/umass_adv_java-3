package edu.garyMooradian.advancedJava;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BasicStockService implements StockService {
 
	/*
	 * This method receives a stock symbol and date. It uses that information to
	 * obtain a stock price. With that information, it creates (and returns) a
	 * StockQuote object.
	 * 
	 * Because we are not actually going out to some third party to obtain the 
	 * actual price, we will hard code a price.
	 * NOTE: The symbol and date arguments will be provided via JUnit test
	 */
	@Override
	public StockQuote getQuote(String symbol, Date date) {
		/*
		 * returning StockQuote
		 * Note: new BigDecimal(5.00) is the price
		 */
		return new StockQuote(date, new BigDecimal(5.00), symbol);
	}
	
	
	/**
	 * Get a historical list of stock quotes for the provided symbol
	 * @param the stock symbol to search for
	 * @param from the date of the first stock quote
	 * @param until the date of the last stock quote
	 * @return a list of StockQuote instances. One for each day in the range specified.
	 */
	@Override
	public List<StockQuote> getQuoteHistory(String symbol, Calendar from, Calendar until){
		
		Date date = from.getTime();
		Date endDate = until.getTime();
		List<StockQuote> listOfQuotes= new ArrayList<>();
		
		/*
		 * While the date is before or equal to the end date, keep looping.
		 * Note that the date is initially the start date, but is incremented a
		 * day per iteration.
		 * When the date is no longer before or equal to the end date
		 * we terminate the loop
		 */
		while(date.before(endDate) || date.equals(endDate)) {
			listOfQuotes.add(getQuote(symbol,date));//Add StockQuote object to list
			from.add(Calendar.DAY_OF_YEAR, 1);//Add a day to the date
            date = from.getTime();//date is now one day later, and we do another iteration      
		}
		return listOfQuotes;
	}

}
