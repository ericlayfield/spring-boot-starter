package com.edl.student.model;

import org.springframework.data.jpa.repository.JpaRepository;

//used JpaRepository to extend CrudRepository for paging and sorting ability
public interface StudentRepository extends JpaRepository<Student, Long> {
}
