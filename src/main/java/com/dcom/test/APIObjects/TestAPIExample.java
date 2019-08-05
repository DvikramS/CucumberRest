package com.dcom.test.APIObjects;

import com.dcom.APIDriver.BaseAPIObject;
import com.dcom.Request.Request;
import com.jayway.restassured.response.Response;

public class TestAPIExample extends BaseAPIObject {
	
	private static final String getUrl="/MyWebApp/topics";
	private static final String postURL="/MyWebApp/topics";
	private static final String getById="/MyWebApp/topics/{id}";
	public static final String postBodyPath="/PostBodyPath/inputbody.json";
	
	public TestAPIExample() {
		// TODO Auto-generated constructor stub
	}
	
	public Response getByid1(String id){
		clearParams();
		params.setPath("id", id);

		
		return get(getById, params);

	}
	
	public Response get(){
		
		return get(getUrl, params);
		
	}
	
public Response addResource(Request body){
	clearParams();
		return post(postURL, body, params);

}

public Response getByid(String id){
	
	params.setPath("id", id);
	params.setQuery("id", id);
	params.setHeader("id", id);
	
	return get(getById, params);

}
}
