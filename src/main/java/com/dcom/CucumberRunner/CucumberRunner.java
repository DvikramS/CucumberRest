package com.dcom.CucumberRunner;


import org.testng.annotations.BeforeSuite;

import com.jayway.restassured.RestAssured;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		strict = false,
		monochrome = true, 
		features = "src/main/resources/features",
		glue = "com.dcom.stepdefinition",
		plugin = {"pretty", "html:target/cucumber-html-report"})
		//"pretty", "html:target/cucumber-html-report"

public class CucumberRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite(alwaysRun=true)
	public void setup(){
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.urlEncodingEnabled = true;
	}
}
