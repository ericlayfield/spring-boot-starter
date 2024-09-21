package com.edl.student;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

//start an embedded server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerITest {

	@Autowired
	private TestRestTemplate template;

    @Test
    public void getStudents() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/students", String.class);
        assertThat(response.getBody()).isEqualTo("[{\"id\":1,\"firstName\":\"Sam\",\"lastName\":\"Smith\",\"email\":\"sammysmith@live.com\",\"birthDate\":\"2012-03-12\"},{\"id\":2,\"firstName\":\"Ralph\",\"lastName\":\"Thomas\",\"email\":\"RalphThomas@gmail.com\",\"birthDate\":\"2010-11-01\"}]");
    }
}
