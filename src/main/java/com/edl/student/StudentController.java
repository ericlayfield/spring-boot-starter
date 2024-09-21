package com.edl.student;

import com.edl.student.model.Student;
import com.edl.student.model.StudentNotFoundException;
import com.edl.student.model.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

	private final StudentRepository studentRepository;

	StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("/students")
	List<Student> all() { return studentRepository.findAll();}

	@GetMapping("/students/{id}")
	Student one(@PathVariable Long id) {
		return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id.toString()));
	}

}
