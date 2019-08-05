package com.dcom.APIDriver;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.dcom.Request.Request;
import com.dcom.test.APIObjects.TestAPIExample;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;


public class BaseAPIObject extends Request{

	public Properties prop;
	public RestParameters params;
	public BaseAPIObject() {
		params= new RestParameters();
		prop=new Properties();
		try {
			prop.load(BaseAPIObject.class.getResourceAsStream("/setup.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Response post(String url, Request payload,RestParameters params){
		String uri=generateURL(url, params);

		RequestSpecification specs=generateSpecification(params);

		return RestAssured.given().spec(specs).body(payload.getRequest()).post(uri);


	}
	public Response put(String url, Request payload,RestParameters params){
		String uri=generateURL(url, params);
		RequestSpecification specs=generateSpecification(params);

		return RestAssured.given().spec(specs).body(payload.getRequest()).put(uri);
	}
	public Response get(String url,RestParameters params){
		String uri=generateURL(url, params);

		RequestSpecification specs=generateSpecification(params);

		return RestAssured.given().spec(specs).get(uri);
	}
	public Response delete(String url,RestParameters params){
		String uri=generateURL(url, params);
		RequestSpecification specs=generateSpecification(params);

		return RestAssured.given().spec(specs).delete(uri);
	}

	private String generateURL(String url, RestParameters params){
		String uri=prop.getProperty("URL")+url;
		if(!(params.getPath().isEmpty()) &&params.getPath()!=null){
			Set<Entry<String, Object>> entry=params.getPath().entrySet();
			for(Entry<String, Object> set:entry){

				if(uri.contains("{"+ set.getKey()+"}"))
					uri=uri.replace("{"+ set.getKey()+"}", set.getValue().toString());
			}
		}

		return uri;
	}

	private RequestSpecification generateSpecification(RestParameters params){

		RequestSpecification spec= RestAssured.given().accept(ContentType.JSON)
				.contentType(ContentType.JSON);

		if(!(params.getQuery().isEmpty()) &&params.getQuery()!=null){
			spec.queryParameters(params.getQuery());
		} if(!(params.getHeader().isEmpty()) &&params.getHeader()!=null){
			spec.headers(params.getHeader());
		}
		return spec;
	}

	public void clearParams(){

		params.getHeader().clear();
		params.getPath().clear();
		params.getQuery().clear();
	}


}
