package com.angjs.rest.repos;

import java.util.ArrayList;
import java.util.List;

import com.angjs.rest.entity.Trade;
import com.angjs.rest.exception.TradeException;



public class TradeDao {

	public Trade fetchById(int tradeId) {
		if(tradeId == 11111)
			throw new TradeException("No record for tradeId 11111 found in the db!");
		
		Trade trade = new Trade();
		trade.setTradeId(tradeId);
		trade.setDate("18-05-2017");
		trade.setAmount(4500);
		trade.setRegion("NY");
		
		return trade;
	}
	
	public List<Trade> fetchByRegion(String region) {
		List<Trade> trades = new ArrayList<>();

		Trade trade = new Trade();
		trade.setTradeId(12345);
		trade.setDate("18-05-2017");
		trade.setAmount(4500);
		trade.setRegion(region);
		trades.add(trade);
		
		trade = new Trade();
		trade.setTradeId(67890);
		trade.setDate("16-05-2017");
		trade.setAmount(7500);
		trade.setRegion(region);
		trades.add(trade);

		trade = new Trade();
		trade.setTradeId(11223);
		trade.setDate("16-05-2017");
		trade.setAmount(8500);
		trade.setRegion(region);
		trades.add(trade);

		return trades;
	}
	
	public List<Trade> fetchAll() {
		List<Trade> trades = new ArrayList<>();

		Trade trade = new Trade();
		trade.setTradeId(12345);
		trade.setDate("18-05-2017");
		trade.setAmount(4500);
		trade.setRegion("NY");
		trades.add(trade);
		
		trade = new Trade();
		trade.setTradeId(67890);
		trade.setDate("16-05-2017");
		trade.setAmount(7500);
		trade.setRegion("NY");
		trades.add(trade);

		trade = new Trade();
		trade.setTradeId(11223);
		trade.setDate("16-05-2017");
		trade.setAmount(8500);
		trade.setRegion("TK");
		trades.add(trade);

		return trades;
	}

	public void add(Trade trade) {
		
	}
}
