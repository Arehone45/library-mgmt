package com.library.mgmnt.student.service.serviceImpl;

import com.library.mgmnt.student.entity.Student;
import com.library.mgmnt.student.exception.StudentException;
import com.library.mgmnt.student.repository.StudentRepository;
import com.library.mgmnt.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {

        if (student == null)
        {
            throw new StudentException("Please enter student values");
        }

        Student savedStudent = new Student();

        savedStudent = studentRepository.save(student);

        return savedStudent;
    }

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByStudentNo(String studentNo) {

        Student student = studentRepository.findByStudentNo(studentNo);

        if (student == null) {
            throw new StudentException("Student with student no: " + studentNo + " not found.");
        }
        else {
            return student;
        }
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentException("Student with id: " + id + " not found"));

    }
}
