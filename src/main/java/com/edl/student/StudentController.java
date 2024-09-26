package com.edl.student;

import com.edl.student.model.Student;
import com.edl.student.model.StudentNotFoundException;
import com.edl.student.model.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	private final StudentRepository studentRepository;

	StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("/students")
	List<Student> all() {
		log.info("get all students");
		return studentRepository.findAll();
	}

	@GetMapping("/students/{id}")
	Student one(@PathVariable Long id) {
		log.info("get student with id {}", id);
		return studentRepository.findById(id)
				.orElseThrow(() -> createStudentNotFoundException(id));
	}

	@PostMapping("/students")
	Student create(@RequestBody Student student) {
		log.info("create student {}", student);
		return studentRepository.save(student);
	}

	private static StudentNotFoundException createStudentNotFoundException(Long id) {
		String message = "student with id " + id + " not found";
		log.warn(message);
		return new StudentNotFoundException(message);
	}

}
