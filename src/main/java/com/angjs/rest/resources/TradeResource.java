package com.angjs.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.angjs.rest.entity.Trade;
import com.angjs.rest.repos.TradeDao;


@Path("/trade")
public class TradeResource {

	//this example depends upon Accept HTTP header
	//to generate json or xml accordingly
	@Path("/all")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Trade> getAll() {
		TradeDao dao = new TradeDao(); //should use DI instead
		return dao.fetchAll();
	}
	
	//passing parameter in the URL
	@Path("/{tradeId}")
	@GET
	@Produces({"application/xml","application/json"})
	public Trade get(@PathParam("tradeId") int tradeId) {
		TradeDao dao = new TradeDao();
		return dao.fetchById(tradeId);
	}
	
	@Path("/new")
	@POST
	@Consumes({"application/xml","application/json"})
	@Produces("text/plain")
	public String add(Trade trade) {
		TradeDao dao = new TradeDao();
		dao.add(trade);
		
		return "Trade record created successfully!";
	}
}
