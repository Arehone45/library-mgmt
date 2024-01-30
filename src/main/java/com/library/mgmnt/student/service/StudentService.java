package com.library.mgmnt.student.service;

import com.library.mgmnt.student.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentByStudentNo(String studentNo);
    Student getStudentById(Long id);
}
