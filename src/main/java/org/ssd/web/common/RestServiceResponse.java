package org.ssd.web.common;

public class RestServiceResponse {

	private Object data;
	
	public RestServiceResponse(Object data){
		this.data = data;
	}

	public Object getData() {
		return data;
	}
}
