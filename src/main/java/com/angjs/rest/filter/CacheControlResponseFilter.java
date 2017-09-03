package com.angjs.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.Provider;

@Provider
public class CacheControlResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		System.out.println("CacheControlResponseFilter executed..");
		
		if(request.getMethod().equals("GET")) {
			CacheControl cc = new CacheControl();
			cc.setMaxAge(120);
			response.getHeaders().add("Cache-Control", cc);
		}
	}

	
}
