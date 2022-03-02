package com.cgg.data;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= "resources/Cucumber.feature",
	plugin = {"pretty","json:target/cucumber.json"},
	glue= {"StepDefination"}
)
public class CucumberRunnerTest {

}
