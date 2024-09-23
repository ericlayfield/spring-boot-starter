package com.edl.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//start the web server with the repository and data loaded
@SpringBootTest
@AutoConfigureMockMvc
//a lighter version would use the following
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(StudentController.class)
//with a MockBean repository
public class StudentControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getStudents() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"firstName\":\"Sam\",\"lastName\":\"Smith\",\"email\":\"sammysmith@live.com\",\"birthDate\":\"2012-03-12\"},{\"id\":2,\"firstName\":\"Ralph\",\"lastName\":\"Thomas\",\"email\":\"RalphThomas@gmail.com\",\"birthDate\":\"2010-11-01\"}]")));
	}

	@Test
	public void getStudent() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/students/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":1,\"firstName\":\"Sam\",\"lastName\":\"Smith\",\"email\":\"sammysmith@live.com\",\"birthDate\":\"2012-03-12\"}")));
	}

	@Test
	public void notFound() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/students/3").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().string("student with id " + 3 + " not found"));
	}
}
