package com.edl.student;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getStudents() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"firstName\":\"Sam\",\"lastName\":\"Smith\",\"email\":\"sammysmith@live.com\",\"birthDate\":\"2012-03-12\"},{\"id\":2,\"firstName\":\"Ralph\",\"lastName\":\"Thomas\",\"email\":\"RalphThomas@gmail.com\",\"birthDate\":\"2010-11-01\"}]")));
	}
}
