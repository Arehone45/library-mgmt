package com.library.mgmnt.student.controller;

import com.library.mgmnt.student.entity.Student;
import com.library.mgmnt.student.service.serviceImpl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-service")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;

    @PostMapping("/addStudent")
    ResponseEntity<Student> addStudent(@RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);

        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/getStudentByStdNo/{studentNo}")
    ResponseEntity<Student> getByStdNo(@PathVariable String studentNo) {

        Student student = studentService.getStudentByStudentNo(studentNo);

        return ResponseEntity.ok(student);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<Student>> getAll()
    {
        List<Student> student = studentService.getAllStudents();

        return ResponseEntity.ok(student);
    }
}
