package com.angjs.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TradeExceptionMapper implements ExceptionMapper<TradeException> {

	@Override
	public Response toResponse(TradeException ex) {
		// exception handling code will be here, like logging and other
		
		ErrorObj errorObj = new ErrorObj();
		errorObj.setErrorCode(123);
		errorObj.setErrorMessage(ex.getMessage());
		
		return Response
				.status(400) //HTTP Status Code
				.entity(errorObj) //Response Body
				.type("application/json") //Content-Type header
				.build();
	}

	public static class ErrorObj {
		private int errorCode;
		private String errorMessage;
		
		public int getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		
	}
	
}
