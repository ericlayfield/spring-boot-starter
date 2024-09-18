package com.edl.student;

import com.edl.student.model.Student;
import com.edl.student.model.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class StudentTest {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("adding a student to the student repository")
    public void addStudent() throws ParseException {
        Student student = createStudent();
        assertStudent(student);

        studentRepository.save(student);
        student = studentRepository.findById(student.getId()).get();
        assertStudent(student);
        assertTrue(student.getId()>0);

    }

    private void assertStudent(Student student) throws ParseException {
        assertNotNull(student.getId());
        assertEquals("Ralph", student.getFirstName());
        assertEquals("Thomas", student.getLastName());
        assertEquals("RalphThomas@gmail.com", student.getEmail());
        assertEquals(sdf.parse("03/08/2012"), student.getBirthDate());
    }

    private Student createStudent() throws ParseException {
        Student student = new Student();
        student.setFirstName("Ralph");
        student.setLastName("Thomas");
        student.setEmail("RalphThomas@gmail.com");
        student.setBirthDate(sdf.parse("03/08/2012"));
        return student;
    }

}
