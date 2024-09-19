package com.edl.student.utility;

import com.edl.student.model.Student;
import com.edl.student.ultiliy.Builder;
import org.junit.jupiter.api.Test;

import static com.edl.student.model.StudentTest.assertStudent;
import static java.time.LocalDate.of;

public class BuilderTest {

    @Test
    public void buildStudent() {
        Student student = Builder.of(Student::new)
                .with(Student::setFirstName, "Sam")
                .with(Student::setLastName, "Smith")
                .with(Student::setEmail, "sammysmith@live.com")
                .with(Student::setBirthDate, of(2012,3,12))
                .build();
        assertStudent(student,"Sam", "Smith", "sammysmith@live.com", of(2012,3,12));
    }
}
