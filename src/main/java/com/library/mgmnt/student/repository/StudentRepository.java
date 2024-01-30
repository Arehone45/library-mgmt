package com.library.mgmnt.student.repository;

import com.library.mgmnt.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByStudentNo(String studentNo);
}
