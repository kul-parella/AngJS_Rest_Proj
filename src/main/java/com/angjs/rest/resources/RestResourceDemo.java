package com.angjs.rest.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.angjs.rest.entity.Trade;
import com.angjs.rest.repos.TradeDao;



@Path("/example")
public class RestResourceDemo {

	//http://localhost:8080/JAX-RS-Proj/1.0/example/01/1001/kuladeep/10000
	@GET
	@Path("/01/{empno}/{name}/{salary}")
	@Produces(MediaType.TEXT_PLAIN)
	public String example01(
			@PathParam("empno") int empno,
			@PathParam("name") String name,
			@PathParam("salary") double salary) {
		
		System.out.println(empno + " " + name + " " + salary);
		
		return "Response from example01";
	}
	
	//http://localhost:8080/JAX-RS-Proj/1.0/example/02?empno=1001&name=kuladeep&salary=2000
	@GET
	@Path("/02")
	@Produces(MediaType.TEXT_PLAIN)
	public String example02(
			@QueryParam("empno") int empno,
			@QueryParam("name") String name,
			@QueryParam("salary") double salary) {
		
		System.out.println(empno + " " + name + " " + salary);
		
		return "Response from example02";
	}

	//http://localhost:8080/JAX-RS-Proj/1.0/example/03;empno=1001;name=kuladeep;salary=2000
	@GET
	@Path("/03")
	@Produces(MediaType.TEXT_PLAIN)
	public String example03(
			@MatrixParam("empno") int empno,
			@MatrixParam("name") String name,
			@MatrixParam("salary") double salary) {
		
		System.out.println(empno + " " + name + " " + salary);
		
		return "Response from example03";
	}

	//http://localhost:8080/JAX-RS-Proj/1.0/example/04/1001/kuladeep
	@GET
	@Path("/04/{empno : \\d+}/{name : [a-zA-Z]*}")
	@Produces(MediaType.TEXT_PLAIN)
	public String example04(
			@PathParam("empno") int empno,
			@PathParam("name") String name) {
		
		System.out.println(empno + " " + name);
		
		return "Response from example04";
	}

	//suppose in the URL we need to send bunch of empnos, how do we do it?
	//http://localhost:8080/JAX-RS-Proj/1.0/example/05/1001/1002/1003/1004/1005/action/sendEmail
	//http://localhost:8080/JAX-RS-Proj/1.0/example/05/1001/1002;sendAsSMS=true/1003/1004;sendAsSMS=true/1005/action/sendEmail
	@GET
	@Path("/05/{empnos : .+}/action/{someAction}")
	@Produces(MediaType.TEXT_PLAIN)
	public String example05(
			//@PathParam("empnos") String empnos, // not the right way
			//@PathParam("empnos") List<String> empnos, //not the right way
			@PathParam("empnos") List<PathSegment> empnos, //the right way
			@PathParam("someAction") String someAction) {
		
		for(PathSegment empno : empnos) {
			System.out.println(empno.getPath());
			System.out.println(empno.getMatrixParameters());
		}
		//System.out.println(empnos);
		System.out.println(someAction);
		
		return "Response from example05";
	}
	
	//http://localhost:8080/JAX-RS-Proj/1.0/example/06/abc/xyz?x=1&y=20
	@GET
	@Path("/06/{param1}/{param2}")
	@Produces(MediaType.TEXT_PLAIN)
	public String example06(@Context UriInfo uriInfo) {
		System.out.println(uriInfo.getPath());
		System.out.println(uriInfo.getAbsolutePath());
		System.out.println(uriInfo.getBaseUri());
		System.out.println(uriInfo.getRequestUri());
		
		return "Response from example06";
	}

	//http://localhost:8080/JAX-RS-Proj/1.0/example/07
	@GET
	@Path("/07")
	public Response example07() {
		return Response
				.ok() //HTTP 200 OK
				.header("token", "sdfdsfdsf@#$@#$23sdfsdf") //any inbuilt or user defined header
				.cookie(new NewCookie("username", "kuladeep"))
				.entity("Response from example07") //Response Body
				.type(MediaType.TEXT_PLAIN)
				.build();	
	}
	
	//trying to achieve HATEOAS, means sending URL/links in the response
	//two options in JAX-RS
	//checking option 1
	@Path("/08")
	@POST
	@Consumes({"application/xml","application/json"})
	public Response example08(Trade trade, @Context UriInfo uriInfo) {
		TradeDao dao = new TradeDao();
		dao.add(trade);
		
		URI uri = uriInfo
					.getBaseUriBuilder() //http://localhost:8080/JAX-RS-Proj/1.0
					.path(TradeResource.class) // /trade
					.path(TradeResource.class , "get") // {tradeId}
					.build(trade.getTradeId()); //substituting {tradeId} and generating the final URI
		
		return Response
				.created(uri) //201 HTTP Status Code
				.entity("Trade record created successfully!")
				.type(MediaType.TEXT_PLAIN)
				.build();
	}
	
	//checking option 2 : sending multiple links in the response
	@Path("/09")
	@POST
	@Consumes({"application/xml","application/json"})
	public Response example09(Trade trade, @Context UriInfo uriInfo) {
		TradeDao dao = new TradeDao();
		dao.add(trade);
		
		/*URI uri UriBuilder
					.fromUri("http://{server}:{port}/{context}/{base}")
					.build("jpmc.com","9999","app","trade")*/
		
		URI uri1 = uriInfo
					.getBaseUriBuilder() //http://localhost:8080/JAX-RS-Proj/1.0
					.path(TradeResource.class) // /trade
					.path(TradeResource.class , "get") // {tradeId}
					.build(trade.getTradeId()); //substituting {tradeId} and generating the final URI
		
		Link link1 = Link
						.fromUri(uri1)
						.rel("Get Trade By ID") //any logical name
						.build();

		URI uri2 = uriInfo
					.getBaseUriBuilder() //http://localhost:8080/JAX-RS-Proj/1.0
					.path(TradeResource.class) // /trade
					.path(TradeResource.class , "getAll") // /all
					.build(); //generating the final URI
	
		Link link2 = Link
						.fromUri(uri2)
						.rel("Get all Trades") //any logical name
						.build();

		return Response
				.ok() //200 OK
				.links(link1, link2)
				.entity("Trade record created successfully!")
				.type(MediaType.TEXT_PLAIN)
				.build();
	}

	//in this example we will see the usage of MessageBodyWriter
	@Path("/10/{tradeId}")
	@GET
	@Produces("text/csv")
	public Trade example10(@PathParam("tradeId") int tradeId) {
		TradeDao dao = new TradeDao();
		return dao.fetchById(tradeId);
	}

	
}
