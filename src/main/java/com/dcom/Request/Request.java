package com.dcom.Request;

import java.io.IOException;

import com.dcom.test.APIObjects.TestAPIExample;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

public class Request {
	private JsonNode jsonNode;
	public Request() {
	}
	public Request(JsonNode jsonNode) {
		this.jsonNode=jsonNode;
	}


	private JsonNode generatePayLoad(String path){
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			this.jsonNode=objectMapper.readTree(Request.class.getResourceAsStream(path));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.jsonNode;
	}
	
	public Request buildPayload(String path){
		
		return new Request(generatePayLoad(path));
	}
	
	public JsonNode getRequest(){
		return this.jsonNode;
	}

	public void updateNodeValue(String path, Object value){

		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()).build(); 
		JsonPath.using(configuration).parse(this.jsonNode).set(path, value).json();

	}

	public void deleteNode(String path, Object value){

		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()).build(); 
		JsonPath.using(configuration).parse(this.jsonNode).delete(path).json();

	}

	public void addNode(String path, Object value){

		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()).build(); 
		JsonPath.using(configuration).parse(this.jsonNode).add(path, value).json();

	}
	
	

}
