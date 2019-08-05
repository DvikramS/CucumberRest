package com.dcom.stepdefinition;

import java.net.HttpURLConnection;

import org.testng.Assert;

import com.dcom.Request.Request;
import com.dcom.test.APIObjects.TestAPIExample;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test1 {
	
	TestAPIExample ex;
	Request body;
	Response res;
	
	@Given("^request body is given$")
	public void builPayLoad() {
		ex=new TestAPIExample();
		body = ex.buildPayload(TestAPIExample.postBodyPath);
		System.out.println(body.getRequest().get("id"));
	body.updateNodeValue("id", "17");
	}
	
	
	@When("^Hit API with given and URL$")
	public void hitAPI(){	
		res=ex.addResource(body);
	}
	
	@Then("^response should be successful$")
	public void validate(){
		Assert.assertEquals(res.getStatusCode(), HttpURLConnection.HTTP_OK);
		Assert.assertEquals(res.getBody().jsonPath().getString("name"), body.getRequest().get("name").asText());
		System.out.println(res.asString());
		System.out.println(res.getBody().jsonPath().getString("id"));
	}
	

}
