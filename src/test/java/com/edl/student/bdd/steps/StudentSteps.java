package com.edl.student.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StudentSteps {
    @Given("student id is {string}")
    public void studentIdIsValid(String studentId) {
        if (studentId == null || studentId.isEmpty()) { throw new RuntimeException("empty id");}
    }

    @When("student does not exist")
    public void studentDoesNotExist() {
        
    }

    @Then("student is added")
    public void studentIsAdded() {
    }
}
