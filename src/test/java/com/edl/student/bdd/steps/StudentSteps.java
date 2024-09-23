package com.edl.student.bdd.steps;

import com.edl.student.model.Student;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentSteps {
    private static final String URI_BASE = "http://localhost:8080/students";
    private RestClient restClient = RestClient.create();
    private Long studentId;
    private Student student;

    @Before
    public void before() {
        this.studentId = null;
        this.student = null;
    }

    @Given("student id is {long}")
    public void studentIdIsValid(Long studentId) {
        if (studentId == null) { throw new RuntimeException("invalid student id");}
        if (studentId <= 0) { throw new RuntimeException("invalid student id");}
        this.studentId = studentId;
    }

    @When("student does not exist")
    public void studentDoesNotExist() {
        assertThrows(
                HttpClientErrorException.class, () -> {
                    restClient.get()
                            .uri(URI_BASE + "/{id}", studentId)
                            .retrieve()
                            .body(String.class);
                }
        );
    }

    @Then("student is added")
    public void studentIsAdded() {

    }

    @When("student exists")
    public void studentExist() {
        student = restClient.get()
                .uri(URI_BASE + "/{id}", studentId)
                .retrieve()
                .body(Student.class);
    }

    @Then("student has first name {string}")
    public void studentHasFirstName(String firstName) {
        assertEquals(firstName, student.getFirstName());
    }

    @And("student has last name {string}")
    public void studentHasLastName(String lastName) {
        assertEquals(lastName, student.getLastName());
    }

    @And("student has birth date {string}")
    public void studentHasBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertEquals(LocalDate.parse(birthDate, formatter), student.getBirthDate());
    }

}
