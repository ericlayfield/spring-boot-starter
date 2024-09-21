package com.edl.student.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class StudentRepositoryTest {

    public static final LocalDate BIRTH_DATE = of(2010, 11, 1);
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("adding a student to the student repository")
    public void addStudent() {
        Student student = createStudent();
        assertStudent(student, "Ralph", "Thomas", "RalphThomas@gmail.com", BIRTH_DATE);

        studentRepository.save(student);
        student = studentRepository.findById(student.getId()).get();
        assertStudent(student, "Ralph", "Thomas", "RalphThomas@gmail.com", BIRTH_DATE);
        assertTrue(student.getId()>0);

    }

    @Test
    @DisplayName("validate the StudentNotFoundException")
    public void validateStudentNotFoundException() {
        StudentNotFoundException thrown = assertThrows(
                StudentNotFoundException.class,
                () -> {
                    long id = 111;
                    studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found + id"));
                }
        );
    }

    public static void assertStudent(Student student, String firstName, String lastName, String email, LocalDate birthDate) {
        assertNotNull(student.getId());
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertEquals(email, student.getEmail());
        assertEquals(birthDate, student.getBirthDate());
        System.out.println(student);
    }

    private Student createStudent() {
        Student student = new Student();
        student.setFirstName("Ralph");
        student.setLastName("Thomas");
        student.setEmail("RalphThomas@gmail.com");
        student.setBirthDate(BIRTH_DATE);
        return student;
    }

}
