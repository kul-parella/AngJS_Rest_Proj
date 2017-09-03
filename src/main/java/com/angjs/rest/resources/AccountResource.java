package com.angjs.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.angjs.rest.entity.Account;
import com.angjs.rest.services.AccountService;

@Path("/account")
public class AccountResource {
	
	AccountService service = new AccountService();
	
	@GET
	public String get() {
		return "account";
	}
	
	@Path("/{actId}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Account getActById(@PathParam("actId")int actId) {
		System.out.println(actId);
		Account act = service.getAccountById(actId);
		System.out.println("returning::"+act.getActId()+" "+act.getBalance());
		return act;
	}
	
	@Path("/all")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Account> getAllActs(){
		return service.getAllAccounts();
	}
	
	@Path("/new")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewAct(Account act) {
		String str = service.addNewAct(act);
		System.out.println(str);
		return str;
	}
	

}
