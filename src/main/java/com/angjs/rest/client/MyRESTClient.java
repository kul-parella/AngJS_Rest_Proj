package com.angjs.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.angjs.rest.entity.Trade;
import com.angjs.rest.exception.TradeExceptionMapper.ErrorObj;



public class MyRESTClient {

	public static void main(String[] args) {
		//Using JAX-RS Client API
		Client client = ClientBuilder.newClient();
		
		//get all trades
		WebTarget target = client.target("http://localhost:8080/AngJS_Rest_Proj/webapi/trade/all");
		
		String response = target
							.request()
							.accept("application/xml") //setting Accept header
							.get(String.class); //using GET method
		System.out.println(response);
		
		//getting the response as a Collection object
		List<Trade> trades = target
							.request()
							.accept("application/xml") //setting Accept header
							.get(new GenericType<List<Trade>>() { }); //using GET method
		
		for(Trade trade : trades)
			System.out.println(trade.getTradeId()+","+trade.getAmount()+","+trade.getDate()+","+trade.getRegion());
	
		//get trade by id
		target = client.target("http://localhost:8080/AngJS_Rest_Proj/webapi/trade/{tradeId}");
		response = target
					.resolveTemplate("tradeId", 12345) //substituting the pathparam {tradeId}
					.request()
					.get(String.class);
		System.out.println(response);
		
		//getting the response as an object
		Trade trade = target
					.resolveTemplate("tradeId", 12345) //substituting the pathparam {tradeId}
					.request()
					.get(Trade.class);
		System.out.println(trade.getTradeId()+","+trade.getAmount()+","+trade.getDate()+","+trade.getRegion());

		//handling an error response
		Response responseObj = target
								.resolveTemplate("tradeId", 12345) //substituting the pathparam {tradeId}
								.request()
								.get(Response.class);
		if(responseObj.getStatus() == 400) {
			ErrorObj errorObj = responseObj.readEntity(ErrorObj.class);
			System.out.println(errorObj.getErrorCode() + " , " + errorObj.getErrorMessage());
		}
		else {
			Trade tradeObj = responseObj.readEntity(Trade.class);
			System.out.println(tradeObj.getTradeId()+","+tradeObj.getAmount()+","+tradeObj.getDate()+","+tradeObj.getRegion());
		}
		
		//testing post method
		Trade newTrade = new Trade();
		newTrade.setTradeId(78901);
		newTrade.setAmount(9875.55);
		newTrade.setRegion("LN");
		newTrade.setDate("18-5-2017");
		
		target = client.target("http://localhost:8080/AngJS_Rest_Proj/webapi/trade/new");
		response = 
				target
				.request()
				.post(Entity.entity(newTrade, "application/json"), String.class);
		System.out.println(response);
		
		client.close(); //should be in finally
	}
}
