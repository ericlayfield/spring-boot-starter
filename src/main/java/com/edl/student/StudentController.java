package com.edl.student;

import com.edl.student.model.Student;
import com.edl.student.model.StudentNotFoundException;
import com.edl.student.model.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/students")
@RestController
public class StudentController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	private final StudentRepository studentRepository;

	StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping()
	List<Student> all() {
		log.info("get all students");
		return studentRepository.findAll();
	}

	@GetMapping("/{id}")
	Student one(@PathVariable Long id) {
		log.info("get student with id {}", id);
		return studentRepository.findById(id)
				.orElseThrow(() -> createStudentNotFoundException(id));
	}

	@PostMapping()
	Student create(@RequestBody Student student) {
		log.info("create student {}", student);
		return studentRepository.save(student);
	}

	@PutMapping("/{id}")
	Student update(@PathVariable Long id, @RequestBody Student student) {
		log.info("update student with id {}", id);
		return studentRepository.save(student);
	}

	@DeleteMapping("/{id}")
	void delete(@PathVariable Long id) {
		log.info("delete student with id {}", id);
		studentRepository.deleteById(id);
	}

	private static StudentNotFoundException createStudentNotFoundException(Long id) {
		String message = "student with id " + id + " not found";
		log.warn(message);
		return new StudentNotFoundException(message);
	}

}
