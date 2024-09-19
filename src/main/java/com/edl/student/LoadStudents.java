package com.edl.student;

import com.edl.student.model.Student;
import com.edl.student.model.StudentRepository;
import com.edl.student.ultiliy.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.time.LocalDate.of;

@Configuration
public class LoadStudents {
    private static final Logger log = LoggerFactory.getLogger(LoadStudents.class);

    private ApplicationContext context;


    @Bean
    CommandLineRunner run(StudentRepository studentRepository) {
        return args -> {
            log.info("Loading students:");
            studentRepository.save (Builder.of(Student::new)
                    .with(Student::setFirstName, "Sam")
                    .with(Student::setLastName, "Smith")
                    .with(Student::setEmail, "sammysmith@live.com")
                    .with(Student::setBirthDate, of(2012,3,12))
                    .build());
            studentRepository.save (Builder.of(Student::new)
                    .with(Student::setFirstName, "Ralph")
                    .with(Student::setLastName, "Thomas")
                    .with(Student::setEmail, "RalphThomas@gmail.com")
                    .with(Student::setBirthDate, of(2010,11,1))
                    .build());
            log.info("student repository count: " + studentRepository.count());
        };
    }

}
