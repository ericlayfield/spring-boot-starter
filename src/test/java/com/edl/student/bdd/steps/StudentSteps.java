package com.edl.student.bdd.steps;

import com.edl.student.model.Student;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static java.time.LocalDate.parse;
import static org.junit.jupiter.api.Assertions.*;

public class StudentSteps {
    private static final String URI_BASE = "http://localhost:8080";
    public static final String HEALTH = URI_BASE + "/actuator/health";
    private static final String STUDENTS = URI_BASE + "/students";
    public static final String STUDENTS_ID = STUDENTS + "/{id}";
    private final RestClient restClient = RestClient.create();
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
                    restClient
                            .get()
                            .uri(STUDENTS_ID, studentId)
                            .retrieve()
                            .body(String.class);
                }
        );
    }

    @Then("student is added")
    public void studentIsAdded() {
        ResponseEntity<Void> response = restClient
                .post()
                .uri(STUDENTS)
                .body(student)
                .retrieve()
                .toBodilessEntity();
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @When("student exists")
    public void studentExist() {
        student = restClient
                .get()
                .uri(STUDENTS_ID, studentId)
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
        assertEquals(parse(birthDate, formatter), student.getBirthDate());
    }

    @And("student has email {string}")
    public void studentHasEmail(String email) {
        assertEquals(email, student.getEmail());
    }

    @Then("student first name is {string}")
    public void studentFirstNameIs(String firstName) {
        student = new Student();
        student.setFirstName(firstName);
    }

    @And("student last name is {string}")
    public void studentLastNameIs(String lastName) {
        student.setLastName(lastName);
    }

    @And("student birth date is {string}")
    public void studentBirthDateIs(String birthDate) {
        student.setBirthDate(parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @And("student email is {string}")
    public void studentEmailIs(String email) {
        student.setEmail(email);
    }

    @Given("the service is running")
    public void theServiceIsRunning() {
        Map response =
                restClient
                    .get()
                    .uri(HEALTH)
                    .retrieve()
                    .body(Map.class);
        assertNotNull(response);
        assertEquals("UP", response.get("status"));
    }

    @And("student {long} is updated")
    public void studentIsUpdated(Long studentId) {
        ResponseEntity<Void> response = restClient
                .put()
                .uri(STUDENTS + "/" + studentId )
                .body(student)
                .retrieve()
                .toBodilessEntity();
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Then("student {long} is deleted")
    public void studentIsDeleted(Long studentId) {
        ResponseEntity<Void> response = restClient
                .delete()
                .uri(STUDENTS + "/" + studentId)
                .retrieve()
                .toBodilessEntity();
    }
}
